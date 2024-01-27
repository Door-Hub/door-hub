package org.example.doorhub.category;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.doorhub.category.dto.*;
import org.example.doorhub.category.entity.Category;
import org.example.doorhub.common.service.GenericCrudService;
import org.example.doorhub.user.UserRepository;
import org.example.doorhub.user.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.ContextResource;
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

    @Override
    protected Category save(CategoryCreateDto categoryCreateDto) {

        Category parentCategory = repository.findById(categoryCreateDto.getParentCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("category id not found"));

        Category category = mapper.toEntity(categoryCreateDto);
        category.setParentCategory(parentCategory);
        return repository.save(category);
    }

    @Transactional
    public ParentCategoryResponseDto createParentCategory(ParentCategoryCreateDto parentCategoryCreateDto) {

        User user = userRepository.findById(parentCategoryCreateDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("user id not found"));

        Category category = modelMapper.map(parentCategoryCreateDto, Category.class);

        category.setUser(user);
        Category save = repository.save(category);

        return modelMapper.map(save, ParentCategoryResponseDto.class);
    }

    @Override
    protected Category updateEntity(CategoryUpdateDto categoryUpdateDto, Category category) {

        Optional<User> user = userRepository.findById(categoryUpdateDto.getUserId());

        Optional<Category> parentCategory = repository.findById(categoryUpdateDto.getParentCategoryId());
        if (user.isPresent() && parentCategory.isPresent()) {
            mapper.update(categoryUpdateDto, category);
            return repository.save(category);
        }
        throw new EntityNotFoundException("user or category id not found ");
    }
}
