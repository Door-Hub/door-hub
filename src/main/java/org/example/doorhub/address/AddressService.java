package org.example.doorhub.address;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.doorhub.address.dto.AddressBaseDto;
import org.example.doorhub.address.dto.AddressResponseDto;
import org.example.doorhub.address.dto.AddressUpdateDto;
import org.example.doorhub.address.entity.Address;
import org.example.doorhub.common.service.GenericCrudService;
import org.example.doorhub.location.LocationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
public class AddressService extends GenericCrudService<Address, Integer, AddressBaseDto, AddressUpdateDto, AddressUpdateDto, AddressResponseDto> {

    private final AddressRepository repository;
    private final AddressDtoMapper mapper;
    private final Class<Address> entityClass = Address.class;
    private final LocationService locationService;
    private final ModelMapper modelMapper;


    @Override
    protected Address save(AddressBaseDto addressCreateDto) {

        Address address = mapper.toEntity(addressCreateDto);
        return repository.save(address);
    }


    @Override
    protected Address updateEntity(AddressUpdateDto addressUpdateDto, Address address) {
        mapper.update(addressUpdateDto, address);
        return repository.save(address);
    }

    public AddressResponseDto create(AddressBaseDto createDTo, String locationName) {
        Address address = mapper.toEntity(createDTo);
        address.setLocationName(locationName);

        Address save = repository.save(address);

        return mapper.toResponseDto(save);
    }

    public AddressResponseDto updateLocation(Integer id, String location) {
        Address address = repository.findById(id).orElseThrow();

        address.setLocationName(location);

        Address save = repository.save(address);

        return mapper.toResponseDto(save);


    }
}
