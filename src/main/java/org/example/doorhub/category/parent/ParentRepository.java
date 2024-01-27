package org.example.doorhub.category.parent;

import org.example.doorhub.category.parent.entity.ParentCategory;
import org.example.doorhub.common.repository.GenericSpecificationRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends GenericSpecificationRepository<ParentCategory, Integer> {
}
