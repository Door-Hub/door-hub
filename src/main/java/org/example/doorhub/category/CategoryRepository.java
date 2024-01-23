package org.example.doorhub.category;

import org.example.doorhub.category.entity.Category;
import org.example.doorhub.common.repository.GenericSpecificationRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends GenericSpecificationRepository<Category, Integer> {
}
