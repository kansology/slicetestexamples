package com.slicetest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by tarunkansal on 2/7/18.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CompanyNotFoundException extends RuntimeException {

    private final String message;

    public CompanyNotFoundException(String message) {
        this.message = message;
    }
}
