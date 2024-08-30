package com.tcs.LibraryManagementSystem.service;

import com.tcs.LibraryManagementSystem.entity.Author;
import com.tcs.LibraryManagementSystem.entity.Book;
import com.tcs.LibraryManagementSystem.entity.Genre;
import com.tcs.LibraryManagementSystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.tcs.LibraryManagementSystem.utility.ResponceUpdate.DELETED_FALG;
import static com.tcs.LibraryManagementSystem.utility.ResponceUpdate.GENRE_UPDATE_RESPONSE_MESSAGE;

@Service
public class GenreService {

    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    BorrowerRepository borrowerRepository;
    @Autowired
    GenreRepository genreRepository;
    @Autowired
    LibraryBranchRepository libraryBranchRepository;

    public Genre genreSave(Genre genre) {
        return genreRepository.save(genre);

    }
    public Genre genreUpdate(int genre_id, Genre updateGenre) {
        StringBuilder stringBuilder=new StringBuilder();
        Genre genre = genreRepository.findById(genre_id).orElse(null);
        if (genre != null) {
            if(updateGenre.getGenre_name() !=null && !updateGenre.getGenre_name().isEmpty()) {
                genre.setGenre_name(updateGenre.getGenre_name());
                stringBuilder.append("Genre info Updated");
            }else {
                stringBuilder.append("Genre info is empty");
            }
            stringBuilder.append(" and ");
            if(updateGenre.getBooks()!=null && updateGenre.getBooks().size() > 0) {
                genre.setBooks(updateGenre.getBooks());
                stringBuilder.append("Book info Updated");
            }else{
                stringBuilder.append("Book info is empty");
            }
            GENRE_UPDATE_RESPONSE_MESSAGE = "Genre :" + updateGenre.getGenre_name() + " and id :" + updateGenre.getId() + " "+ stringBuilder;
            genreRepository.save(genre);
        }
        return genre;
    }
    public Genre genreBookSave(int genre_id, Book book) {
        Genre genre = genreRepository.findById(genre_id).orElse(null);
        if (genre != null) {
            genre.getBooks().add(book);
            genreRepository.save(genre);
        }
        return genre;

    }
    public List<Genre> getAllGenres() {
        return new ArrayList<>(genreRepository.findAll());
    }

    public Genre getGenreById(int id) {
        Optional<Genre> genre = genreRepository.findById(id);
        return genre.orElse(null);
    }

    public Boolean deleteById(int id){

        if(getGenreById(id)!=null){
            bookRepository.deleteById(id);
            return DELETED_FALG;
        }else {
            return !DELETED_FALG;

        }
    }

}
