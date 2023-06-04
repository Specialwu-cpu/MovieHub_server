package com.moviehub.server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

/**
 * @Project ：server
 * @File ：Cast.java
 * @IDE ：IntelliJ IDEA
 * @Author ：wsh ruan
 * @Date ：2023/4/27 15:13
 **/
@Entity
@Getter
@Table(name = "cast")
public class Cast {

    @Id
    @Column(name = "credit_id")
    private String creditId;

    @Column(name = "tmdb_id")
    private Long tmdbId;

    @Column(name = "character")
    private String character;

    @Column(name = "order")
    private int order;

    @Column(name = "credit_url")
    private String creditUrl;

    @Column(name = "name")
    private String name;

    // 其他属性和方法

}

