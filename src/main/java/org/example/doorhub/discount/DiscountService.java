package org.example.doorhub.discount;


import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.doorhub.category.parent.ParentRepository;
import org.example.doorhub.category.parent.entity.ParentCategory;
import org.example.doorhub.common.service.GenericCrudService;
import org.example.doorhub.discount.dto.DiscountCreateDto;
import org.example.doorhub.discount.dto.DiscountPatchDto;
import org.example.doorhub.discount.dto.DiscountResponseDto;
import org.example.doorhub.discount.dto.DiscountUpdateDto;
import org.example.doorhub.discount.entity.Discount;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
public class DiscountService extends GenericCrudService<Discount, Integer, DiscountCreateDto, DiscountUpdateDto, DiscountPatchDto, DiscountResponseDto> {

    private final DiscountRepository repository;
    private final DiscountMapperDto mapper;
    private final Class<Discount> EntityClass = Discount.class;
    private final ParentRepository parentRepository;

    @Override
    protected Discount save(DiscountCreateDto discountCreateDto) {

        Discount discount = mapper.toEntity(discountCreateDto);

        ParentCategory category = parentRepository.findById(discountCreateDto.getParentCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("category not found"));

        discount.setParentCategory(category);
        category.getDiscounts().add(discount);

        return repository.save(discount);

    }

    public DiscountResponseDto create(DiscountCreateDto createDto) {
        ParentCategory category = parentRepository.findById(createDto.getParentCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("category not found"));

        Discount discount = save(createDto);
        DiscountResponseDto responseDto = mapper.toResponseDto(discount);
        responseDto.setParentCategoryId(category.getId());
        return responseDto;

    }

    @Override
    protected Discount updateEntity(DiscountUpdateDto discountUpdateDto, Discount discount) {

        mapper.update(discountUpdateDto, discount);
        return repository.save(discount);
    }

    public DiscountResponseDto update(Integer id, DiscountUpdateDto updateDto) {

        Discount discount = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("discount id not found"));

        ParentCategory parentCategory = parentRepository.findById(updateDto.getParentCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("parentCategory id not found"));

        Discount updateEntity = updateEntity(updateDto, discount);
        DiscountResponseDto responseDto = mapper.toResponseDto(updateEntity);
        responseDto.setParentCategoryId(parentCategory.getId());
        return responseDto;
    }
}
