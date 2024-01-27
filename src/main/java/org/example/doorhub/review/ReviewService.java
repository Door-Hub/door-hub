package org.example.doorhub.review;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.doorhub.category.parent.ParentRepository;
import org.example.doorhub.category.parent.entity.ParentCategory;
import org.example.doorhub.common.exception.CustomCategoryNotFoundException;
import org.example.doorhub.review.dto.ReviewCreateDto;
import org.example.doorhub.review.dto.ReviewResponseDto;
import org.example.doorhub.review.entity.Review;
import org.example.doorhub.user.UserRepository;
import org.example.doorhub.user.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final ParentRepository parentRepository;

    public ReviewResponseDto create(Principal userId, ReviewCreateDto createDto) {

        User user = userRepository.findById(createDto.getUserId()).orElseThrow(EntityNotFoundException::new);

        ParentCategory parentCategory = parentRepository.findById(createDto.getCategoryId()).orElseThrow(
                () -> new CustomCategoryNotFoundException("Category not found")
        );

        createDto.setUserId(user.getId());
        createDto.setCategoryId(parentCategory.getId());
        createDto.setStars(createDto.getStars() + 1);

        Review review = mapper.map(createDto, Review.class);

        reviewRepository.save(review);

        parentCategory.setStars(createDto.getStars());

        parentRepository.save(parentCategory);

        return mapper.map(createDto, ReviewResponseDto.class);

    }

    public List<ReviewResponseDto> getAll(Integer id) {

        ParentCategory category = parentRepository.findById(id)
                .orElseThrow(() -> new CustomCategoryNotFoundException("Category not found"));

        Integer stars = category.getStars();

        List<ReviewResponseDto> reviewList = new ArrayList<>();
        ReviewResponseDto reviewResponseDto = new ReviewResponseDto();


        reviewResponseDto.setStars(stars);

        return reviewList;
    }
}
