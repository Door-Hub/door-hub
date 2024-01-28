package org.example.doorhub.address;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.doorhub.address.dto.AddressBaseDto;
import org.example.doorhub.address.dto.AddressResponseDto;
import org.example.doorhub.address.dto.AddressUpdateDto;
import org.example.doorhub.location.LocationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;
    private final LocationService locationService;


    @PostMapping
    public ResponseEntity<AddressResponseDto> createAddress(
            @RequestBody @Valid AddressBaseDto createDTo,
            @RequestParam(name = "lat", required = false) Double latitude,
            @RequestParam(name = "lon", required = false) Double longitude) {
        if (latitude != null || longitude != null) {
            String locationName = locationService.getLocationName(latitude, longitude);
            AddressResponseDto addressResponseDto = addressService.createAddressLocation(createDTo, locationName);
            return ResponseEntity.status(HttpStatus.CREATED).body(addressResponseDto);
        } else {
            AddressResponseDto addressResponseDto = addressService.create(createDTo);
            return ResponseEntity.status(HttpStatus.CREATED).body(addressResponseDto);
        }
    }


    @GetMapping
    public ResponseEntity<Page<AddressResponseDto>> getAllAddress(Pageable pageable, @RequestParam(required = false) String predicate) {
        Page<AddressResponseDto> all = addressService.getAll(pageable, predicate);
        return ResponseEntity.ok(all);
    }


    @GetMapping("/{id}")
    public ResponseEntity<AddressResponseDto> getAddress(@PathVariable Integer id) {
        AddressResponseDto responseDto = addressService.getById(id);
        return ResponseEntity.ok(responseDto);
    }


    @PutMapping("/{id}")
    public ResponseEntity<AddressResponseDto> updateAddress(@PathVariable Integer id, @RequestBody  AddressUpdateDto updateDto) {
        AddressResponseDto responseDto = addressService.update(id, updateDto);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("location/{id}")
    public ResponseEntity<AddressResponseDto> updateAddressLocation(@PathVariable Integer id,
                                                                    @RequestParam(name = "lat", required = false) Double latitude,
                                                                    @RequestParam(name = "lon", required = false) Double longitude) {
        String locationName = locationService.getLocationName(latitude, longitude);
        AddressResponseDto responseDto = addressService.updateLocation(id, locationName);
        return ResponseEntity.ok(responseDto);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<AddressResponseDto> patchAddress(@PathVariable Integer id, @RequestBody AddressUpdateDto patchDto) throws NoSuchFieldException, IllegalAccessException {
        AddressResponseDto responseDto = addressService.patch(id, patchDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable Integer id) {
        addressService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
