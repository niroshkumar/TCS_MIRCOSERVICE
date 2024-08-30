package com.tcs.LibraryManagementSystem.controller;

import com.tcs.LibraryManagementSystem.ExceptionHandling.ResourceNotFoundInLibraryException;
import com.tcs.LibraryManagementSystem.entity.Book;
import com.tcs.LibraryManagementSystem.entity.LibraryBranch;
import com.tcs.LibraryManagementSystem.service.BookService;
import com.tcs.LibraryManagementSystem.service.LibraryBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.tcs.LibraryManagementSystem.utility.JsonStringConvert.formatedString;
import static com.tcs.LibraryManagementSystem.utility.ResponceUpdate.LIB_BRANCH_UPDATE_RESPONSE_MESSAGE;

@RestController
@RequestMapping("/api/library-branches")
public class LibraryBranchesController {
    @Autowired
    LibraryBranchService libraryBranchService;
    @Autowired
    BookService bookService;

    LibraryBranch libraryBranch = null;

    @GetMapping()
    public ResponseEntity<String> getAllBranches() {
        List<LibraryBranch> libraryBranches = libraryBranchService.getAllBranches();
        if (libraryBranches.size() > 0) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(libraryBranches.toString());
        } else {
            throw  new ResourceNotFoundInLibraryException("No library branches found");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getBranch(@PathVariable int id) {
        libraryBranch = libraryBranchService.getBranchById(id);
        if (libraryBranch != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(libraryBranch.toString());
        }
        throw  new ResourceNotFoundInLibraryException("Library branch id: " + id + " Not found");
    }

    @PostMapping()
    public ResponseEntity<String> saveBranch(@RequestBody LibraryBranch libraryBranch) {
        libraryBranch = libraryBranchService.libraryBranchSave(libraryBranch);
        if (libraryBranch != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(formatedString("Library branch :" + libraryBranch.getLibraryBranchName() + " created"));
        }
        throw  new ResourceNotFoundInLibraryException("Library branch invalid data and Not created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBranch(@PathVariable int id, @RequestBody LibraryBranch libraryBranch) {
        libraryBranch = libraryBranchService.branchUpdate(id, libraryBranch);
        if (libraryBranch != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(formatedString(LIB_BRANCH_UPDATE_RESPONSE_MESSAGE));
        }
        throw  new ResourceNotFoundInLibraryException("Library branch id: " + id + " Not found");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBranch(@PathVariable int id) {
        if (libraryBranchService.deleteById(id)) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(formatedString("Library branch id: " + id + "  deleted!"));
        }
        throw  new ResourceNotFoundInLibraryException("Library branch id: " + id + " Not found");
    }

    /*
    Add books under the library branch location
     */
    @PostMapping("/book/{id}")
    public ResponseEntity<String> saveBranchBook(@PathVariable int id, @RequestBody Book book) {
        libraryBranch = libraryBranchService.libraryBranchSaveBooks(id, book);
        if (libraryBranch != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(formatedString("Book :" + book.getBook_name() + " added in library branch: " + libraryBranch.getLibraryBranchName()));
        }
        throw  new ResourceNotFoundInLibraryException("Library branch id: " + id + " Not found");
    }
}
