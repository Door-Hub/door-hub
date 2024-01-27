package org.example.doorhub.category;

import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.doorhub.category.dto.CategoryCreateDto;
import org.example.doorhub.category.dto.CategoryPatchDto;
import org.example.doorhub.category.dto.CategoryResponseDto;
import org.example.doorhub.category.dto.CategoryUpdateDto;
import org.example.doorhub.category.entity.Category;
import org.example.doorhub.common.service.GenericCrudService;
import org.example.doorhub.user.UserRepository;
import org.example.doorhub.user.entity.User;
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

    @Override
    protected Category save(CategoryCreateDto categoryCreateDto) {
        User user = userRepository.findById(categoryCreateDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("user id not found"));

        Category parentCategory = repository.findById(categoryCreateDto.getParentCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("category id not found"));

        Category category = mapper.toEntity(categoryCreateDto);
        category.setUser(user);
        category.setParentCategory(parentCategory);
        return repository.save(category);
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
