package com.moviehub.server.entity;

/**
 * @Project ：server
 * @File ：UserCollection.java
 * @IDE ：IntelliJ IDEA
 * @Author ：wsh ruan
 * @Date ：2023/6/9 10:21
 **/

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Schema(description = "用户收藏实体")
public class UserCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "收藏ID", example = "1")
    private Integer collection_id;

    @Schema(description = "用户邮箱或ID", example = "example@example.com")
    private String mail_or_id;

    @Schema(description = "TMDB ID", example = "12345")
    private Long tmdb_id;

    // Constructors, getters, and setters

    public UserCollection() {
    }

    public UserCollection(String mail_or_id, Long tmdb_id) {
        this.mail_or_id = mail_or_id;
        this.tmdb_id = tmdb_id;
    }

    public Integer getCollection_id() {
        return collection_id;
    }

    public void setCollection_id(Integer collection_id) {
        this.collection_id = collection_id;
    }

    public String getMail_or_id() {
        return mail_or_id;
    }

    public void setMail_or_id(String mail_or_id) {
        this.mail_or_id = mail_or_id;
    }

    public Long getTmdb_id() {
        return tmdb_id;
    }

    public void setTmdb_id(Long tmdb_id) {
        this.tmdb_id = tmdb_id;
    }
}

