package org.example.doorhub.payment;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.doorhub.common.configuration.ModelMapperConfiguration;
import org.example.doorhub.payment.dto.PaymentCreatedDto;
import org.example.doorhub.payment.dto.PaymentResponseDto;
import org.example.doorhub.payment.entity.Payment;
import org.example.doorhub.user.UserRepository;
import org.example.doorhub.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final ModelMapperConfiguration mapper;

    @Transactional
    public PaymentResponseDto create(PaymentCreatedDto createdDto) {

        User user = new User();
        User user1 = userRepository.findById(user.getId())
                .orElseThrow(EntityNotFoundException::new);

        Payment map = mapper.mapper().map(createdDto, Payment.class);

        map.setUser(user1);
        map.setUserId(user1.getId());

        paymentRepository.save(map);

        return mapper.mapper().map(map, PaymentResponseDto.class);

    }

    @Transactional
    public List<Payment> getAllHistory() {
        return paymentRepository.findAll();
    }

    @Transactional
    public void delete(Integer id) {

        User user = new User();
        paymentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        paymentRepository.deleteByIdAndUser(id, user);
    }
}
