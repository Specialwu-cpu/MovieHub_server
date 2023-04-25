package com.moviehub.server.entity;

import jakarta.persistence.*;

/**
 * @Project ：MovieHub-server
 * @File ：Genre.java
 * @IDE ：IntelliJ IDEA
 * @Author ：wsh
 * @Date ：2023/4/25 12:36
 **/
@Entity
@Table(name = "genre")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private Long id;

    @Column(name = "genre_name", length = 255)
    private String name;

    // 构造函数、Getter和Setter方法略去
}

