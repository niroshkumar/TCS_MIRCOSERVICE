package com.tcs.LibraryManagementSystem.service;

import com.tcs.LibraryManagementSystem.entity.Author;
import com.tcs.LibraryManagementSystem.entity.Book;
import com.tcs.LibraryManagementSystem.entity.Borrower;
import com.tcs.LibraryManagementSystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.tcs.LibraryManagementSystem.utility.ResponceUpdate.BORROWER_UPDATE_RESPONSE_MESSAGE;
import static com.tcs.LibraryManagementSystem.utility.ResponceUpdate.DELETED_FALG;

@Service
public class BorrowerService {

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


    public Borrower borrowerSave(Borrower borrower) {
        return borrowerRepository.save(borrower);

    }

    public Borrower borrowerUpdate(int borrower_id, Borrower updateBorrower) {
        StringBuilder stringBuilder=new StringBuilder();
        Borrower borrower1 = borrowerRepository.findById(borrower_id).orElse(null);
        if (borrower1 != null) {
            borrower1.setBorrowerName(updateBorrower.getBorrowerName());
            borrower1.setBorrowerDOB(updateBorrower.getBorrowerDOB());
            borrower1.setBorrowerEmail(updateBorrower.getBorrowerEmail());
            borrower1.setBorrowerPhone(updateBorrower.getBorrowerPhone());

            BORROWER_UPDATE_RESPONSE_MESSAGE = "Borrower :" + updateBorrower.getBorrowerName() + " and id :" + updateBorrower.getId() + " created";
            borrowerRepository.save(borrower1);
        }
        return borrower1;
    }


    public List<Borrower> getAllBorrowers() {
        return new ArrayList<>(borrowerRepository.findAll());
    }

    public Borrower getBorrowerById(int id) {
        Optional<Borrower> borrower = borrowerRepository.findById(id);
        return borrower.orElse(null);
    }

    public Borrower borrowerBookSave(int id, Book book) {
        Borrower borrower1 = null;
        Optional<Borrower> borrower = borrowerRepository.findById(id);
        if(borrower.isPresent()){
            borrower1=borrower.get();
            if(borrower1.getBooks()!=null && borrower1.getBooks().size() >0){
                borrower1.getBooks().add(book);
                borrowerRepository.save(borrower1);
            }
        }
        return borrower1 ;

    }

    public Boolean deleteById(int id){

        if(getBorrowerById(id)!=null){
            borrowerRepository.deleteById(id);
            return DELETED_FALG;
        }else {
            return !DELETED_FALG;

        }
    }

}
