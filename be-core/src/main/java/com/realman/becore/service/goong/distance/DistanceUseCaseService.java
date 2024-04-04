package com.realman.becore.service.goong.distance;

import org.springframework.stereotype.Service;

import com.realman.becore.dto.goong.distance.DistanceRequest;
import com.realman.becore.dto.goong.distance.DistanceResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DistanceUseCaseService {
    @NonNull
    private final DistanceRequestService distanceRequestService;

    public DistanceResponse requestDistance(DistanceRequest distanceRequest) {
        return distanceRequestService.requestDistanceList(distanceRequest);
    }
}
