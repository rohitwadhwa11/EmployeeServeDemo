package com.paypal.bfs.test.employeeserv.error;

public class EmpNotExistsException extends RuntimeException {
    public EmpNotExistsException(String exception) {
        super(exception);
    }
}
