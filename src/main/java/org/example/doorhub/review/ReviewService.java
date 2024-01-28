package org.example.doorhub.review;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.doorhub.category.parent.ParentRepository;
import org.example.doorhub.category.parent.entity.ParentCategory;
import org.example.doorhub.review.dto.ReviewCreateDto;
import org.example.doorhub.review.dto.ReviewResponseDto;
import org.example.doorhub.review.entity.Review;
import org.example.doorhub.user.UserRepository;
import org.example.doorhub.user.entity.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapperDto mapperDto;
    private final UserRepository userRepository;
    private final ParentRepository parentRepository;


    public void delete(Integer id) {
        Review review = reviewRepository.findById(id).orElseThrow();
        reviewRepository.delete(review);
    }

    public ReviewResponseDto create(ReviewCreateDto createDto) {
        User user = userRepository.findById(createDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("user not found id"));
        ParentCategory parentCategory = parentRepository.findById(createDto.getParentCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("parentCategory not found id"));

        Review review = mapperDto.toEntity(createDto);

        review.setUser(user);
        user.getReviews().add(review);

        review.setParentCategory(parentCategory);
        parentCategory.getViews().add(review);

        Review saved = reviewRepository.save(review);

        ReviewResponseDto responseDto = mapperDto.toResponseDto(saved);
        responseDto.setUserId(user.getId());
        responseDto.setParentCategoryId(parentCategory.getId());
        return responseDto;
    }
}
