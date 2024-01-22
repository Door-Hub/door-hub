package org.example.doorhub.view.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class View {
    @Id
    private Integer id;
    private Integer userId;
    private Integer categoryId;
}
