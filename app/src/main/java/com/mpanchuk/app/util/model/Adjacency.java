package com.mpanchuk.app.util.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Adjacency {
    private Long root;
    private Long child;
    private int distance;

    @Override
    public String toString() {
        return root + " " + child + " " + distance;
    }
//    public boolean connected(String a, String b) {
//        return root.equals(a) && child.equals(b) || root.equals(b) && child.equals(a);
//    }
}
