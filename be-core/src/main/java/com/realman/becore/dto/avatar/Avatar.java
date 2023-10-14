package com.realman.becore.dto.avatar;

import lombok.Builder;

@Builder
public record Avatar(
        Long avatarId,
        Long accountId,
        Boolean isThumbnail,
        String url) {

}
