package org.example.doorhub.user;

import org.example.doorhub.common.repository.GenericSpecificationRepository;
import org.example.doorhub.user.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends GenericSpecificationRepository<User, Integer> {
    Optional<User> findUserByPhoneNumber(String phone);

    Optional<User> findUserByEmail(String email);
}
