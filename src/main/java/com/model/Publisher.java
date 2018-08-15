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
    private int publisherId;

    @Column(name = "publisher_name")
    private String publisherName;

    @Column(name = "publisher_address")
    private String publisherAddress;

    @OneToMany(targetEntity = Book.class, mappedBy = "publisher_id",
            cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Book> books;



    //GETTERS AND SETTERS


    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
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

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
