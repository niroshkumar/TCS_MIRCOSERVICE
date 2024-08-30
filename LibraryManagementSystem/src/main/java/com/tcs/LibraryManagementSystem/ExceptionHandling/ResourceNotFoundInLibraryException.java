package com.tcs.LibraryManagementSystem.ExceptionHandling;

public class ResourceNotFoundInLibraryException extends RuntimeException{
    public  ResourceNotFoundInLibraryException(String message){
        super(message);
    }
}
