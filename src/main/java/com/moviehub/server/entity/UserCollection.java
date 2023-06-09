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
    private Long collectionId;

    @Schema(description = "用户邮箱或ID", example = "example@example.com")
    private String mailOrId;

    @Schema(description = "TMDB ID", example = "12345")
    private Long tmdbId;

    // Constructors, getters, and setters

    public UserCollection() {
    }

    public UserCollection(String mailOrId, Long tmdbId) {
        this.mailOrId = mailOrId;
        this.tmdbId = tmdbId;
    }

    public UserCollection(Long collectionId, String mailOrId) {
        this.collectionId = collectionId;
        this.mailOrId = mailOrId;
    }

    public Long getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Long collectionId) {
        this.collectionId = collectionId;
    }

    public String getMailOrId() {
        return mailOrId;
    }

    public void setMailOrId(String mailOrId) {
        this.mailOrId = mailOrId;
    }

    public Long getTmdbId() {
        return tmdbId;
    }

    public void setTmdbId(Long tmdbId) {
        this.tmdbId = tmdbId;
    }
}

