package org.example.doorhub.discount;

import lombok.RequiredArgsConstructor;
import org.example.doorhub.common.service.GenericDtoMapper;
import org.example.doorhub.discount.dto.DiscountCreateDto;
import org.example.doorhub.discount.dto.DiscountResponseDto;
import org.example.doorhub.discount.dto.DiscountUpdateDto;
import org.example.doorhub.discount.entity.Discount;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DiscountMapperDto extends GenericDtoMapper<Discount, DiscountCreateDto, DiscountUpdateDto, DiscountResponseDto> {

    private final ModelMapper mapper;

    @Override
    public Discount toEntity(DiscountCreateDto discountCreateDto) {
        return mapper.map(discountCreateDto, Discount.class);
    }

    @Override
    public DiscountResponseDto toResponseDto(Discount discount) {
        return mapper.map(discount, DiscountResponseDto.class);
    }

    @Override
    public void update(DiscountUpdateDto discountUpdateDto, Discount discount) {
        mapper.map(discountUpdateDto, discount);
    }

    @Override
    public DiscountCreateDto toCreateDto(Discount discount) {
        return mapper.map(discount, DiscountCreateDto.class);
    }
}
