package com.moviehub.server.entity;

import jakarta.persistence.*;
import lombok.Getter;

/**
 * @Project ：MovieHub-server
 * @File ：Genre.java
 * @IDE ：IntelliJ IDEA
 * @Author ：wsh
 * @Date ：2023/4/25 12:36
 **/
@Entity
@Getter
@Table(name = "genre")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private int id;

    @Column(name = "genre_name", length = 255)
    private String name;

    // 构造函数、Getter和Setter方法略去
}

