package com.example.belensarabia.myapplication.models;

/**
 * Created by belensarabia on 1/4/18.
 */

public class Movie {

    private String name;
    private int poster;
    private int punctuation;

    private static final int LIMIT_PUNCTUATION = 10;

    public Movie() {
    }

    public Movie(String name, int poster, int punctuation) {
        this.name = name;
        this.poster = poster;
        this.punctuation = punctuation;
    }

    public String getName() {
        return name;
    }

    public int getPoster() {
        return poster;
    }

    public int getPunctuation() {
        return punctuation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }

    public void setPunctuation(int punctuation) {
        this.punctuation = punctuation;
    }

    public void addPunctation(int punctuation) {
        if (this.punctuation < LIMIT_PUNCTUATION)
            this.punctuation += punctuation;
    }

}
