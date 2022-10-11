package com.jbsa.libraryexercise;


import javax.persistence.*;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String reference;
    private String title;
    @Column(length = 700)
    private String review;

    public Book() {}

    public Book(String reference, String title, String description) {
        this.reference = reference;
        this.title = title;
        this.review = description;
    }

    public Book(Long id, String reference, String title, String description) {
        this.id =id;
        this.reference = reference;
        this.title = title;
        this.review = description;
    }

    public Long getId(){
        return id;
    }

    public String getReview() {
        return review;
    }

    public String getReference() {
        return reference;
    }

    public String getTitle() {
        return title;
    }
}
