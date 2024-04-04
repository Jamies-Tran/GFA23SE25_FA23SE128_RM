package com.realman.becore.service.twilio;

import org.springframework.stereotype.Service;

import com.realman.becore.dto.booking.Booking;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TwilioUseCaseService {
    @NonNull
    private final TwilioRequestService twilioRequestService;

    public void sendOTP(String phone, String otp) {
        twilioRequestService.sendOTP(phone, otp);
    }

    public void informBooking(String phone, Booking booking) {
        twilioRequestService.informBooking(phone, booking);
    }

    public void informEndBooking(String phone, Booking booking) {
        twilioRequestService.informEndBooking(phone, booking);
    }

    public void denyBooking(String phone, String bookingCode) {
        twilioRequestService.denyBooking(phone, bookingCode);
    }
}
