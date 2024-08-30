package com.tcs.LibraryManagementSystem.service;

import com.tcs.LibraryManagementSystem.entity.Author;
import com.tcs.LibraryManagementSystem.entity.Book;
import com.tcs.LibraryManagementSystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.tcs.LibraryManagementSystem.utility.ResponceUpdate.AUTHOR_UPDATE_RESPONSE_MESSAGE;
import static com.tcs.LibraryManagementSystem.utility.ResponceUpdate.DELETED_FALG;

@Service
public class AuthorService {

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

    public Author authorSave(Author author) {
        return authorRepository.save(author);

    }

    public Author authorUpdate(int author_id, Author updateAuthor) {
        StringBuilder stringBuilder=new StringBuilder();
        Author author1 = authorRepository.findById(author_id).orElse(null);
        if (author1 != null) {
            if(updateAuthor.getAuthor_name() !=null && !updateAuthor.getAuthor_name().isEmpty()) {
                author1.setAuthor_name(updateAuthor.getAuthor_name());
                stringBuilder.append("Author info Updated");
            }else {
                stringBuilder.append("Author info is empty");
            }
            stringBuilder.append(" and ");
            if(updateAuthor.getBooks()!=null && updateAuthor.getBooks().size() > 0) {
                author1.setBooks(updateAuthor.getBooks());
                stringBuilder.append("Book info Updated");
            }else{
                stringBuilder.append("Book info is empty");
            }
            AUTHOR_UPDATE_RESPONSE_MESSAGE = "Author :" + updateAuthor.getAuthor_name() + " and id :" + updateAuthor.getId() + " "+ stringBuilder;
            authorRepository.save(author1);
        }
      return author1;
    }

    public Author authorBookSave(int author_id, Book book) {
        Author author1 = authorRepository.findById(author_id).orElse(null);
        if (author1 != null) {
            author1.getBooks().add(book);
            authorRepository.save(author1);
        }
        return author1;

    }

    public List<Author> getAllAuthor() {
        return new ArrayList<>(authorRepository.findAll());
    }

    public Author getAuthorById(int id) {
        Optional<Author> author = authorRepository.findById(id);
        return author.orElse(null);
    }

    public Boolean deleteById(int id){

         if(getAuthorById(id)!=null){
            authorRepository.deleteById(id);
            return DELETED_FALG;
         }else {
             return !DELETED_FALG;

         }
    }


}
