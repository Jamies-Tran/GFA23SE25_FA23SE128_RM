package com.realman.becore.controller.api.rating;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.realman.becore.controller.api.rating.models.RatingRequest;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Rating", description = "Rating API")
@RequestMapping("/v1/ratings")
public interface RatingsAPI {
    @PostMapping
    void save(@RequestBody @Valid RatingRequest ratingRequest);
}
