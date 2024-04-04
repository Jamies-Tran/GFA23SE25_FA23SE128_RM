package com.realman.becore.service.twilio;

import org.springframework.stereotype.Service;
import java.time.format.DateTimeFormatter;
import com.realman.becore.dto.booking.Booking;
import com.realman.becore.dto.booking.service.BookingService;
import com.realman.becore.util.TwilioUtil;

@Service
public class TwilioRequestService {

    public void sendOTP(String phone, String otp) {
        TwilioUtil.initTwilio();
        String sendOTPMessage = sendOTPMessage(otp);
        TwilioUtil.sendMessage(phone, sendOTPMessage);
    }

    public void informBooking(String phone, Booking booking) {
        TwilioUtil.initTwilio();
        String acceptBookingMessage = informBookingMessage(booking);
        TwilioUtil.sendMessage(phone, acceptBookingMessage);
    }

    public void informEndBooking(String phone, Booking booking) {
        TwilioUtil.initTwilio();
        String acceptBookingMessage = informEndBookingMessage(booking);
        TwilioUtil.sendMessage(phone, acceptBookingMessage);
    }

    public void denyBooking(String phone, String bookingCode) {
        TwilioUtil.initTwilio();
        String denyBooking = denyBookingMessage(bookingCode);
        TwilioUtil.sendMessage(phone, denyBooking);
    }

    private String sendOTPMessage(String otp) {
        return messageBuilder().append("Mật khẩu đăng nhập của quí khách là: ")
                .append(otp).toString();
    }

    private String informBookingMessage(Booking booking) {
        String startTime = booking.bookingServices().stream()
                .sorted((o1, o2) -> o1.startAppointment().compareTo(o2.startAppointment()))
                .map(BookingService::startAppointment).findFirst().get()
                .format(DateTimeFormatter.ofPattern("hh:mm:ss"));
        return String.format(
                "Bạn vừa đặt lịch thành công với RealMen \n\nChúng mình có hẹn với nhau tại chi nhánh %s, địa chỉ %s \n\nvào lúc %s ngày %s \n\nRealMen mong sớm được phục vụ bạn",
                booking.branchName(), booking.branchAddress(), startTime,
                booking.appointmentDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

    private String denyBookingMessage(String bookingCode) {
        return messageBuilder()
                .append("Lịch hẹn làm tóc của bạn có mã")
                .append(" ")
                .append(bookingCode)
                .append(" ")
                .append("đã bị từ chối.")
                .append("Vui lòng kiểm tra thông tin trên app")
                .toString();
    }

    private String informEndBookingMessage(Booking booking) {

        return String.format("Bạn vừa hoàn thành đơn %s \n\nTổng hóa đơn là %s\n\nSố dịch vụ đã sử dụng %s",
                booking.bookingCode(), booking.totalBookingPrice(), booking.bookingServices().size());
    }

    private StringBuilder messageBuilder() {
        return new StringBuilder();
    }
}
