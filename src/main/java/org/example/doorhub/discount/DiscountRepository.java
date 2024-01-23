package org.example.doorhub.discount;

import org.example.doorhub.common.repository.GenericSpecificationRepository;
import org.example.doorhub.discount.entity.Discount;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends GenericSpecificationRepository<Discount, Integer> {
}
