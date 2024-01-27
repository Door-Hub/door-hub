package org.example.doorhub.category.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponseDto {

    private Integer id;
    private String name;
    private String avatar;
}
