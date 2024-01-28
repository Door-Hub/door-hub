package org.example.doorhub.review;


import lombok.RequiredArgsConstructor;
import org.example.doorhub.review.entity.Review;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public void delete(Integer id) {
        Review review = reviewRepository.findById(id).orElseThrow();
        reviewRepository.delete(review);
    }
}
