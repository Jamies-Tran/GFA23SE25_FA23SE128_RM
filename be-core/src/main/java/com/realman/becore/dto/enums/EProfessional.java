package com.realman.becore.dto.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EProfessional {
    STYLIST(0),
    MASSEUR(1),
    RECEPTIONIST(2);

    public final Integer value;
}
