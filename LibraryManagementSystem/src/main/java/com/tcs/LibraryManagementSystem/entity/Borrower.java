package com.tcs.LibraryManagementSystem.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Borrower {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String borrowerName;

    @Column
    private String borrowerEmail;

    @Column
    private String borrowerDOB;

    @Column
    private String borrowerPhone;

    @ManyToMany(fetch=FetchType.LAZY,
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name="book_borrower",
            joinColumns=@JoinColumn(name="borrower_id"),
            inverseJoinColumns=@JoinColumn(name="book_id")
    )
    private List<Book> books ;

    public Borrower(String borrowerName, String borrowerEmail, String borrowerDOB, String borrowerPhone) {
        this.borrowerName = borrowerName;
        this.borrowerEmail = borrowerEmail;
        this.borrowerDOB = borrowerDOB;
        this.borrowerPhone = borrowerPhone;
    }

    public Borrower() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public String getBorrowerEmail() {
        return borrowerEmail;
    }

    public void setBorrowerEmail(String borrowerEmail) {
        this.borrowerEmail = borrowerEmail;
    }

    public String getBorrowerDOB() {
        return borrowerDOB;
    }

    public void setBorrowerDOB(String borrowerDOB) {
        this.borrowerDOB = borrowerDOB;
    }

    public String getBorrowerPhone() {
        return borrowerPhone;
    }

    public void setBorrowerPhone(String borrowerPhone) {
        this.borrowerPhone = borrowerPhone;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Borrower{" +
                "id=" + id +
                ", borrowerName='" + borrowerName + '\'' +
                ", borrowerEmail='" + borrowerEmail + '\'' +
                ", borrowerDOB='" + borrowerDOB + '\'' +
                ", borrowerPhone='" + borrowerPhone + '\'' +
                '}';
    }
}
