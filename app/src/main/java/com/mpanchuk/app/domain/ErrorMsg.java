package com.mpanchuk.app.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorMsg {
    private String status;
    private String message;
}
