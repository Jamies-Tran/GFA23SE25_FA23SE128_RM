package com.realman.becore.controller.api.avatar.models;

import com.realman.becore.custom_constrain.text.NormalText;

import jakarta.validation.constraints.NotNull;

public record AvatarRequest(
    @NormalText String url,
    @NotNull Boolean isThumbnail
) {
    
}