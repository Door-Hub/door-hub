package org.example.doorhub.category;

import lombok.RequiredArgsConstructor;
import org.example.doorhub.category.dto.CategoryCreateDto;
import org.example.doorhub.category.dto.CategoryResponseDto;
import org.example.doorhub.category.dto.CategoryUpdateDto;
import org.example.doorhub.category.entity.Category;
import org.example.doorhub.common.service.GenericDtoMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryMapperDto extends GenericDtoMapper<Category, CategoryCreateDto, CategoryUpdateDto, CategoryResponseDto> {
    private final ModelMapper mapper;

    @Override
    public Category toEntity(CategoryCreateDto categoryCreateDto) {
        return mapper.map(categoryCreateDto, Category.class);
    }

    @Override
    public CategoryResponseDto toResponseDto(Category category) {
        return mapper.map(category, CategoryResponseDto.class);
    }

    @Override
    public void update(CategoryUpdateDto categoryUpdateDto, Category category) {
        mapper.map(categoryUpdateDto, category);
    }

    @Override
    public CategoryCreateDto toCreateDto(Category category) {
        return mapper.map(category, CategoryCreateDto.class);
    }
}
