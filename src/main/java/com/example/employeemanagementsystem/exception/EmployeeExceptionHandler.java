package com.example.employeemanagementsystem.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class EmployeeExceptionHandler {
    @Autowired
    Environment environment;
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorInfo> handleBookNotFoundException(EmployeeNotFoundException employeeNotFoundException){
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrorCode(404);
        errorInfo.setErrorMessage(environment.getProperty(employeeNotFoundException.getMessage()));
        errorInfo.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
    }
    //ConstraintViolationException

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorInfo> exceptionHandler(MethodArgumentNotValidException exception) {
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
        errorInfo.setTimestamp(LocalDateTime.now());

        String errorMsg = exception.getBindingResult().getAllErrors().stream().map(x -> x.getDefaultMessage())
                .collect(Collectors.joining(", "));
        errorInfo.setErrorMessage(errorMsg);

        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }





}
