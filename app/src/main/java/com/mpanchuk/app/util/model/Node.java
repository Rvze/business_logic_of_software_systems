package com.mpanchuk.app.util.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Node {
    private String root;
    private List<String> children;
}
