package org.example.doorhub.address;

import lombok.RequiredArgsConstructor;
import org.example.doorhub.address.dto.AddressBaseDto;
import org.example.doorhub.address.dto.AddressResponseDto;
import org.example.doorhub.address.dto.AddressUpdateDto;
import org.example.doorhub.address.entity.Address;
import org.example.doorhub.common.service.GenericDtoMapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressDtoMapper extends GenericDtoMapper<Address, AddressBaseDto, AddressUpdateDto, AddressResponseDto> {
    private final ModelMapper mapper;

    @Override
    public Address toEntity(AddressBaseDto addressCreateDto) {
        return mapper.map(addressCreateDto, Address.class);
    }

    @Override
    public AddressResponseDto toResponseDto(Address address) {
        return mapper.map(address, AddressResponseDto.class);
    }

    @Override
    public void update(AddressUpdateDto addressUpdateDto, Address address) {
        mapper.map(addressUpdateDto, address);
    }

    @Override
    public AddressBaseDto toCreateDto(Address address) {
        return mapper.map(address, AddressBaseDto.class);
    }
}
