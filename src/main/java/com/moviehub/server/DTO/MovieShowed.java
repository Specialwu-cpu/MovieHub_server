package com.moviehub.server.DTO;

import java.time.LocalDate;

public class MovieShowed {
    private Long tmdb_id;
    private LocalDate releaseDate;
    private String title;
    private String posterPath;

    private Float voteAverage;

    public MovieShowed(Long tmdb_id, LocalDate releaseDate, String title, String posterPath, Float voteAverage) {
        this.tmdb_id = tmdb_id;
        this.releaseDate = releaseDate;
        this.title = title;
        this.posterPath = posterPath;
        this.voteAverage = voteAverage;
    }

    public Long getTmdb_id() {
        return tmdb_id;
    }

    public void setTmdb_id(Long tmdb_id) {
        this.tmdb_id = tmdb_id;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Float voteAverage) {
        this.voteAverage = voteAverage;
    }
}
