package org.example.doorhub.address;

import org.example.doorhub.address.entity.Address;
import org.example.doorhub.common.repository.GenericSpecificationRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends GenericSpecificationRepository<Address, Integer> {

}
