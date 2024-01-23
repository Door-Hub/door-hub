package org.example.doorhub.listeners;

import jakarta.persistence.PrePersist;
import org.example.doorhub.discount.entity.Discount;

import java.time.LocalDateTime;

public class DiscountStartDate {
    @PrePersist
    public void startDate(Discount discount) {
        discount.setStartDate(LocalDateTime.now());
    }

}
