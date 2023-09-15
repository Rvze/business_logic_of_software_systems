package com.mpanchuk.app.domain;


import com.mpanchuk.app.model.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class StashPair<L extends Item, I extends Number> implements Serializable {
    private L first ;
    private I second ;
}
