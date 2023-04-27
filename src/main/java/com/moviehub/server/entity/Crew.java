package com.moviehub.server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

/**
 * @Project ：server
 * @File ：Crew.java
 * @IDE ：IntelliJ IDEA
 * @Author ：wsh ruan
 * @Date ：2023/4/27 15:14
 **/
@Entity
@Getter
@Table(name = "crew")
public class Crew {

    @Id
    @Column(name = "credit_id", length = 255)
    private String creditId;

    @Column(name = "tmdb_id", length = 10)
    private Long tmdbId;

    @Column(name = "job", length = 100)
    private String job;

    // constructors, getters and setters
}

