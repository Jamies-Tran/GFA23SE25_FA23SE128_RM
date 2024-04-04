package com.realman.becore.controller.api.place.autocomplete.models;

import lombok.Builder;

@Builder
public record AutoCompleteRequest(
    String input,
    Integer limit,
    Integer radius,
    Boolean moreCompound
) {
    
}
