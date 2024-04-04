package com.realman.becore.controller.api.place.autocomplete.models;

import lombok.Builder;

@Builder
public record Prediction(
    String description
) {

}
