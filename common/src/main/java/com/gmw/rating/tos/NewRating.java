package com.gmw.rating.tos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewRating {
    private Long userId;
    private Long modId;
    private Integer rating;
}
