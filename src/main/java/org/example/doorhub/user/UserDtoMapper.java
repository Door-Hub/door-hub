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
        User mapped = mapper.map(userCreateDto, User.class);
        System.out.println("mapped = " + mapped);
        return mapped;
    }

    @Override
    public UserResponseDto toResponseDto(User user) {
        UserResponseDto mapped = mapper.map(user, UserResponseDto.class);
        System.out.println("mapped = " + mapped);
        return mapped;
    }

    @Override
    public void update(UserUpdateDto userUpdateDto, User user) {
         mapper.map(userUpdateDto,user);
    }
}
