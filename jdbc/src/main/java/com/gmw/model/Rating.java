package com.gmw.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class Rating extends Table{
    private Long id;
    private Long userId;
    private Long modId;
    private String rating;


    public Rating() {
        super(null);
    }

    public Rating(String tableName) {
        super(tableName);
    }

    public Rating(String tableName, Long id, Long userId, Long modId, String rating) {
        super(tableName);
        this.id = id;
        this.userId = userId;
        this.modId = modId;
        this.rating = rating;
    }
}