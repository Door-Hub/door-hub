package org.example.doorhub.address;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.doorhub.address.dto.AddressCreateDto;
import org.example.doorhub.address.dto.AddressPatchDto;
import org.example.doorhub.address.dto.AddressResponseDto;
import org.example.doorhub.address.dto.AddressUpdateDto;
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


    @PostMapping
    public ResponseEntity<AddressResponseDto> createAddress( @RequestBody @Valid AddressCreateDto createDTo )
    {
        AddressResponseDto addressResponseDto = addressService.create( createDTo );
        return ResponseEntity.status( HttpStatus.CREATED ).body( addressResponseDto );
    }


    @GetMapping
    public ResponseEntity<Page<AddressResponseDto>> getAllAddress(Pageable pageable, @RequestParam(required = false) String predicate)
    {
        Page<AddressResponseDto> all = addressService.getAll(pageable, predicate);
        return ResponseEntity.ok(all);
    }


    @GetMapping("/{id}")
    public ResponseEntity<AddressResponseDto> getAddress(@PathVariable Integer id)
    {
        AddressResponseDto responseDto = addressService.getById(id);
        return ResponseEntity.ok(responseDto);
    }


    @PutMapping("/{id}")
    public ResponseEntity<AddressResponseDto> updateAddress(@PathVariable Integer id, @RequestBody @Valid AddressUpdateDto updateDto)
    {
        AddressResponseDto responseDto = addressService.update(id, updateDto);
        return ResponseEntity.ok(responseDto);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<AddressResponseDto> patchAddress(@PathVariable Integer id, @RequestBody AddressPatchDto patchDto) throws NoSuchFieldException, IllegalAccessException
    {
        AddressResponseDto responseDto = addressService.patch(id, patchDto);
        return ResponseEntity.ok(responseDto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable Integer id)
    {
        addressService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
