package org.example.doorhub.review;

import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.doorhub.category.parent.ParentRepository;
import org.example.doorhub.category.parent.entity.ParentCategory;
import org.example.doorhub.common.exception.CustomCategoryNotFoundException;
import org.example.doorhub.common.repository.GenericSpecificationRepository;
import org.example.doorhub.common.service.GenericCrudService;
import org.example.doorhub.common.service.GenericDtoMapper;
import org.example.doorhub.review.dto.ReviewCreateDto;
import org.example.doorhub.review.dto.ReviewPathDto;
import org.example.doorhub.review.dto.ReviewResponseDto;
import org.example.doorhub.review.dto.ReviewUpdateDto;
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
@Getter
public class ReviewService extends GenericCrudService<Review, Integer, ReviewCreateDto, ReviewUpdateDto, ReviewPathDto, ReviewResponseDto> {

    private final ReviewRepository repository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ReviewMapperDto mapper;
    private final Class<Review> EntityClass = Review.class;
    private final ParentRepository parentRepository;

    public ReviewResponseDto createReview(ReviewCreateDto createDto) {

        User user = userRepository.findById(createDto.getUserId()).orElseThrow(EntityNotFoundException::new);

        ParentCategory parentCategory = parentRepository.findById(createDto.getParentCategoryId()).orElseThrow(
                () -> new CustomCategoryNotFoundException("Category not found")
        );

        createDto.setUserId(user.getId());
        createDto.setParentCategoryId(parentCategory.getId());
        createDto.setStars(createDto.getStars() + 1);

        Review review = modelMapper.map(createDto, Review.class);

        repository.save(review);

        parentCategory.getViews().add(review);

        parentRepository.save(parentCategory);

        return modelMapper.map(createDto, ReviewResponseDto.class);

    }


    @Override
    protected Review save(ReviewCreateDto reviewCreateDto) {
        Review entity = mapper.toEntity(reviewCreateDto);
        return repository.save(entity);
    }

    @Override
    protected Review updateEntity(ReviewUpdateDto reviewUpdateDto, Review review) {
        mapper.update(reviewUpdateDto, review);
        return repository.save(review);

    }


    public List<ReviewResponseDto> getAll(Integer id) {

        ParentCategory category = parentRepository.findById(id)
                .orElseThrow(() -> new CustomCategoryNotFoundException("Category not found"));
        List<Review> views = category.getViews();

        List<ReviewResponseDto> reviewList = new ArrayList<>();
        ReviewResponseDto reviewResponseDto = new ReviewResponseDto();


        return reviewList;
    }
}
