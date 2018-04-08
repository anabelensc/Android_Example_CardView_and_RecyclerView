package com.example.belensarabia.myapplication.models;

/**
 * Created by belensarabia on 1/4/18.
 */

public class Movie {

    private String name;
    private int poster;

    public Movie() {
    }

    public Movie(String name, int poster) {
        this.name = name;
        this.poster = poster;
    }

    public String getName() {
        return name;
    }

    public int getPoster() {
        return poster;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }
}
