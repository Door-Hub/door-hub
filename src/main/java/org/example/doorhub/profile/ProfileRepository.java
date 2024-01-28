package org.example.doorhub.profile;

import org.example.doorhub.common.repository.GenericSpecificationRepository;
import org.example.doorhub.profile.entity.Profile;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends GenericSpecificationRepository<Profile,Integer> {



}
