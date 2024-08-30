package com.tcs.LibraryManagementSystem.controller;

import com.tcs.LibraryManagementSystem.ExceptionHandling.ResourceNotFoundInLibraryException;
import com.tcs.LibraryManagementSystem.entity.Book;
import com.tcs.LibraryManagementSystem.entity.Genre;
import com.tcs.LibraryManagementSystem.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.tcs.LibraryManagementSystem.utility.JsonStringConvert.formatedString;
import static com.tcs.LibraryManagementSystem.utility.ResponceUpdate.GENRE_UPDATE_RESPONSE_MESSAGE;

@RestController
@RequestMapping("/api/genres")
public class GenreController {
    @Autowired
    GenreService genreService;

    Genre genre = null;

    @GetMapping()
    public ResponseEntity<String> getAllGenres() {
        List<Genre> genres = genreService.getAllGenres();
        if (genres.size() > 0) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(genres.toString());
        } else {
            throw  new ResourceNotFoundInLibraryException("No genres found");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getGenre(@PathVariable int id) {
        genre = genreService.getGenreById(id);
        if (genre != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(genre.toString());
        }
        throw  new ResourceNotFoundInLibraryException("Genre id: " + id + " not found");
    }

    @PostMapping()
    public ResponseEntity<String> saveBook(@RequestBody Genre updateGenre) {
        genre = genreService.genreSave(updateGenre);
        if (genre != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(formatedString("Genre " + genre.getGenre_name() + " created"));
        }
        throw  new ResourceNotFoundInLibraryException("Genre invalid data");
    }

    @PostMapping("/book/{id}")
    public ResponseEntity<String> saveGenreBook(@PathVariable int id,@RequestBody Book book) {
        genre= genreService.genreBookSave(id,book);
        if(genre!=null){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(formatedString("Book :" + book.getBook_name() + " added under genre: " + book.getBook_name()));
        }
        throw  new ResourceNotFoundInLibraryException("Genre id: " + id + " not found");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateGenreBook(@PathVariable int id, @RequestBody Genre updateGenre) {
        genre = genreService.genreUpdate(id, updateGenre);
        if (genre != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(formatedString(GENRE_UPDATE_RESPONSE_MESSAGE));
        }
        throw  new ResourceNotFoundInLibraryException("Genre id: " + id + " not found");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id) {
        if (genreService.deleteById(id)) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(formatedString("Genre id:" + id + "  deleted!"));
        }
        throw  new ResourceNotFoundInLibraryException("Genre id: " + id + " not found");
    }

}
