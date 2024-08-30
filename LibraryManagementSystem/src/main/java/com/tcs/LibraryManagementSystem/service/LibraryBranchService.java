package com.tcs.LibraryManagementSystem.service;

import com.tcs.LibraryManagementSystem.entity.Book;
import com.tcs.LibraryManagementSystem.entity.Genre;
import com.tcs.LibraryManagementSystem.entity.LibraryBranch;
import com.tcs.LibraryManagementSystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.tcs.LibraryManagementSystem.utility.ResponceUpdate.DELETED_FALG;
import static com.tcs.LibraryManagementSystem.utility.ResponceUpdate.LIB_BRANCH_UPDATE_RESPONSE_MESSAGE;

@Service
public class LibraryBranchService {

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

    public LibraryBranch libraryBranchSave(LibraryBranch libraryBranch) {
        return libraryBranchRepository.save(libraryBranch);

    }
    public LibraryBranch libraryBranchSaveBooks(int branch_id, Book updateBook) {
        LibraryBranch libraryBranch = null;
        Optional<LibraryBranch> libraryBranch1=libraryBranchRepository.findById(branch_id);
        if(libraryBranch1.isPresent()){
            libraryBranch = libraryBranch1.get();
            libraryBranch.getBooks().add(updateBook);
            libraryBranchRepository.save(libraryBranch);
        }
        return libraryBranch;

    }
    public LibraryBranch branchUpdate(int branch_id, LibraryBranch updateBranch) {
        LibraryBranch libraryBranch = libraryBranchRepository.findById(branch_id).orElse(null);
        if (libraryBranch != null) {
            libraryBranch.setLibraryBranchName(updateBranch.getLibraryBranchName());
            libraryBranch.setLibraryBranchLocation(updateBranch.getLibraryBranchLocation());
            libraryBranch.setWorkingHours(updateBranch.getWorkingHours());
            if(updateBranch.getBooks()!=null && updateBranch.getBooks().size() > 0){
                libraryBranch.setBooks(updateBranch.getBooks());
            }
            LIB_BRANCH_UPDATE_RESPONSE_MESSAGE = "Library branch :" + updateBranch.getLibraryBranchName() + " and id :" + updateBranch.getId() + " created";
            libraryBranchRepository.save(libraryBranch);
        }
        return libraryBranch;
    }

    public List<LibraryBranch> getAllBranches() {
        return new ArrayList<>(libraryBranchRepository.findAll());
    }

    public LibraryBranch getBranchById(int id) {
        Optional<LibraryBranch> libraryBranch = libraryBranchRepository.findById(id);
        return libraryBranch.orElse(null);
    }

    public Boolean deleteById(int id){

        if(getBranchById(id)!=null){
            libraryBranchRepository.deleteById(id);
            return DELETED_FALG;
        }else {
            return !DELETED_FALG;

        }
    }


}
