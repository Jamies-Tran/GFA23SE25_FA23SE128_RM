package com.realman.becore.controller.api.services.models;

import lombok.Builder;

@Builder
public record ServiceDisplayRequest(
    String serviceDisplayUrl
) {
}