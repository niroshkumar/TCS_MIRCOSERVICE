package com.tcs.LibraryManagementSystem.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Genre {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String genre_name;
    @ManyToMany(fetch=FetchType.LAZY,
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name="genre_book",
            joinColumns=@JoinColumn(name="genre_id"),
            inverseJoinColumns=@JoinColumn(name="book_id")
    )
    private List<Book> books;

    public Genre(String genre_name) {
        this.genre_name = genre_name;
    }

    public Genre() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenre_name() {
        return genre_name;
    }

    public void setGenre_name(String genre_name) {
        this.genre_name = genre_name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", genre_name='" + genre_name + '\'' +
                '}';
    }
}
