package com.realman.becore.service.goong.place.autocomplete;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;

import com.realman.becore.controller.api.place.autocomplete.models.AutoCompleteRequest;
import com.realman.becore.controller.api.place.autocomplete.models.AutoCompleteResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AutoCompleteRequestService {

    @Value("${goong.api.key}")
    private String apiKey;

    @Value("${goong.place.autocomplete}")
    private String autoCompleteRequestUrl;

    @NonNull
    private final RestTemplate restTemplate;

    public AutoCompleteResponse requestPlaces(AutoCompleteRequest request) {
        StringBuilder requestBuilder = new StringBuilder(autoCompleteRequestUrl);
        String sessionToken = RequestContextHolder.getRequestAttributes().getSessionId();
        String requestUrl = requestBuilder.append("?api_key=").append(apiKey)
                .append("&input=").append(request.input())
                .append("&limit=").append(request.limit())
                .append("&radius=").append(request.radius())
                .append("&sessionToken=").append(sessionToken)
                .append("&more_compound=").append(request.moreCompound())
                .toString();
        AutoCompleteResponse response = restTemplate
                .getForObject(requestUrl, AutoCompleteResponse.class);

        return response;
    }

}
