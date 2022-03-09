package com.example.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeExceptionHandler {
    private final ErrorResponseModel errorResponseModel;

    public EmployeeExceptionHandler(ErrorResponseModel errorResponseModel) {
        this.errorResponseModel = errorResponseModel;
    }


    @ExceptionHandler
    public ResponseEntity<ErrorResponseModel> handleEmployeeNotFoundException(EmployeeNotFoundException e)
    {
        errorResponseModel.setErrorMessage(e.getMessage());
        errorResponseModel.setErrorTime(System.currentTimeMillis());
        errorResponseModel.setErrorCode(HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseModel);
    }
}
