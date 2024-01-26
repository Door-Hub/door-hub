package org.example.doorhub.attachment;

import org.example.doorhub.attachment.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {

    Optional<Attachment> findByUserId(Integer userId);

    void deleteById(Integer id);
}
