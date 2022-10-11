package com.jbsa.libraryexercise;

public class BookRequest {

    private String reference;

    public BookRequest(String reference) {
        this.reference = reference;
    }

    public BookRequest() {

    }
    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
