package com.parallon.banking.accounts.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class TransferFailedException extends RuntimeException {


    private static final long serialVersionUID = 100L;
    public TransferFailedException(String operation_failed) {
    }
}
