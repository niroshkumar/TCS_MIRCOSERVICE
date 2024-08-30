package com.tcs.LibraryManagementSystem.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class LibraryBranch {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String libraryBranchName;

    @Column
    private String libraryBranchLocation;

    @Column
    private String workingHours;

    public LibraryBranch(String libraryBranchName, String libraryBranchLocation, String workingHours) {
        this.libraryBranchName = libraryBranchName;
        this.libraryBranchLocation = libraryBranchLocation;
        this.workingHours = workingHours;
    }

    public LibraryBranch() {

    }
    @ManyToMany(fetch=FetchType.LAZY,
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name="book_branch",
            joinColumns=@JoinColumn(name="branch_id"),
            inverseJoinColumns=@JoinColumn(name="book_id")
    )
    private List<Book> books ;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibraryBranchName() {
        return libraryBranchName;
    }

    public void setLibraryBranchName(String libraryBranchName) {
        this.libraryBranchName = libraryBranchName;
    }

    public String getLibraryBranchLocation() {
        return libraryBranchLocation;
    }

    public void setLibraryBranchLocation(String libraryBranchLocation) {
        this.libraryBranchLocation = libraryBranchLocation;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    @Override
    public String toString() {
        return "LibraryBranch{" +
                "id=" + id +
                ", libraryBranchName='" + libraryBranchName + '\'' +
                ", libraryBranchLocation='" + libraryBranchLocation + '\'' +
                ", workingHours='" + workingHours + '\'' +
                '}';
    }
}
