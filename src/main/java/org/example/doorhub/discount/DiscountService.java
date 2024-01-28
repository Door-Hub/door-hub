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

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Getter
public class DiscountService extends GenericCrudService<Discount, Integer, DiscountCreateDto, DiscountUpdateDto, DiscountPatchDto, DiscountResponseDto> {

    //private final DiscountRepository repository;
    private final DiscountRepository repository;
    private final DiscountMapperDto mapper;
    private final Class<Discount> EntityClass = Discount.class;
    private final ParentRepository parentRepository;

    @Override
    protected Discount save(DiscountCreateDto discountCreateDto) {
        Discount discount = mapper.toEntity(discountCreateDto);
        ParentCategory parentCategory = parentRepository.findById(discountCreateDto.getParentCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("category not found"));
        discount.setParentCategory(parentCategory);
        return repository.save(discount);

    }

    @Override
    protected Discount updateEntity(DiscountUpdateDto discountUpdateDto, Discount discount) {

        Optional<ParentCategory> parent = parentRepository.findById(discountUpdateDto.getParentCategoryId());

        if (parent.isPresent()) {
            mapper.update(discountUpdateDto, discount);
            return repository.save(discount);
        }

        throw new EntityNotFoundException("category id not found");
    }
}
