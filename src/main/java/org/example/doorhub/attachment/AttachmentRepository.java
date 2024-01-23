package org.example.doorhub.attachment;

import org.example.doorhub.attachment.entity.Attachment;
import org.example.doorhub.common.repository.GenericSpecificationRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentRepository extends GenericSpecificationRepository<Attachment, Integer>
{

}
