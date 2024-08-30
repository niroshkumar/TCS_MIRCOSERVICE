package com.tcs.LibraryManagementSystem.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.tcs.LibraryManagementSystem.ExceptionHandling.ResourceNotFoundInLibraryException;
import com.tcs.LibraryManagementSystem.entity.Author;
import com.tcs.LibraryManagementSystem.entity.Book;
import com.tcs.LibraryManagementSystem.service.AuthorService;
import com.tcs.LibraryManagementSystem.utility.JsonService;
import io.swagger.v3.core.util.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.tcs.LibraryManagementSystem.utility.JsonStringConvert.formatedString;
import static com.tcs.LibraryManagementSystem.utility.ResponceUpdate.AUTHOR_UPDATE_RESPONSE_MESSAGE;

/*
REST END POINTS:
1.GET: /api/authors
2.GET: /api/authors/{author_id}
3.POST: /api/authors  @RequestBody Author information
4.POST: /api/authors/book/{author_id}/  @RequestBody Book information
5.PUT: /api/authors/{author_id} @RequestBody Author information
6.DEL: /api/authors/{author_id}
------------------------
POST: /api/authors
{
"author_name":"Kalki"
}
------------------------
POST: /api/authors/book/{author_id}
{
"book_name":"Ponniyin Selvan"
}
------------------------
PUT: /api/authors/{author_id}
{
"author_name":"Kalki V1.2",
"books":[{
"book_name":"Ponniyin Selvan v1.2"
}]
}
 */
@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    Author author = null;

    @GetMapping()
    public ResponseEntity<?> getAllAuthors() {
        List<Author> authors = authorService.getAllAuthor();
        if (authors.size() > 0) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(authors.toString());
        } else {
            throw  new ResourceNotFoundInLibraryException("LibraryErrorResponse");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAuthor(@PathVariable int id) {
        author = authorService.getAuthorById(id);
        if (author != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(author.toString());
        }
        throw  new ResourceNotFoundInLibraryException("Author Not found with ID: "+id);
    }

    @PostMapping()
    public ResponseEntity<?> saveAuthor(@RequestBody Author updateAuthor) {
        author = authorService.authorSave(updateAuthor);
        if (author != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(formatedString("Author :" + author.getAuthor_name() + " created"));
        }
        throw  new ResourceNotFoundInLibraryException("Author invalid data: "+updateAuthor);
    }

    @PostMapping("/book/{id}/")
    public ResponseEntity<Book> saveAuthorBook(@PathVariable int id, @RequestBody Book book) {
        author = authorService.authorBookSave(id, book);
        if (author != null) {
            //return ResponseEntity.status(HttpStatus.ACCEPTED).body(formatedString("Book :" + book.getBook_name() + " added under author: " + author.getAuthor_name()));
            return new ResponseEntity<>(book,HttpStatus.ACCEPTED);
        }
        throw  new ResourceNotFoundInLibraryException("Author Not found with ID: "+id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAuthorBook(@PathVariable int id, @RequestBody Author updateAuthor) {
        author = authorService.authorUpdate(id, updateAuthor);
        if (author != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(formatedString(AUTHOR_UPDATE_RESPONSE_MESSAGE));
        }
        throw  new ResourceNotFoundInLibraryException("Author Not found with ID: "+id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable int id) {
        if (authorService.deleteById(id)) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(formatedString("Author id:" + id + "  deleted!"));
        }
        throw  new ResourceNotFoundInLibraryException("Author Not found with ID: "+id);
    }

}
