package com.realman.becore.controller.api.rating;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.realman.becore.controller.api.rating.models.RatingRequest;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Rating", description = "Rating API")
@RequestMapping("/v1/ratings/{ratingId}")
public interface RatingAPI {
    @PutMapping
    void update(@PathVariable Long ratingId, @RequestBody @Valid RatingRequest ratingRequest);
}
