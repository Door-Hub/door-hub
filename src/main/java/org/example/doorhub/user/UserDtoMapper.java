package org.example.doorhub.user;


import lombok.RequiredArgsConstructor;
import org.example.doorhub.common.service.GenericDtoMapper;
import org.example.doorhub.user.dto.UserCreateDto;
import org.example.doorhub.user.dto.UserResponseDto;
import org.example.doorhub.user.dto.UserUpdateDto;
import org.example.doorhub.user.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDtoMapper extends GenericDtoMapper<User, UserCreateDto, UserUpdateDto, UserResponseDto> {

    private final ModelMapper mapper;

    @Override
    public User toEntity(UserCreateDto userCreateDto) {
        return mapper.map(userCreateDto, User.class);
    }

    @Override
    public UserResponseDto toResponseDto(User user) {
        return mapper.map(user, UserResponseDto.class);
    }

    @Override
    public void update(UserUpdateDto userUpdateDto, User user) {
        mapper.map(userUpdateDto, user);
    }

    @Override
    public UserCreateDto toCreateDto(User user) {
        return mapper.map(user, UserCreateDto.class);
    }
}
