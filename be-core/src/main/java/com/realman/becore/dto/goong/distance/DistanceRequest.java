package com.realman.becore.dto.goong.distance;
import lombok.Builder;

@Builder
public record DistanceRequest(
                String origin,
                String destination) {

        public static DistanceRequest of(Double originLat, Double originLng,
                        Double desLat, Double desLng) {
                StringBuilder originBuilder = new StringBuilder(originLat.toString());
                StringBuilder destinationBuilder = new StringBuilder(desLat.toString());
                originBuilder.append(",").append(originLng.toString());
                destinationBuilder.append(",").append(desLng.toString());
                return DistanceRequest.builder().origin(originBuilder.toString())
                                .destination(destinationBuilder.toString()).build();
        }

}
