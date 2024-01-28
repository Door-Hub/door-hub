package org.example.doorhub.address;

import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.doorhub.address.dto.AddressBaseDto;
import org.example.doorhub.address.dto.AddressResponseDto;
import org.example.doorhub.address.dto.AddressUpdateDto;
import org.example.doorhub.address.entity.Address;
import org.example.doorhub.common.service.GenericCrudService;
import org.example.doorhub.location.LocationService;
import org.example.doorhub.user.UserRepository;
import org.example.doorhub.user.entity.User;
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
    private final UserRepository userRepository;


    @Override
    protected Address save(AddressBaseDto addressCreateDto) {

        User user = userRepository.findById(addressCreateDto.getUserId()).orElseThrow(() -> new EntityNotFoundException("user not found"));

        Address address = mapper.toEntity(addressCreateDto);
        address.setUser(user);
        user.getAddresses().add(address);

        return repository.save(address);
    }


    public AddressResponseDto create(AddressBaseDto addressBaseDto) {

        User user = userRepository.findById(addressBaseDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("user not found"));

        Address address = save(addressBaseDto);

        AddressResponseDto responseDto = mapper.toResponseDto(address);
        responseDto.setUserId(user.getId());
        return responseDto;

    }


    @Override
    protected Address updateEntity(AddressUpdateDto addressUpdateDto, Address address) {
        mapper.update(addressUpdateDto, address);
        return repository.save(address);
    }

    public AddressResponseDto createAddressLocation(AddressBaseDto createDTo, String locationName) {

        User user = userRepository
                .findById(createDTo.getUserId()).orElseThrow(() -> new EntityNotFoundException("user id not found"));
        Address address = mapper.toEntity(createDTo);
        address.setLocationName(locationName);
        address.setUser(user);
        user.getAddresses().add(address);

        Address save = repository.save(address);

        AddressResponseDto responseDto = mapper.toResponseDto(save);
        responseDto.setUserId(user.getId());
        return responseDto;
    }

    public AddressResponseDto updateLocation(Integer id, String location) {

        Address address = repository.findById(id).orElseThrow();

        address.setLocationName(location);

        Address save = repository.save(address);

        AddressResponseDto responseDto = mapper.toResponseDto(save);
        responseDto.setUserId(address.getUser().getId());

        return responseDto;
    }

}
