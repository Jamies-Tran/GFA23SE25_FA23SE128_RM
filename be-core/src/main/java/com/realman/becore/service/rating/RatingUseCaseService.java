package com.realman.becore.service.rating;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.realman.becore.dto.rating.Rating;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RatingUseCaseService {
    @NonNull
    private final RatingCommandService ratingCommandService;
    @NonNull
    private final RatingQueryService ratingQueryService;

    @Transactional
    public void save(Rating rating) {
        ratingCommandService.save(rating);
    }

    @Transactional
    public void update(Long ratingId, Rating rating) {
        ratingCommandService.update(ratingId, rating);
    }

    public Double averageRating(Long staffId) {
        return ratingQueryService.averageRating(staffId);
    }
}
