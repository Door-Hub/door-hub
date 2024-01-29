package org.example.doorhub.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.doorhub.common.exception.CustomExceptionThisUsernameOlReadyTaken;
import org.example.doorhub.jwt.JwtService;
import org.example.doorhub.otp.dto.OtpVerifyDto;
import org.example.doorhub.user.dto.RegisterSignInResponseDto;
import org.example.doorhub.user.dto.UserCreateDto;
import org.example.doorhub.user.dto.UserResponseDto;
import org.example.doorhub.user.dto.UserSignInDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtService jwtService;


    @GetMapping("/me")
    public Principal principal(Principal principal){
        return principal;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterSignInResponseDto> register(@RequestBody @Valid UserCreateDto userRegisterRequestDto) throws CustomExceptionThisUsernameOlReadyTaken {
        RegisterSignInResponseDto userResponseDto = userService.register(userRegisterRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDto);
    }

    @PostMapping("/login")
    public ResponseEntity<RegisterSignInResponseDto> signIn(@RequestBody @Valid UserSignInDto signInDto) {
        RegisterSignInResponseDto userResponseDto = userService.signIn(signInDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDto);
    }

    @PostMapping("register/verify-phone")
    public ResponseEntity<UserResponseDto> registerVerifyPhone(@RequestBody @Valid OtpVerifyDto verifyDto) {
        UserResponseDto userResponseDto = userService.registerVerifyOtp(verifyDto);
        String token = jwtService.generateToken(userResponseDto.getPhoneNumber());

        return ResponseEntity.status(HttpStatus.CREATED)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .body(userResponseDto);
    }

    @PostMapping("login/verify-phone")
    public ResponseEntity<UserResponseDto> signInVerifyPhone(@RequestBody @Valid OtpVerifyDto verifyDto) {
        UserResponseDto userResponseDto = userService.signInVerifyOtp(verifyDto);
        String token = jwtService.generateToken(userResponseDto.getPhoneNumber());

        return ResponseEntity.status(HttpStatus.CREATED)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .body(userResponseDto);
    }

}
