package com.realman.becore.service.rating;

import org.springframework.stereotype.Service;
import java.util.List;
import com.realman.becore.dto.rating.RatingMapper;
import com.realman.becore.repository.database.rating.RatingRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RatingQueryService {
    @NonNull
    private final RatingRepository ratingRepository;
    @NonNull
    private final RatingMapper ratingMapper;

    public Double averageRating(Long staffId) {
        List<Double> ratings = ratingRepository.averageRating(staffId);
        return ratings.stream().reduce(Double::sum).orElse(0.0) / ratings.size();
    }
}
