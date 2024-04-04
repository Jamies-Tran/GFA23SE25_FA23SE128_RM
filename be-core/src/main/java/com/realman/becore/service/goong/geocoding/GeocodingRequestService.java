package com.realman.becore.service.goong.geocoding;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.realman.becore.dto.goong.geocoding.GeometryResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GeocodingRequestService {
    @Value("${goong.api.key}")
    private String apiKey;
    @Value("${goong.geometry}")
    private String geometryUrl;
    @NonNull
    private final RestTemplate restTemplate;

    public GeometryResponse requestGeometry(String adress) {
        StringBuilder urlBuilder = new StringBuilder(geometryUrl);
        String requestUrl = urlBuilder
                .append("?api_key=").append(apiKey)
                .append("&address=").append(adress)
                .toString();
        GeometryResponse response = restTemplate.getForObject(requestUrl, GeometryResponse.class);
        return response;
    }
}
