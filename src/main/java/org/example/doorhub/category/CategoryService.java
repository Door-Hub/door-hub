package org.example.doorhub.category;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.doorhub.category.dto.CategoryCreateDto;
import org.example.doorhub.category.dto.CategoryPatchDto;
import org.example.doorhub.category.dto.CategoryResponseDto;
import org.example.doorhub.category.dto.CategoryUpdateDto;
import org.example.doorhub.category.entity.Category;
import org.example.doorhub.common.service.GenericCrudService;
import org.springframework.stereotype.Service;

@Service
@Getter
@RequiredArgsConstructor
public class CategoryService extends GenericCrudService<Category, Integer, CategoryCreateDto, CategoryUpdateDto, CategoryPatchDto, CategoryResponseDto> {

    private final CategoryRepository repository;
    private final CategoryMapperDto mapper;
    private final Class<Category> EntityClass = Category.class;

    @Override
    protected Category save(CategoryCreateDto categoryCreateDto) {
        Category category = mapper.toEntity(categoryCreateDto);
        return repository.save(category);
    }

    @Override
    protected Category updateEntity(CategoryUpdateDto categoryUpdateDto, Category category) {
        mapper.update(categoryUpdateDto, category);
        return repository.save(category);
    }
}
