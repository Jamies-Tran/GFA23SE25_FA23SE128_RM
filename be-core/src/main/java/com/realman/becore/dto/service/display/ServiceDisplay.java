package com.realman.becore.dto.service.display;

public record ServiceDisplay(
        Long serviceDisplayId,
        Long serviceId,
        String serviceDisplayUrl,
        String serviceDisplayBase64Url) {
}
