package org.example.doorhub.review.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReviewResponseDto {

    private Integer stars;
    private Integer userId;
    private Integer parentCategoryId;

}
