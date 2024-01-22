package org.example.doorhub.user;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.doorhub.address.AddressRepository;
import org.example.doorhub.address.entity.Address;
import org.example.doorhub.common.exception.CustomExceptionThisUsernameOlReadyTaken;
import org.example.doorhub.common.exception.ExceptionUNAUTHORIZED;
import org.example.doorhub.common.exception.PhoneNumberNotVerifiedException;
import org.example.doorhub.common.exception.SmsVerificationException;
import org.example.doorhub.common.service.GenericCrudService;
import org.example.doorhub.jwt.JwtService;
import org.example.doorhub.notification.sms.eskiz.SmsNotificationService;
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
    public UserSignInResponseDto signIn(UserSignInDto signInDto) {
        String phoneNumber = signInDto.getPhoneNumber();

        User user = repository
                .findUserByPhoneNumber(phoneNumber)
                .orElseThrow(() ->
                        new BadCredentialsException("Username or password doesn't match"));

        if (passwordEncoder.matches(signInDto.getPassword(), user.getPassword())) {


                String token = jwtService.generateToken(user.getPhoneNumber());
                return new UserSignInResponseDto(token);

        }
        throw new BadCredentialsException("Username or password doesn't match");
    }

    @Transactional
    public UserResponseDto addUserAddress(Integer userid, Integer addressId) {

        User user = repository.findById(userid)
                .orElseThrow(() -> new EntityNotFoundException("user with id = %d not found".formatted(userid)));
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new EntityNotFoundException("address with id = %d not found".formatted(addressId)));

        user.getAddresses().add(address);

        User save = repository.save(user);
        return mapper.toResponseDto(save);
    }

    @Transactional
    public UserResponseDto verifyOtp(OtpVerifyDto verifyDto) {

        OTP otp = otpRepository
                .findById(verifyDto.getPhone())
                .orElseThrow(() -> new ExceptionUNAUTHORIZED("You need to register first"));

        if (otp.getCode() == verifyDto.getCode()) {
            User user = modelMapper.map(otp, User.class);

            User save = save(mapper.toCreateDto(user));

            otpRepository.delete(otp);

            return modelMapper.map(save, UserResponseDto.class);
        } else {
            throw new SmsVerificationException("Invalid verification code");
        }
    }

    @Transactional
    public UserResponseDto register(UserCreateDto userCreateDto) throws CustomExceptionThisUsernameOlReadyTaken {

        validateUserRegister(userCreateDto);

        OTP otp = modelMapper.map(userCreateDto, OTP.class);
        int code = new Random().nextInt(1000, 9999);
        otp.setCode(code);
        otp.setSendTime(LocalDateTime.now());
        otp.setSentCount(1);

        boolean sendSms = smsNotificationService.sendSms(otp.getPhoneNumber(), "Your verification code: %d".formatted(code));
        if (sendSms) {
            OTP save = otpRepository.save(otp);
            return modelMapper.map(save, UserResponseDto.class);
        } else {
            throw new RuntimeException();
        }

    }


    protected void validateUserRegister(UserCreateDto req) throws CustomExceptionThisUsernameOlReadyTaken {
        Optional<OTP> otp = otpRepository.findById(req.getPhoneNumber());

        if (otp.isPresent()) {
            throw new RuntimeException("sms ol ready");
        } else {
            Optional<User> byPhoneNumber = repository.findUserByPhoneNumber(req.getPhoneNumber());

            if (byPhoneNumber.isPresent()) {

                throw new CustomExceptionThisUsernameOlReadyTaken("This phone number is already logged in");
            }
        }
    }
}

