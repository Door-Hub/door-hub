package org.example.doorhub.user;

import org.example.doorhub.common.repository.GenericSpecificationRepository;
import org.example.doorhub.user.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends GenericSpecificationRepository<User, Integer> {

}
