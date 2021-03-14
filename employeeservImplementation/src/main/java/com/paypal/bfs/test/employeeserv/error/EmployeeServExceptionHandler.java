package com.paypal.bfs.test.employeeserv.error;


import com.paypal.bfs.test.employeeserv.model.EmpResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class EmployeeServExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ NullPointerException.class, IllegalArgumentException.class, RuntimeException.class})
    public ResponseEntity<Object> handleInternal(final RuntimeException ex, final WebRequest request) {

        // log the exception, haven't added the logger config for this demo project
        // customising the error message that can be returned to user
        EmpResponseDTO empResponseDTO=new EmpResponseDTO();
        empResponseDTO.setSuccess(false);
        empResponseDTO.setMessage("A technical Error has occured");
        return handleExceptionInternal(ex, empResponseDTO, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler({ EmployeeServException.class})
    public ResponseEntity<Object> handleEmpServerException(final RuntimeException ex, final WebRequest request) {

        EmpResponseDTO empResponseDTO=new EmpResponseDTO();
        empResponseDTO.setSuccess(false);
        empResponseDTO.setMessage(ex.getMessage());
        return handleExceptionInternal(ex, empResponseDTO, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler({ EmpNotExistsException.class})
    public ResponseEntity<Object> handleEmpNotExistsException(final RuntimeException ex, final WebRequest request) {

        EmpResponseDTO empResponseDTO=new EmpResponseDTO();
        empResponseDTO.setSuccess(false);
        empResponseDTO.setMessage(ex.getMessage());
        return handleExceptionInternal(ex, empResponseDTO, new HttpHeaders(), HttpStatus.OK, request);
    }

    @ExceptionHandler({ InvalidRequestException.class})
    public ResponseEntity<Object> handleInvalidReqException(final RuntimeException ex, final WebRequest request) {

        EmpResponseDTO empResponseDTO=new EmpResponseDTO();
        empResponseDTO.setSuccess(false);
        empResponseDTO.setMessage(ex.getMessage());
        return handleExceptionInternal(ex, empResponseDTO, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }


}