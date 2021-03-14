package com.paypal.bfs.test.employeeserv.error;

public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException(String exception) {
        super(exception);
    }
}
