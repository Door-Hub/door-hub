package org.example.doorhub.attachment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Attachment {

    @Id
    private Integer id;
    private String fileName;
    private String OriginalFileName;
    private String url;

    @Enumerated(EnumType.STRING)
    private FileType fileType;



}
