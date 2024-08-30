package com.tcs.LibraryManagementSystem.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.tcs.LibraryManagementSystem.ExceptionHandling.ResourceNotFoundInLibraryException;
import com.tcs.LibraryManagementSystem.entity.*;
import com.tcs.LibraryManagementSystem.service.*;
import com.tcs.LibraryManagementSystem.utility.JsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.tcs.LibraryManagementSystem.utility.JsonStringConvert.formatedString;
import static com.tcs.LibraryManagementSystem.utility.ResponceUpdate.BOOK_UPDATE_RESPONSE_MESSAGE;

/*
REST END POINTS:
1.GET: /api/books
2.GET: /api/books/{book_id}
3.POST: /api/books  @RequestBody Author information
4.POST: /api/books/author/{id}/  @RequestBody author information
5.POST: /api/books/genre/{id}/  @RequestBody genre information
6.PUT: /api/books/{book_id} @RequestBody Author,Branch, Genre information
7.DEL: /api/books/{book_id}

------------------------
POST: /api/books
{
"book_name":"Mahabharatham"
}
------------------------
POST: /api/books/author/{id}/  @RequestBody author information
{
"author_name":"Elango"
}
------------------------
POST: /api/books/genre/{id}/  @RequestBody genre information
{
"genre_name":"history"
}
------------------------
POST: /api/books/librarybranch/{id} @RequestBody librarybranch information
{
"libraryBranchName":"Ghandhi National Library",
 "libraryBranchLocation":"Chennai",
  "workingHours":"Mon-Fri 9AM-6PM"
}
------------------------
PUT: /api/books/{book_id}
{
"book_name":"Mahabharatham",
"authors":[{
"author_name":"Elango"
}],
"genres":[{
"genre_name":"history"
}],
"libraryBranches":[{
"libraryBranchName":"Ghandhi National Library",
 "libraryBranchLocation":"Chennai",
  "workingHours":"Mon-Fri 9AM-6PM"
}]
}
------------------------
 */
@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    BookService bookService;
    @Autowired
    GenreService genreService;
    @Autowired
    AuthorService authorService;
    @Autowired
    LibraryBranchService libraryBranchService;
    @Autowired
    BorrowerService borrowerService;
    Book book = null;

    @GetMapping()
    public ResponseEntity<String> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        if (books.size() > 0) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(books.toString());
        } else {
            throw  new ResourceNotFoundInLibraryException("No book found");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getBook(@PathVariable int id) {
        book = bookService.getBookById(id);
        if (book != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(book.toString());
        }
        throw  new ResourceNotFoundInLibraryException("Book id: " + id + " not found");
    }

    @PostMapping()
    public ResponseEntity<String> saveBook(@RequestBody Book updateBook) {
        book = bookService.bookSave(updateBook);
        if (book != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(formatedString("Book " + book.getBook_name() + " created"));
        }
        throw  new ResourceNotFoundInLibraryException("Book invalid data and not created");
    }

    @PostMapping("/author/{id}")
    public ResponseEntity<String> saveBookAuthor(@PathVariable int id, @RequestBody Author author) {
        book = bookService.bookAuthorSave(id, author);
        if (book != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(formatedString("Book :" + book.getBook_name() + " added under author: " + author.getAuthor_name()));
        }
        throw  new ResourceNotFoundInLibraryException("Book id: " + id + " Not found");
    }

    @PostMapping("/genre/{id}")
    public ResponseEntity<String> saveBookAuthor(@PathVariable int id, @RequestBody Genre genre) {
        book = bookService.bookGenreSave(id, genre);
        if (book != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(formatedString("Book :" + book.getBook_name() + " added under genre: " + genre.getGenre_name()));
        }
        throw  new ResourceNotFoundInLibraryException("Book id:" + id + " Not found");
    }

/*    @PostMapping("/librarybranch/{id}")
    public ResponseEntity<String> saveBookBranch(@PathVariable int id, @RequestBody LibraryBranch libraryBranch) {
        book = bookService.bookBranchSave(id, libraryBranch);
        if (book != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(formatedString("Book :" + book.getBook_name() + " added under branch: " + libraryBranch.getLibraryBranchName()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(formatedString("Book " + id + " Not found"));
    }*/

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBookAuthorGenre(@PathVariable int id, @RequestBody Book updateBook) {
        book = bookService.bookUpdate(id, updateBook);
        if (book != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(formatedString(BOOK_UPDATE_RESPONSE_MESSAGE));
        }
        throw  new ResourceNotFoundInLibraryException("Book id: " + id + " Not found");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id) {
        if (bookService.deleteById(id)) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(formatedString("Book id:" + id + "  deleted!"));
        }
        throw  new ResourceNotFoundInLibraryException("Book id: " + id + " Not found");
    }

    /* additional END points*/
    @GetMapping("/genre/{id}")
    public ResponseEntity<String> getBookByGenre(@PathVariable int id) {
        Genre genre = genreService.getGenreById(id);
        if (genre != null) {
            List<Book> books = genre.getBooks();
            if (books.size() > 0) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(books.toString());
            }
        }
        throw  new ResourceNotFoundInLibraryException("Books not found under Genre id: " + id);
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<List<Book>> getBookByAuthor(@PathVariable int id) {
        Author author = authorService.getAuthorById(id);
        if (author != null) {
            List<Book> books = author.getBooks();
            if (books !=null && books.size() > 0)
                return new ResponseEntity<>(books,HttpStatus.ACCEPTED);
        }
        throw  new ResourceNotFoundInLibraryException("Books not found under Author id: " + id);
    }
    @GetMapping("/library-branch/{id}")
    public ResponseEntity<List<Book> > getBookByLibrary(@PathVariable int id) {
        LibraryBranch libraryBranch = libraryBranchService.getBranchById(id);
        if (libraryBranch != null) {
            List<Book> books = libraryBranch.getBooks();
            if (books !=null && books.size() > 0)
                return new ResponseEntity<>(books,HttpStatus.ACCEPTED);
        }
        throw  new ResourceNotFoundInLibraryException("Books not found under library id: " + id);
    }
    @GetMapping("/borrower/{id}")
    public ResponseEntity< List<Book> > getBookByBorrower(@PathVariable int id) {
        Borrower borrower = borrowerService.getBorrowerById(id);
        if (borrower != null) {
            List<Book> books = borrower.getBooks();
            if (books !=null && books.size() > 0)
                return new ResponseEntity<>(books,HttpStatus.ACCEPTED);
        }
        throw  new ResourceNotFoundInLibraryException("Books not found under borrower id: " + id);
    }
}
