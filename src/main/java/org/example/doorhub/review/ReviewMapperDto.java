package org.example.doorhub.review;

import lombok.RequiredArgsConstructor;
import org.example.doorhub.common.service.GenericDtoMapper;
import org.example.doorhub.review.dto.ReviewCreateDto;
import org.example.doorhub.review.dto.ReviewResponseDto;
import org.example.doorhub.review.entity.Review;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ReviewMapperDto extends GenericDtoMapper<Review, ReviewCreateDto, ReviewCreateDto, ReviewResponseDto> {

    private final ModelMapper modelMapper;

    @Override
    public Review toEntity(ReviewCreateDto reviewCreateDto) {
        return modelMapper.map(reviewCreateDto, Review.class);
    }

    @Override
    public ReviewResponseDto toResponseDto(Review review) {
        return modelMapper.map(review, ReviewResponseDto.class);
    }

    @Override
    public void update(ReviewCreateDto reviewUpdateDto, Review review) {
        modelMapper.map(reviewUpdateDto, review);
    }

    @Override
    public ReviewCreateDto toCreateDto(Review review) {
        return modelMapper.map(review, ReviewCreateDto.class);
    }
}
