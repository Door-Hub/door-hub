package org.example.doorhub.category.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.doorhub.user.entity.User;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ParentCategoryResponseDto {

    private Integer id;
    private String name;
    private User userId;
    private Integer stars;
}
