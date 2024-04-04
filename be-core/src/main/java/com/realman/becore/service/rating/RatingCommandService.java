package com.realman.becore.service.rating;

import org.springframework.stereotype.Service;
import com.realman.becore.dto.rating.Rating;
import com.realman.becore.dto.rating.RatingMapper;
import com.realman.becore.error_handlers.exceptions.ResourceNotFoundException;
import com.realman.becore.repository.database.rating.RatingEntity;
import com.realman.becore.repository.database.rating.RatingRepository;
import com.realman.becore.service.booking.service.BookingServiceCommandService;
import com.realman.becore.service.booking.service.BookingServiceQueryService;
import com.realman.becore.util.RequestContext;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RatingCommandService {
    @NonNull
    private final RatingRepository ratingRepository;
    @NonNull
    private final RequestContext requestContext;
    @NonNull
    private final BookingServiceQueryService bookingServiceQueryService;
    @NonNull
    private final BookingServiceCommandService bookingServiceCommandService;
    @NonNull
    private final RatingMapper ratingMapper;

    public void save(Rating rating) {
        bookingServiceQueryService.findById(rating.bookingServiceId());
        RatingEntity saveRating = ratingMapper.toEntity(rating, requestContext.getCustomerId());
        RatingEntity savedRating = ratingRepository.save(saveRating);
        bookingServiceCommandService.updateRating(savedRating.getBookingServiceId(), savedRating.getRatingId());
    }

    public void update(Long ratingId, Rating rating) {
        RatingEntity foundRating = ratingRepository.findById(ratingId).orElseThrow(ResourceNotFoundException::new);
        ratingMapper.updateEntity(foundRating, rating);
        ratingRepository.save(foundRating);
    }
}
