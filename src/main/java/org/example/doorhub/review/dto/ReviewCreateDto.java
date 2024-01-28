package org.example.doorhub.review.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReviewCreateDto {

    @NotNull
    private Integer stars;
    private Integer seenUsers;
    @NotNull
    private Integer userId;
    @NotNull
    private Integer parentCategoryId;
}
