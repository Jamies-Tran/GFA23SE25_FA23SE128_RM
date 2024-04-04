package com.realman.becore.service.booking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import java.time.LocalTime;
import com.realman.becore.controller.api.booking.service.models.BookingResultRequest;
import com.realman.becore.controller.api.booking.service.models.BookingServiceId;
import com.realman.becore.dto.booking.result.BookingResult;
import com.realman.becore.dto.booking.service.BookingService;
import com.realman.becore.dto.booking.service.BookingServiceInfo;
import com.realman.becore.dto.booking.service.BookingServiceMapper;
import com.realman.becore.dto.enums.EBookingServiceStatus;
import com.realman.becore.dto.enums.EBookingServiceType;
import com.realman.becore.error_handlers.exceptions.ResourceInvalidException;
import com.realman.becore.error_handlers.exceptions.ResourceNotFoundException;
import com.realman.becore.repository.database.booking.service.BookingServiceEntity;
import com.realman.becore.repository.database.booking.service.BookingServiceRepository;
import com.realman.becore.service.booking.result.BookingResultCommandService;
import com.realman.becore.service.twilio.TwilioUseCaseService;
import com.realman.becore.util.RequestContext;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingServiceCommandService {
    @NonNull
    private final BookingServiceRepository bookingServiceRepository;
    @NonNull
    private final BookingResultCommandService bookingResultCommandService;
    @NonNull
    private final BookingServiceMapper bookingServiceMapper;
    @NonNull
    private final TwilioUseCaseService twilioUseCaseService;
    @NonNull
    private final RequestContext requestContext;

    public List<BookingService> saveAll(Long bookingId, List<BookingService> bookingServiceList) {
        List<BookingServiceEntity> bookingServiceEntities = bookingServiceList.stream()
                .map(booking -> {
                    BookingServiceEntity entity = new BookingServiceEntity();
                    if (booking.bookingServiceType().equals(EBookingServiceType.PICKUP_STYLIST)) {
                        entity = bookingServiceMapper.toEntity(booking, bookingId, EBookingServiceStatus.PENDING,
                                EBookingServiceType.PICKUP_STYLIST);
                    } else if (booking.bookingServiceType().equals(EBookingServiceType.CHOSEN_STYLIST)) {
                        entity = bookingServiceMapper.toEntity(booking, bookingId, EBookingServiceStatus.ONGOING,
                                EBookingServiceType.CHOSEN_STYLIST);
                    }
                    return entity;
                }).toList();
        return bookingServiceRepository.saveAll(bookingServiceEntities).stream().map(bookingServiceMapper::toDto)
                .toList();
    }

    public void chooseStylist(Long staffId, Long bookingServiceId) {
        BookingServiceEntity foundBookingService = bookingServiceRepository.findById(bookingServiceId)
                .orElseThrow(ResourceNotFoundException::new);
        if (!foundBookingService.getBookingServiceType().equals(EBookingServiceType.PICKUP_STYLIST)) {
            throw new ResourceInvalidException();
        }
        foundBookingService.setStaffId(staffId);
        foundBookingService.setBookingServiceStatus(EBookingServiceStatus.ONGOING);
        foundBookingService.setBookingServiceType(EBookingServiceType.CHOSEN_STYLIST);
        bookingServiceRepository.save(foundBookingService);
    }

    public BookingService startService(BookingServiceId bookingServiceId) {
        BookingServiceInfo bookingInfo = bookingServiceRepository
                .findInfoById(bookingServiceId.value(), requestContext.getAccountId())
                .orElseThrow(ResourceNotFoundException::new);
        if (bookingInfo.getAllowUpdate()) {
            if (!bookingInfo.getBookingServiceStatus().equals(EBookingServiceStatus.CONFIRM)) {
                throw new ResourceInvalidException();
            }
            List<BookingServiceEntity> otherBookingServices = bookingServiceRepository
                    .findOtherByBookingId(bookingInfo.getBookingId(), bookingInfo.getBookingServiceId());
            if (otherBookingServices.stream()
                    .filter(b -> !b.getBookingServiceStatus().equals(EBookingServiceStatus.REQUEST_CONFIRM)).findAny()
                    .isPresent()) {
                throw new ResourceInvalidException("Có dịch vụ chưa được confirm");
            }
            BookingServiceEntity foundBookingServiceEntity = bookingServiceMapper.toEntity(bookingInfo);
            foundBookingServiceEntity.setBookingServiceStatus(EBookingServiceStatus.PROCESSING);
            foundBookingServiceEntity.setActualStartAppointment(LocalTime.now());
            BookingServiceEntity saveBookingService = bookingServiceRepository.save(foundBookingServiceEntity);
            lockBookingService(foundBookingServiceEntity.getBookingId(),
                    foundBookingServiceEntity.getBookingServiceId());
            return bookingServiceMapper.toDto(saveBookingService);
        }
        return null;
    }

    public BookingService finishService(BookingServiceId bookingServiceId, BookingResultRequest bookingResultRequests) {
        BookingServiceInfo bookingInfo = bookingServiceRepository
                .findInfoById(bookingServiceId.value(), requestContext.getAccountId())
                .orElseThrow(ResourceNotFoundException::new);
        if (bookingInfo.getAllowUpdate()) {
            if (!bookingInfo.getBookingServiceStatus().equals(EBookingServiceStatus.PROCESSING)) {
                throw new ResourceInvalidException();
            }
            BookingServiceEntity foundBookingServiceEntity = bookingServiceMapper.toEntity(bookingInfo);
            foundBookingServiceEntity.setBookingServiceStatus(EBookingServiceStatus.FINISHED);
            foundBookingServiceEntity.setActualEndAppointment(LocalTime.now());
            BookingServiceEntity savedBookingService = bookingServiceRepository.save(foundBookingServiceEntity);
            unlockBookingService(foundBookingServiceEntity.getBookingId(),
                    foundBookingServiceEntity.getBookingServiceId());
            List<BookingResult> bookingResults = bookingResultRequests.bookingResultImgs().stream().map(
                    b -> BookingResult.builder()
                            .bookingResultImg(b)
                            .bookingServiceId(bookingServiceId.value())
                            .build())
                    .toList();
            bookingResultCommandService.saveAll(bookingResults);
            return bookingServiceMapper.toDto(savedBookingService);
        }
        return null;
    }

    public BookingService confirmService(BookingServiceId bookingServiceId) {
        BookingServiceInfo bookingInfo = bookingServiceRepository
                .findInfoById(bookingServiceId.value(), requestContext.getAccountId())
                .orElseThrow(ResourceNotFoundException::new);
        if (bookingInfo.getAllowUpdate()) {
            if (!bookingInfo.getBookingServiceStatus().equals(EBookingServiceStatus.ONGOING)
                    && !bookingInfo.getBookingServiceStatus().equals(EBookingServiceStatus.REQUEST_CONFIRM)) {
                throw new ResourceInvalidException();
            }
            BookingServiceEntity foundBookingService = bookingServiceMapper.toEntity(bookingInfo);
            foundBookingService.setBookingServiceStatus(EBookingServiceStatus.CONFIRM);
            List<BookingServiceEntity> otherBookingServices = bookingServiceRepository
                    .findOtherByBookingId(foundBookingService.getBookingId(), bookingServiceId.value());
            List<BookingServiceEntity> updateBookingServices = otherBookingServices.stream()
                    .map(bs -> {
                        if (bs.getBookingServiceStatus().equals(EBookingServiceStatus.ONGOING)) {
                            bs.setBookingServiceStatus(EBookingServiceStatus.REQUEST_CONFIRM);
                        }
                        return bs;
                    }).toList();
            BookingServiceEntity saveBookingService = bookingServiceRepository.save(foundBookingService);
            bookingServiceRepository.saveAll(updateBookingServices);
            return bookingServiceMapper.toDto(saveBookingService);
        }
        return null;
    }

    public void cancelAllBookingService(Long bookingId) {
        List<BookingServiceEntity> bookingServices = bookingServiceRepository.findAllByBookingId(bookingId);
        List<BookingServiceEntity> updateBookingServices = bookingServices.stream().map(bs -> {
            if (canBookingServiceCancel(bs)) {
                bs.setBookingServiceStatus(EBookingServiceStatus.CANCLED);
            }
            return bs;
        }).toList();
        bookingServiceRepository.saveAll(updateBookingServices);
    }

    public void cancelBookingService(Long bookingServiceId) {
        BookingServiceEntity bookingService = bookingServiceRepository.findById(bookingServiceId)
                .orElseThrow(ResourceNotFoundException::new);
        boolean canBookingServiceCancel = canBookingServiceCancel(bookingService);
        if (canBookingServiceCancel) {
            bookingService.setBookingServiceStatus(EBookingServiceStatus.CANCLED);
            bookingServiceRepository.save(bookingService);
        } else {
            throw new ResourceNotFoundException();
        }
    }

    private void lockBookingService(Long bookingId, Long bookingServiceId) {
        List<BookingServiceEntity> foundBookingServices = bookingServiceRepository.findAllNotEqualId(bookingId,
                bookingServiceId);
        List<BookingServiceEntity> updatedBookingServices = foundBookingServices.stream().map(bs -> {
            if (bs.getBookingServiceStatus().equals(EBookingServiceStatus.CONFIRM)) {
                bs.setBookingServiceStatus(EBookingServiceStatus.LOCKED);
            }
            return bs;
        }).toList();
        bookingServiceRepository.saveAll(updatedBookingServices);
    }

    private void unlockBookingService(Long bookingId, Long bookingServiceId) {
        List<BookingServiceEntity> foundBookingServices = bookingServiceRepository.findAllNotEqualId(bookingId,
                bookingServiceId);
        List<BookingServiceEntity> updatedBookingServices = foundBookingServices.stream().map(bookingService -> {
            if (bookingService.getBookingServiceStatus().equals(EBookingServiceStatus.LOCKED)) {
                bookingService.setBookingServiceStatus(EBookingServiceStatus.CONFIRM);
            }
            return bookingService;
        }).toList();
        bookingServiceRepository.saveAll(updatedBookingServices);
    }

    public void chooseStylistForAll(Long bookingId, Long staffId) {
        List<BookingServiceEntity> bookingServices = bookingServiceRepository.findAllByBookingId(bookingId);
        List<BookingServiceEntity> updatedBookingServices = new ArrayList<>();
        bookingServices.forEach(bs -> {
            if (bs.getBookingServiceType().equals(EBookingServiceType.PICKUP_STYLIST)) {
                bs.setStaffId(staffId);
                bs.setBookingServiceStatus(EBookingServiceStatus.ONGOING);
                bs.setBookingServiceType(EBookingServiceType.CHOSEN_STYLIST);
                updatedBookingServices.add(bs);
            }
        });
        bookingServiceRepository.saveAll(updatedBookingServices);
    }

    public void endBookingService(Long bookingId) {
        List<BookingServiceEntity> bookingServices = bookingServiceRepository.findAllByBookingId(bookingId);
        List<BookingServiceEntity> saveBookingServices = bookingServices.stream()
                .map(bs -> {
                    bs.setBookingServiceStatus(EBookingServiceStatus.PAID);
                    return bs;
                }).toList();
        bookingServiceRepository.saveAll(saveBookingServices);
    }

    public void updateRating(Long bookingServiceId, Long ratingId) {
        BookingServiceEntity bookingService = bookingServiceRepository.findById(bookingServiceId)
                .orElseThrow(ResourceNotFoundException::new);
        bookingService.setRatingId(ratingId);
        bookingServiceRepository.save(bookingService);
    }

    private boolean canBookingServiceCancel(BookingServiceEntity bookingService) {
        return bookingService.getBookingServiceStatus().equals(EBookingServiceStatus.ONGOING)
                || bookingService.getBookingServiceStatus()
                        .equals(EBookingServiceStatus.CONFIRM)
                || bookingService.getBookingServiceStatus()
                        .equals(EBookingServiceStatus.REQUEST_CONFIRM);
    }
}
