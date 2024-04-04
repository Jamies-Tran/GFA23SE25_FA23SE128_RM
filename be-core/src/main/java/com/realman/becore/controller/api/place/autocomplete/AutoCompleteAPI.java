package com.realman.becore.controller.api.place.autocomplete;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.realman.becore.controller.api.place.autocomplete.models.AutoCompleteResponse;
import com.realman.becore.util.response.ValueResponse;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Autocomplete", description = "request autocomplete from goong")
@RequestMapping("/v1/autocomplete")
public interface AutoCompleteAPI {

    @GetMapping
    ValueResponse<AutoCompleteResponse> requestPlaces(
        @RequestParam(required = true, value = "address", defaultValue = "") String address,
        @RequestParam(required = false, value = "limit", defaultValue = "10") Integer limit,
        @RequestParam(required = false, value = "radius", defaultValue = "1000") Integer radius,
        @RequestParam(required = false, value = "moreCompound", defaultValue = "true") Boolean moreCompound
    );
}
