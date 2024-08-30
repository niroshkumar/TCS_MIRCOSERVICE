package com.tcs.LibraryManagementSystem.repository;

import com.tcs.LibraryManagementSystem.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
