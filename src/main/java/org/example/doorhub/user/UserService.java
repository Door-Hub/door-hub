package org.example.doorhub.user;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.doorhub.address.AddressRepository;
import org.example.doorhub.common.exception.CustomExceptionThisUsernameOlReadyTaken;
import org.example.doorhub.common.exception.ExceptionUNAUTHORIZED;
import org.example.doorhub.common.exception.SmsAlreadySentException;
import org.example.doorhub.common.exception.SmsVerificationException;
import org.example.doorhub.common.service.GenericCrudService;
import org.example.doorhub.jwt.JwtService;
import org.example.doorhub.notification.SmsNotificationService;
import org.example.doorhub.otp.OTP;
import org.example.doorhub.otp.OTPRepository;
import org.example.doorhub.otp.dto.OtpVerifyDto;
import org.example.doorhub.user.dto.*;
import org.example.doorhub.user.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;


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
    private final AddressRepository addressRepository;
    private final OTPRepository otpRepository;
    private final ModelMapper modelMapper;
    private final SmsNotificationService smsNotificationService;


    @Override
    protected User save(UserCreateDto userCreateDto) {
        User user = mapper.toEntity(userCreateDto);
        return repository.save(user);
    }

    @Override
    protected User updateEntity(UserUpdateDto userUpdateDto, User user) {
        mapper.update(userUpdateDto, user);
        return repository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        return repository.findUserByPhoneNumber(phone).orElseThrow(() -> new BadCredentialsException("bad credentials"));
    }

    @Transactional
    public RegisterSignInResponseDto signIn(UserSignInDto signInDto) {
        String phoneNumber = signInDto.getPhoneNumber();

        User user = repository
                .findUserByPhoneNumber(phoneNumber)
                .orElseThrow(() ->
                        new BadCredentialsException("Username or password doesn't match"));

        Optional<OTP> otp = otpRepository.findById(user.getPhoneNumber());
        if (otp.isPresent()) {
            throw new SmsAlreadySentException("sms ol ready");
        }

        return modelMapper.map(sendSms(mapper.toCreateDto(user)), RegisterSignInResponseDto.class);
    }

    @Transactional
    public UserResponseDto registerVerifyOtp(OtpVerifyDto verifyDto) {

        OTP otp = otpRepository
                .findById(verifyDto.getPhone())
                .orElseThrow(() -> new ExceptionUNAUTHORIZED("You need to register first"));

        if (otp.getCode() == verifyDto.getCode()) {
            User user = modelMapper.map(otp, User.class);

            User save = save(mapper.toCreateDto(user));

            otpRepository.delete(otp);

            UserResponseDto responseDto = modelMapper.map(save, UserResponseDto.class);
            responseDto.setId(save.getId());
            return responseDto;
        } else {
            throw new SmsVerificationException("Invalid verification code");
        }
    }

    @Transactional
    public UserResponseDto signInVerifyOtp(OtpVerifyDto verifyDto) {

        OTP otp = otpRepository
                .findById(verifyDto.getPhone())
                .orElseThrow(() -> new ExceptionUNAUTHORIZED("You need to register first"));
        User entity = repository
                .findUserByPhoneNumber(verifyDto.getPhone())
                .orElseThrow(() -> new EntityNotFoundException("user phone not found"));

        if (otp.getCode() == verifyDto.getCode()) {

            User user = modelMapper.map(otp, User.class);
            otpRepository.delete(otp);


            UserResponseDto responseDto = mapper.toResponseDto(user);
            responseDto.setId(entity.getId());
            return responseDto;
        } else {
            throw new SmsVerificationException("Invalid verification code");
        }
    }

    @Transactional
    public RegisterSignInResponseDto register(UserCreateDto userCreateDto) throws CustomExceptionThisUsernameOlReadyTaken {

        try {
            validateUser(userCreateDto);
        } catch (CustomExceptionThisUsernameOlReadyTaken e) {
            throw new CustomExceptionThisUsernameOlReadyTaken("phone number ol ready taken");
        }

        return modelMapper.map(sendSms(userCreateDto), RegisterSignInResponseDto.class);
    }

    private UserResponseDto sendSms(UserCreateDto userCreateDto) {

        OTP otp = modelMapper.map(userCreateDto, OTP.class);
        int code = new Random().nextInt(1000, 9999);
        otp.setCode(code);
        otp.setSendTime(LocalDateTime.now());
        otp.setSentCount(1);

        boolean sendSms = smsNotificationService.sendSms(otp.getPhoneNumber(), "Door Hub => your verification code: %d".formatted(code));
        if (sendSms) {
            OTP save = otpRepository.save(otp);
            return modelMapper.map(save, UserResponseDto.class);
        } else {
            throw new ExceptionUNAUTHORIZED("Failed to send SMS");
        }
    }

    protected void validateUser(UserCreateDto req) throws CustomExceptionThisUsernameOlReadyTaken {
        Optional<OTP> otp = otpRepository.findById(req.getPhoneNumber());

        if (otp.isPresent()) {
            throw new SmsAlreadySentException("sms ol ready");
        } else {
            Optional<User> byPhoneNumber = repository.findUserByPhoneNumber(req.getPhoneNumber());

            if (byPhoneNumber.isPresent()) {
                throw new CustomExceptionThisUsernameOlReadyTaken("This phone number is already logged in");
            }
        }
    }
}

