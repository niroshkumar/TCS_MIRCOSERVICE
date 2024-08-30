package com.tcs.LibraryManagementSystem.repository;

import com.tcs.LibraryManagementSystem.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {

}