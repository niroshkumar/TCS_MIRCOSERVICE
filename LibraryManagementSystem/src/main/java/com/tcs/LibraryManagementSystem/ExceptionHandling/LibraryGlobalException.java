package com.tcs.LibraryManagementSystem.ExceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class LibraryGlobalException {
    @ExceptionHandler(ResourceNotFoundInLibraryException.class)
    public ResponseEntity<LibraryErrorResponse> handleResourceNotFound(ResourceNotFoundInLibraryException ex) {
        LibraryErrorResponse errorResponse = new LibraryErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
