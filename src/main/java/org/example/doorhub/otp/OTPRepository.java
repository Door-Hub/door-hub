package org.example.doorhub.otp;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OTPRepository extends CrudRepository<OTP, String> {
}
