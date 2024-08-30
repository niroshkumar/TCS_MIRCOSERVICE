package com.tcs.LibraryManagementSystem.repository;

import com.tcs.LibraryManagementSystem.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

}