package com.realman.becore.util;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class TwilioUtil {
    public static void initTwilio() {
        Twilio.init(TwilioProperties.ACCOUNT_SID, TwilioProperties.AUTH_TOKEN);
    }

    public static void sendMessage(String phone, String message) {
        phone = "+84" + phone.substring(1);
        Message.creator(new PhoneNumber(phone), new PhoneNumber(TwilioProperties.TWILIO_PHONE_NUMBER),
                message).create();
    }
}
