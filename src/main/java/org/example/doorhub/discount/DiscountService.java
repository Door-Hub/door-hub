package org.example.doorhub.discount;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
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

    @Override
    protected Discount save(DiscountCreateDto discountCreateDto) {
        Discount discount = mapper.toEntity(discountCreateDto);
        return repository.save(discount);

    }

    @Override
    protected Discount updateEntity(DiscountUpdateDto discountUpdateDto, Discount discount) {
        mapper.update(discountUpdateDto, discount);
        return repository.save(discount);
    }
}
