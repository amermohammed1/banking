package com.parallon.banking.accounts.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)

public class InvalidOperationException  extends RuntimeException {
    private static final long serialVersionUID = 100L;
    public InvalidOperationException(String operation_failed) {
    }
}
