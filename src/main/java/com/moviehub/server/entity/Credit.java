package com.moviehub.server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

/**
 * @Project ：server
 * @File ：Credit.java
 * @IDE ：IntelliJ IDEA
 * @Author ：wsh ruan
 * @Date ：2023/4/27 15:13
 **/
@Entity
@Getter
@Table(name = "credit")
public class Credit {
    @Id
    @Column(name = "credit_id")
    private String creditId;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "credit_url")
    private String creditUrl;

    // constructors, getters and setters
}

