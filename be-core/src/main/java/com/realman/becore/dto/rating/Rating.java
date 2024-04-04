package com.realman.becore.dto.rating;

public record Rating(
                Long staffId,
                Long customerId,
                Long bookingServiceId,
                String feedback,
                Double point) {

}
