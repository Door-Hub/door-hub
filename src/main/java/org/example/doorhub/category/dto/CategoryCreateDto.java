package org.example.doorhub.category.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryCreateDto {

    private Integer id;
    private String name;
    private String avatar;
    private Integer parentCategoryId;
}
