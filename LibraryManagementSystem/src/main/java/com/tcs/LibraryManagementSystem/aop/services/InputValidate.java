package com.tcs.LibraryManagementSystem.aop.services;

import com.tcs.LibraryManagementSystem.entity.Borrower;
import com.tcs.LibraryManagementSystem.entity.LibraryBranch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static com.tcs.LibraryManagementSystem.utility.JsonStringConvert.formatedString;
import static com.tcs.LibraryManagementSystem.utility.ResponceUpdate.VALIDATE_RESPONSE;

@Aspect
@Component
public class InputValidate {

    @Pointcut("execution(* com.tcs.LibraryManagementSystem.controller.BorrowerController.saveBorrower(..)) || " +
            "execution(* com.tcs.LibraryManagementSystem.controller.BorrowerController.updateBorrower(..))")
    public void performInputValidationBorrower(){}

    @Pointcut("execution(* com.tcs.LibraryManagementSystem.controller.LibraryBranchesController.saveBranch(..)) || " +
            "execution(* com.tcs.LibraryManagementSystem.controller.LibraryBranchesController.updateBranch(..))")
    public void performInputValidationBranch(){}

    @Around("performInputValidationBorrower()")
    public Object validateBorrowerInfo(ProceedingJoinPoint joinPoint) throws Throwable {
        // Get the original arguments
        Object[] args = joinPoint.getArgs();

        // validate borrower info
        for (Object item : args) {
            if (item != null) {
                if (item instanceof Borrower borrower) {
                    if (borrower.getBorrowerName() == null || borrower.getBorrowerName().isEmpty() || borrower.getBorrowerName().isBlank()  ) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(formatedString("Borrower Name " + VALIDATE_RESPONSE));
                    } else if (borrower.getBorrowerDOB() == null || borrower.getBorrowerDOB().isEmpty() || borrower.getBorrowerDOB().isBlank() ) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(formatedString("Borrower DOB " + VALIDATE_RESPONSE));
                    } else if (borrower.getBorrowerEmail() == null || borrower.getBorrowerEmail().isEmpty() || borrower.getBorrowerEmail().isBlank() ) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(formatedString("Borrower EMAIL " + VALIDATE_RESPONSE));
                    } else if (borrower.getBorrowerPhone() == null || borrower.getBorrowerPhone().isEmpty() || borrower.getBorrowerPhone().isBlank() ) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(formatedString("Borrower PHONE " + VALIDATE_RESPONSE));
                    }
                    return joinPoint.proceed(args);
                }/*else if (item instanceof Integer){
                    code to validate id
                }*/
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(formatedString("Borrower "+VALIDATE_RESPONSE));

    }
    @Around("performInputValidationBranch()")
    public Object validateBranchInfo(ProceedingJoinPoint joinPoint) throws Throwable {
        // Get the original arguments
        Object[] args = joinPoint.getArgs();

        // validate borrower info
        for (Object item : args) {
            if (item != null) {
                if (item instanceof LibraryBranch libraryBranch) {
                    if (libraryBranch.getLibraryBranchName() == null || libraryBranch.getLibraryBranchName().isEmpty() || libraryBranch.getLibraryBranchName().isBlank()) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(formatedString("Library Branch Name " + VALIDATE_RESPONSE));
                    } else if (libraryBranch.getLibraryBranchLocation() == null || libraryBranch.getLibraryBranchLocation().isEmpty() || libraryBranch.getLibraryBranchLocation().isBlank()) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(formatedString("Library Branch Location " + VALIDATE_RESPONSE));
                    } else if (libraryBranch.getWorkingHours() == null || libraryBranch.getWorkingHours().isEmpty() || libraryBranch.getWorkingHours().isBlank()) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(formatedString("Library Branch working hours " + VALIDATE_RESPONSE));
                    }
                    return joinPoint.proceed(args);
                }/*else if (item instanceof Integer){
                    code to validate id
                }*/
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(formatedString("Borrower "+VALIDATE_RESPONSE));

    }

}
