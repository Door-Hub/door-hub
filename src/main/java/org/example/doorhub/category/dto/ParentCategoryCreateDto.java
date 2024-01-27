package org.example.doorhub.category.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ParentCategoryCreateDto {

    private Integer id;
    private String name;
    private Integer userId;

}
