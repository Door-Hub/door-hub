package org.example.doorhub.payment;

import org.example.doorhub.payment.entity.Payment;
import org.example.doorhub.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    void deleteByIdAndUser(Integer id, User user);
}
