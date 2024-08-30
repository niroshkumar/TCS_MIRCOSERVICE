package com.tcs.LibraryManagementSystem.repository;

import com.tcs.LibraryManagementSystem.entity.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowerRepository extends JpaRepository<Borrower, Integer> {

}
