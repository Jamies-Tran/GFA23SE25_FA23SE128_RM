package com.realman.becore.controller.api.services.models;

import lombok.Builder;

@Builder
public record ServiceDisplayResponse(
        Long serviceDisplayId,
        String serviceDisplayUrl,
        String serviceDisplayBase64Url) {
}
