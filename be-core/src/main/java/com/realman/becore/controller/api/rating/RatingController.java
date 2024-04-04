package com.realman.becore.controller.api.rating;

import org.springframework.web.bind.annotation.RestController;

import com.realman.becore.controller.api.rating.models.RatingModelMapper;
import com.realman.becore.controller.api.rating.models.RatingRequest;
import com.realman.becore.dto.rating.Rating;
import com.realman.becore.service.rating.RatingUseCaseService;

import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RatingController implements RatingAPI {
    @NonNull
    private final RatingUseCaseService ratingUseCaseService;
    @NonNull
    private final RatingModelMapper ratingModelMapper;

    @Override
    public void update(Long ratingId, @Valid RatingRequest ratingRequest) {
        Rating rating = ratingModelMapper.toDto(ratingRequest);
        ratingUseCaseService.update(ratingId, rating);
    }

}
