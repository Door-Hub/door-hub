package org.example.doorhub.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.doorhub.user.dto.UserCreateDto;
import org.example.doorhub.user.dto.UserResponseDto;
import org.example.doorhub.user.dto.UserSignInDto;
import org.example.doorhub.user.dto.UserSignInResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@RequestBody @Valid UserCreateDto userRegisterRequestDto){
        UserResponseDto userResponseDto = userService.create(userRegisterRequestDto);
        System.out.println("userResponseDto" + userResponseDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userResponseDto);
    }
    @PostMapping("/login")
    public ResponseEntity<UserSignInResponseDto> signIn(@RequestBody @Valid UserSignInDto signInDto){
        UserSignInResponseDto userSignInResponseDto = userService.signIn(signInDto);
        return ResponseEntity.ok(userSignInResponseDto);
    }
}
