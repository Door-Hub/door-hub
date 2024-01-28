package org.example.doorhub.category.parent;

import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.doorhub.category.CategoryRepository;
import org.example.doorhub.category.entity.Category;
import org.example.doorhub.category.parent.dto.ParentCategoryCreateDto;
import org.example.doorhub.category.parent.dto.ParentCategoryPathDto;
import org.example.doorhub.category.parent.dto.ParentCategoryResponseDto;
import org.example.doorhub.category.parent.dto.ParentCategoryUpdateDto;
import org.example.doorhub.category.parent.entity.ParentCategory;
import org.example.doorhub.common.service.GenericCrudService;
import org.example.doorhub.review.ReviewRepository;
import org.example.doorhub.review.entity.Review;
import org.example.doorhub.user.UserRepository;
import org.example.doorhub.user.entity.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
public class ParentService extends GenericCrudService<ParentCategory, Integer, ParentCategoryCreateDto, ParentCategoryUpdateDto, ParentCategoryPathDto, ParentCategoryResponseDto> {


    private final ParentRepository repository;
    private final ParentMapperDto mapper;
    private final Class<ParentCategory> EntityClass = ParentCategory.class;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ReviewRepository reviewRepository;

    @Override
    protected ParentCategory save(ParentCategoryCreateDto parentCategoryCreateDto) {

        User user = userRepository.findById(parentCategoryCreateDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("user id not found"));

        Category category = categoryRepository.findById(parentCategoryCreateDto.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("category id not found"));


        ParentCategory parentCategory = mapper.toEntity(parentCategoryCreateDto);

        parentCategory.setUser(user);
        user.getCategories().add(parentCategory);

        parentCategory.setCategory(category);
        category.getParents().add(parentCategory);
        return repository.save(parentCategory);
    }

    @Override
    protected ParentCategory updateEntity(ParentCategoryUpdateDto parentCategoryUpdateDto, ParentCategory parentCategory) {
        mapper.update(parentCategoryUpdateDto, parentCategory);
        return repository.save(parentCategory);

    }
}
