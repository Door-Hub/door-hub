package org.example.doorhub.category;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.doorhub.category.dto.*;
import org.example.doorhub.category.entity.Category;
import org.example.doorhub.category.parent.ParentRepository;
import org.example.doorhub.category.parent.dto.ParentCategoryCreateDto;
import org.example.doorhub.category.parent.dto.ParentCategoryResponseDto;
import org.example.doorhub.category.parent.entity.ParentCategory;
import org.example.doorhub.common.service.GenericCrudService;
import org.example.doorhub.user.UserRepository;
import org.example.doorhub.user.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Getter
@RequiredArgsConstructor
public class CategoryService extends GenericCrudService<Category, Integer, CategoryCreateDto, CategoryUpdateDto, CategoryPatchDto, CategoryResponseDto> {

    private final CategoryRepository repository;
    private final CategoryMapperDto mapper;
    private final Class<Category> EntityClass = Category.class;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ParentRepository parentRepository;

    @Override
    @Transactional
    protected Category save(CategoryCreateDto categoryCreateDto) {

        Category category = mapper.toEntity(categoryCreateDto);
        return repository.save(category);
    }


    @Override
    @Transactional
    protected Category updateEntity(CategoryUpdateDto categoryUpdateDto, Category category) {
        mapper.update(categoryUpdateDto, category);
        return repository.save(category);
    }
}
