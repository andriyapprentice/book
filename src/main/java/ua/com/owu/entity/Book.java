package ua.com.owu.entity;

import com.sun.javafx.beans.IDProperty;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString(exclude = "authors")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String nameBook;
    private String description;
    private int year;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY,mappedBy = "book")
    private List<Author> authors=new ArrayList<>();


    public Book(String nameBook, String description, int year) {
        this.nameBook = nameBook;
        this.description = description;
        this.year = year;
    }

    public Book() {
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
