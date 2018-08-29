package com.model;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "book_table")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    @Column(name = "book_number ")
    private Long bookNumber;

    @Column(name = "book_author")
    private String author;

    @Column(name = "book_name")
    private String bookName;

        @Column(name = "book_year")
    private Date year;

    @ManyToOne
    @JoinColumn(name = "publisher_id", referencedColumnName = "publisher_id")
    private Publisher publisher;


    //GETTER AND SETTERS




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public Long getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(Long bookNumber) {
        this.bookNumber = bookNumber;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

}
