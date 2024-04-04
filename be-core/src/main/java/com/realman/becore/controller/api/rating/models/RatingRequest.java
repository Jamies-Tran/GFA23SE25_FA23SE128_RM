package com.realman.becore.controller.api.rating.models;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record RatingRequest(
                @NotNull(message = "Thiếu thông tin người được rating") Long staffId,
                @NotNull(message = "Thiếu thông tin dịch vụ để rating") Long bookingServiceId,
                @NotNull(message = "Thiếu điểm đánh giá") Double point,
                String feedback) {
}
