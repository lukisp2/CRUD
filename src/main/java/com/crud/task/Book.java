package com.crud.task;

@javax.persistence.Entity
@lombok.Data
public class Book {

    @javax.persistence.GeneratedValue
    private long id;

    private String title;

    private String author;

}
