package org.example.doorhub.profile;

import lombok.RequiredArgsConstructor;
import org.example.doorhub.common.service.GenericDtoMapper;
import org.example.doorhub.profile.dto.ProfileCreateDto;
import org.example.doorhub.profile.dto.ProfilePatchDto;
import org.example.doorhub.profile.dto.ProfileResponseDto;
import org.example.doorhub.profile.entity.Profile;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfileDtoMapper extends GenericDtoMapper<Profile, ProfileCreateDto, ProfilePatchDto, ProfileResponseDto> {

    private final ModelMapper mapper;

    @Override
    public Profile toEntity(ProfileCreateDto profileCreateDto) {
        return mapper.map(profileCreateDto, Profile.class);
    }

    @Override
    public ProfileResponseDto toResponseDto(Profile profile) {
        return mapper.map(profile, ProfileResponseDto.class);
    }

    @Override
    public void update(ProfilePatchDto profilePatchDto, Profile profile) {
        mapper.map(profile, profile);

    }

    @Override
    public ProfileCreateDto toCreateDto(Profile profile) {
        return mapper.map(profile, ProfileCreateDto.class);
    }
}
