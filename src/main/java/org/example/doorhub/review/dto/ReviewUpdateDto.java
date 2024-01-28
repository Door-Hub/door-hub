package org.example.doorhub.review.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReviewUpdateDto {
    private Integer id;
    private Integer stars;
    private Integer seenUsers;

}
