package com.tcs.LibraryManagementSystem.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Author {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private String author_name;

    @ManyToMany(fetch=FetchType.LAZY,
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name="author_book",
            joinColumns=@JoinColumn(name="author_id"),
            inverseJoinColumns=@JoinColumn(name="book_id")
    )
    private List<Book> books;

    public Author() {

    }
    public Author(String author_name, List<Book> books) {
        this.author_name = author_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", author_name=" + author_name +
                '}';
    }
}
