package com.transactionanalyser.exception;

import java.io.IOException;

public class BadCsvException extends IOException {

    public BadCsvException(String message) {
        super(message);
    }
}
