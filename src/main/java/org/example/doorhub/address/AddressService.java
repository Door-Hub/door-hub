package org.example.doorhub.address;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.doorhub.address.dto.AddressCreateDto;
import org.example.doorhub.address.dto.AddressPatchDto;
import org.example.doorhub.address.dto.AddressResponseDto;
import org.example.doorhub.address.dto.AddressUpdateDto;
import org.example.doorhub.address.entity.Address;
import org.example.doorhub.common.service.GenericCrudService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
public class AddressService extends GenericCrudService<Address,Integer, AddressCreateDto, AddressUpdateDto, AddressPatchDto, AddressResponseDto> {

    private final AddressRepository repository;
    private final AddressDtoMapper mapper;
    private final Class<Address> entityClass = Address.class;


    @Override
    protected Address save(AddressCreateDto addressCreateDto)
    {
        Address address = mapper.toEntity(addressCreateDto);
        return repository.save(address);
    }

    @Override
    protected Address updateEntity(AddressUpdateDto addressUpdateDto, Address address)
    {
        mapper.update(addressUpdateDto,address);
        return repository.save(address);
    }
}
