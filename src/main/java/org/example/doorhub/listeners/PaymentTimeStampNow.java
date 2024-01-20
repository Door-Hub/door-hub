package org.example.doorhub.listeners;

import jakarta.persistence.PrePersist;
import org.example.doorhub.payment.entity.Payment;

import java.time.LocalDateTime;

public class PaymentTimeStampNow {

    @PrePersist
    public void timestamp(Payment payment) {
        payment.setTimestamp(LocalDateTime.now());
    }

}
