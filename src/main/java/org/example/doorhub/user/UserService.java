package org.example.doorhub.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.doorhub.common.exception.PhoneNumberNotVerifiedException;
import org.example.doorhub.common.service.GenericCrudService;
import org.example.doorhub.jwt.JwtService;
import org.example.doorhub.user.dto.*;
import org.example.doorhub.user.entity.User;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Getter
@RequiredArgsConstructor
public class UserService extends GenericCrudService<User, Integer, UserCreateDto, UserUpdateDto, UserPatchDto, UserResponseDto>
        implements UserDetailsService {

    private final UserRepository repository;
    private final UserDtoMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final Class<User> entityClass = User.class;


    @Override
    protected User save(UserCreateDto userCreateDto) {
        User user = mapper.toEntity(userCreateDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return repository.save(user);
    }

    @Override
    protected User updateEntity(UserUpdateDto userUpdateDto, User user) {
        mapper.update(userUpdateDto,user);
        return repository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
       return repository.findUserByPhoneNumber(phone).orElseThrow(() -> new BadCredentialsException("bad credentials"));
    }

    public UserSignInResponseDto signIn(UserSignInDto signInDto) {
        String phoneNumber = signInDto.getPhoneNumber();

        User user = repository
                .findUserByPhoneNumber(phoneNumber)
                .orElseThrow(() ->
                        new BadCredentialsException("Username or password doesn't match"));

        if (passwordEncoder.matches(signInDto.getPassword(), user.getPassword())) {
            if (user.isPhoneNumberVerification()) {
                String token = jwtService.generateToken(user.getPhoneNumber());
                UserResponseDto responseDto = mapper.toResponseDto(user);
                return new UserSignInResponseDto(responseDto.toString(),token);
            }
            throw new PhoneNumberNotVerifiedException("%s is not verified. Please verify phone your phone number".formatted(user.getPhoneNumber()));
        }
        throw new BadCredentialsException("Username or password doesn't match");
    }
}

