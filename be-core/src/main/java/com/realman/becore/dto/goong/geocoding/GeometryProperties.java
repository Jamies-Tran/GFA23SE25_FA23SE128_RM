package com.realman.becore.dto.goong.geocoding;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GeometryProperties(
    @JsonProperty("address_components") List<AddressComponent> addressComponents,
    Geometry geometry
) {
    
}
