package com.integrador.grupo2android.proyectointegrador.Modelo.POJO;

public class Movie {

    private String title;
    private Integer id;
    private String poster_path;
    private String release_date;
    private String vote_average;
    private String vote_count;

    public Movie(String title, Integer id, String poster_path, String release_date, String vote_average, String vote_count) {
        this.title = title;
        this.id = id;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
    }

    public String getTitle() {
        return title;
    }

    public Integer getId() {
        return id;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getVote_average() {
        return vote_average;
    }

    public String getVote_count() {
        return vote_count;
    }
}
