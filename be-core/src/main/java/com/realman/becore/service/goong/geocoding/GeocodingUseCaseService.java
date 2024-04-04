package com.realman.becore.service.goong.geocoding;

import org.springframework.stereotype.Service;

import com.realman.becore.dto.goong.geocoding.GeometryResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GeocodingUseCaseService {
    @NonNull
    private final GeocodingRequestService geocodingRequestService;

    public GeometryResponse requestGeocoding(String address) {
        return geocodingRequestService.requestGeometry(address);
    }
}
