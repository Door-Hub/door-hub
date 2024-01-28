package org.example.doorhub.profile;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.example.doorhub.common.service.GenericCrudService;
import org.example.doorhub.profile.dto.ProfileCreateDto;
import org.example.doorhub.profile.dto.ProfilePatchDto;
import org.example.doorhub.profile.dto.ProfileResponseDto;
import org.example.doorhub.profile.dto.ProfileUpdateDto;
import org.example.doorhub.profile.entity.Profile;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
public class ProfileService extends GenericCrudService<Profile, Integer, ProfileCreateDto, ProfilePatchDto,ProfileUpdateDto,  ProfileResponseDto> {

    private final ProfileRepository repository;
    private final ProfileDtoMapper mapper;
    private final Class<Profile> entityClass = Profile.class;
    private final ModelMapper modelMapper;



    @Override
    protected Profile save(ProfileCreateDto profileCreateDto) {
        Profile profile = mapper.toEntity(profileCreateDto);
        return repository.save(profile);
    }

    @Override
    protected Profile updateEntity(ProfilePatchDto profilePatchDto, Profile profile) {
        mapper.update(profilePatchDto, profile);
        return repository.save(profile);
    }


}
