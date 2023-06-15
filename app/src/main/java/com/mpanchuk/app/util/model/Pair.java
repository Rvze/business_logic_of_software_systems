package com.mpanchuk.app.util.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Pair<L extends Number, I extends Number> {
    private Long first ;
    private Integer second ;
}
