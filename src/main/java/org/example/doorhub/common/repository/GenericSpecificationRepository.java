package org.example.doorhub.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericSpecificationRepository<ENTITY, ID> extends JpaRepository<ENTITY, ID>, JpaSpecificationExecutor<ENTITY>
{
}
