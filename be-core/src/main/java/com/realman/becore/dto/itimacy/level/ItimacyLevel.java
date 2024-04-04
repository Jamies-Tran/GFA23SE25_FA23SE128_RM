package com.realman.becore.dto.itimacy.level;

import com.realman.becore.dto.enums.EItimacyLevel;

public record ItimacyLevel(
        Long itimacyId,
        Long requirePoint,
        EItimacyLevel itimacyLevel,
        Integer percentDiscount) {

}
