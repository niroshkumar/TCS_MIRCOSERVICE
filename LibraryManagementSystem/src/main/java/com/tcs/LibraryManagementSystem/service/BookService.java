package com.tcs.LibraryManagementSystem.service;

import com.tcs.LibraryManagementSystem.entity.Author;
import com.tcs.LibraryManagementSystem.entity.Book;
import com.tcs.LibraryManagementSystem.entity.Genre;
import com.tcs.LibraryManagementSystem.entity.LibraryBranch;
import com.tcs.LibraryManagementSystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.tcs.LibraryManagementSystem.utility.ResponceUpdate.BOOK_UPDATE_RESPONSE_MESSAGE;
import static com.tcs.LibraryManagementSystem.utility.ResponceUpdate.DELETED_FALG;

@Service
public class BookService {

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
    public Book bookSave(Book book) {
        return bookRepository.save(book);

    }
    public Book bookUpdate(int book_id, Book updateBook) {
        StringBuilder stringBuilder=new StringBuilder();
        Book book1 = bookRepository.findById(book_id).orElse(null);
        if (book1 != null) {
            if(updateBook.getBook_name() !=null && !updateBook.getBook_name().isEmpty()) {
                book1.setBook_name(updateBook.getBook_name());
                stringBuilder.append("1. Book info Updated");
            }else {
                stringBuilder.append("1. Book info is empty");
            }
            stringBuilder.append("\n");
            if(updateBook.getAuthors()!=null && updateBook.getAuthors().size() > 0) {
                book1.setAuthors(updateBook.getAuthors());
                stringBuilder.append("2. Author info Updated");
            }else{
                stringBuilder.append("2. Author info is empty");
            }
            stringBuilder.append("\n");
            if(updateBook.getGenres()!=null && updateBook.getGenres().size() > 0) {
                book1.setGenres(updateBook.getGenres());
                stringBuilder.append("3. Genre info Updated");
            }else{
                stringBuilder.append("3. Genre info is empty");
            }
            stringBuilder.append("\n");
            if(updateBook.getLibraryBranches()!=null && updateBook.getLibraryBranches().size() > 0) {
                book1.setLibraryBranches(updateBook.getLibraryBranches());
                stringBuilder.append("4. Library branch info Updated");
            }else{
                stringBuilder.append("4. Library branch info is empty");
            }
            BOOK_UPDATE_RESPONSE_MESSAGE = "Book :" + updateBook.getBook_name() + " and id :" + updateBook.getId() + " and following updates \n"+ stringBuilder;
            bookRepository.save(book1);
        }
        return book1;
    }

    public Book bookAuthorSave(int book_id, Author author) {
        Book book = bookRepository.findById(book_id).orElse(null);
        if (book != null) {
            book.getAuthors().add(author);
            bookRepository.save(book);
        }
        return book;

    }
    public Book bookGenreSave(int book_id, Genre genre) {
        Book book = bookRepository.findById(book_id).orElse(null);
        if (book != null) {
            book.getGenres().add(genre);
            bookRepository.save(book);
        }
        return book;

    }
    public Book bookBranchSave(int book_id, LibraryBranch libraryBranch) {
        Book book = bookRepository.findById(book_id).orElse(null);
        if (book != null) {
            book.getLibraryBranches().add(libraryBranch);
            bookRepository.save(book);
        }
        return book;

    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(bookRepository.findAll());
    }

    public Book getBookById(int id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);
    }
    public Boolean deleteById(int id){

        if(getBookById(id)!=null){
            bookRepository.deleteById(id);
            return DELETED_FALG;
        }else {
            return !DELETED_FALG;

        }
    }


}
