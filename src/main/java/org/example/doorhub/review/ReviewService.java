package org.example.doorhub.review;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.doorhub.category.CategoryRepository;
import org.example.doorhub.category.entity.Category;
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
    private final CategoryRepository categoryRepository;

    public ReviewResponseDto create(Principal userId, ReviewCreateDto createDto) {

        User user = userRepository.findById(createDto.getUserId()).orElseThrow(EntityNotFoundException::new);

        Category category = categoryRepository.findById(createDto.getCategoryId()).orElseThrow(
                () -> new CustomCategoryNotFoundException("Category not found")
        );

        createDto.setUserId(user.getId());
        createDto.setCategoryId(category.getId());
        createDto.setStars(createDto.getStars() + 1);

        Review review = mapper.map(createDto, Review.class);

        reviewRepository.save(review);

        category.setStars(createDto.getStars());

        Category category1 = mapper.map(category, Category.class);

        categoryRepository.save(category1);

        return mapper.map(createDto, ReviewResponseDto.class);

    }

    public List<ReviewResponseDto> getAll(Integer id) {

        Category category = categoryRepository.findById(id).orElseThrow(() -> new CustomCategoryNotFoundException("Category not found"));

        Integer stars = category.getStars();

        List<ReviewResponseDto> reviewList = new ArrayList<>();
        ReviewResponseDto reviewResponseDto = new ReviewResponseDto();


        reviewResponseDto.setStars(stars);

        return reviewList;
    }
}
