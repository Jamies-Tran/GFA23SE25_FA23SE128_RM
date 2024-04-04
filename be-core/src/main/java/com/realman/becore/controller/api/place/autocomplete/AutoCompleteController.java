package com.realman.becore.controller.api.place.autocomplete;

import org.springframework.web.bind.annotation.RestController;

import com.realman.becore.controller.api.place.autocomplete.models.AutoCompleteRequest;
import com.realman.becore.controller.api.place.autocomplete.models.AutoCompleteResponse;
import com.realman.becore.service.goong.place.autocomplete.AutoCompleteUseCaseService;
import com.realman.becore.util.response.ValueResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AutoCompleteController implements AutoCompleteAPI {

    @NonNull
    private AutoCompleteUseCaseService autoCompleteUseCaseService;

    @Override
    public ValueResponse<AutoCompleteResponse> requestPlaces(String address,
            Integer limit, Integer radius, Boolean moreCompound) {
        AutoCompleteRequest request = AutoCompleteRequest.builder()
                .input(address)
                .limit(limit)
                .radius(radius)
                .moreCompound(moreCompound)
                .build();
        AutoCompleteResponse response = autoCompleteUseCaseService.requestPlaces(request);
        return new ValueResponse<AutoCompleteResponse>(response);
    }

}
