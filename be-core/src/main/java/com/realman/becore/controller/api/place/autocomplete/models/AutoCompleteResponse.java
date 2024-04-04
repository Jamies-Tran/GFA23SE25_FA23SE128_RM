package com.realman.becore.controller.api.place.autocomplete.models;

import java.util.List;

import lombok.Builder;

@Builder
public record AutoCompleteResponse(
    List<Prediction> predictions
) {
    
}
