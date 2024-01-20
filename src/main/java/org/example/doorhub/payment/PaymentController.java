package org.example.doorhub.payment;


import lombok.RequiredArgsConstructor;
import org.example.doorhub.payment.dto.PaymentCreatedDto;
import org.example.doorhub.payment.dto.PaymentResponseDto;
import org.example.doorhub.payment.entity.Payment;
import org.example.doorhub.user.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponseDto> create(@RequestBody PaymentCreatedDto createdDto ) {
        PaymentResponseDto responseDto = paymentService.create(createdDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/history/and/payments")
    public ResponseEntity<List<Payment>> history() {
        return ResponseEntity.status(HttpStatus.OK).body(paymentService.getAllHistory());
    }

    @DeleteMapping("/id")
    public void delete(@PathVariable("id") Integer id) {
        paymentService.delete(id);
    }
}
