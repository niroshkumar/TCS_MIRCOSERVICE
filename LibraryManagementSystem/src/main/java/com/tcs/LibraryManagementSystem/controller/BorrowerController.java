package com.tcs.LibraryManagementSystem.controller;

import com.tcs.LibraryManagementSystem.ExceptionHandling.ResourceNotFoundInLibraryException;
import com.tcs.LibraryManagementSystem.entity.Book;
import com.tcs.LibraryManagementSystem.entity.Borrower;
import com.tcs.LibraryManagementSystem.entity.LibraryBranch;
import com.tcs.LibraryManagementSystem.service.BorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.tcs.LibraryManagementSystem.utility.JsonStringConvert.formatedString;
import static com.tcs.LibraryManagementSystem.utility.ResponceUpdate.BORROWER_UPDATE_RESPONSE_MESSAGE;

@RestController
@RequestMapping("/api/borrowers")
public class BorrowerController {
    @Autowired
    BorrowerService borrowerService;

    Borrower borrower = null;

    @GetMapping()
    public ResponseEntity<String> getAllBorrowers() {
        List<Borrower> borrowers = borrowerService.getAllBorrowers();
        if (borrowers!=null && borrowers.size() > 0) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(borrowers.toString());
        } else {
            throw  new ResourceNotFoundInLibraryException("No Borrowers found");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getBorrower(@PathVariable int id) {
        borrower = borrowerService.getBorrowerById(id);
        if (borrower != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(borrower.toString());
        }
        throw  new ResourceNotFoundInLibraryException("Borrower id: " + id + " Not found");
    }

    @PostMapping()
    public ResponseEntity<String> saveBorrower(@RequestBody Borrower updateBorrower) {
        borrower = borrowerService.borrowerSave(updateBorrower);
        if (borrower != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(formatedString("Borrower :" + borrower.getBorrowerName() + " created"));
        }
        throw  new ResourceNotFoundInLibraryException("Borrower invalid data and Not created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBorrower(@PathVariable int id, @RequestBody Borrower updateBorrower) {
        borrower = borrowerService.borrowerUpdate(id, updateBorrower);
        if (borrower != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(formatedString(BORROWER_UPDATE_RESPONSE_MESSAGE));
        }
        throw  new ResourceNotFoundInLibraryException("Borrower id: " + id + " Not found");
    }
    @PostMapping("/book/{id}")
    public ResponseEntity<String> saveBookBranch(@PathVariable int id, @RequestBody Book book) {
        borrower = borrowerService.borrowerBookSave(id, book);
        if (borrower != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(formatedString("Book :" + book.getBook_name() + " borrowed by: " + borrower.getBorrowerName()));
        }
        throw  new ResourceNotFoundInLibraryException("Borrower id: " + id + " Not found");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBorrower(@PathVariable int id) {
        if (borrowerService.deleteById(id)) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(formatedString("Borrower id:" + id + "  deleted!"));
        }
        throw  new ResourceNotFoundInLibraryException("Borrower id: " + id + " Not found");
    }
}
