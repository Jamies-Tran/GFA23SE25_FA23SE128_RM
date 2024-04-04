package com.realman.becore.dto.goong.geocoding;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AddressComponent(
    @JsonProperty("long_name") String longName,
    @JsonProperty("short_name") String shortName
) {
    
}
