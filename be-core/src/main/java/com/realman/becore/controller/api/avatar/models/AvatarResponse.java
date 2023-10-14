package com.realman.becore.controller.api.avatar.models;

public record AvatarResponse(
    Long accountId,
    Long avatarId,
    String url,
    Boolean isThumbnail
) {
    
}
