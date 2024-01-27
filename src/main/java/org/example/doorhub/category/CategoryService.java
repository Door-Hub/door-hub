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
    protected Category save(CategoryCreateDto categoryCreateDto) {

        ParentCategory parentCategory = parentRepository.findById(categoryCreateDto.getParentCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("category id not found"));

        Category category = mapper.toEntity(categoryCreateDto);
        category.getParentCategories().add(parentCategory);
        return repository.save(category);
    }


    @Override
    protected Category updateEntity(CategoryUpdateDto categoryUpdateDto, Category category) {

        Optional<User> user = userRepository.findById(categoryUpdateDto.getUserId());
        Optional<ParentCategory> parentCategory = parentRepository.findById(categoryUpdateDto.getParentCategoryId());

        if (user.isPresent() && parentCategory.isPresent()) {
            mapper.update(categoryUpdateDto, category);
            return repository.save(category);
        }
        throw new EntityNotFoundException("user or category id not found ");
    }
}
