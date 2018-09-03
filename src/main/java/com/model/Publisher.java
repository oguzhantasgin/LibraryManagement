package com.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "publishing_house_table")
public class Publisher implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisher_id")
    private Long publisherId;

    @Column(name = "publisher_name", unique = true)
    private String publisherName;

    @Column(name = "publisher_address")
    private String publisherAddress;


    @Column(name = "publisher_phone")
    private Long publisherPhone;


/*
    @OneToMany(targetEntity = Book.class,mappedBy = "publisher",
            cascade = CascadeType.ALL)
    private List<Book> books;*/


    //GETTERS AND SETTERS


    public Long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getPublisherAddress() {
        return publisherAddress;
    }

    public void setPublisherAddress(String publisherAddress) {
        this.publisherAddress = publisherAddress;
    }

    public Long getPublisherPhone() {
        return publisherPhone;
    }

    public void setPublisherPhone(Long publisherPhone) {
        this.publisherPhone = publisherPhone;
    }

   /* public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }*/
}
