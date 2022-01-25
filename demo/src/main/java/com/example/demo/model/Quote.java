package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    private String author;
    private String text;
    private String image_link;
    private int user_id;

    public Quote() {
    }

    public Quote(int id, String author, String text, String image_link, int user_id) {
        this.id = id;
        this.author = author;
        this.text = text;
        this.image_link = image_link;
        this.user_id = user_id;
    }

    public Quote(String author, String text, String image_link) {
        this.author = author;
        this.text = text;
        this.image_link = image_link;
        this.user_id = user_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage_link() {
        return image_link;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }
}
