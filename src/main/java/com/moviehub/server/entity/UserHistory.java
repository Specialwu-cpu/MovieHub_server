package com.moviehub.server.entity;

/**
 * @Project ：server
 * @File ：UserHistory.java
 * @IDE ：IntelliJ IDEA
 * @Author ：wsh ruan
 * @Date ：2023/6/9 10:20
 **/

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Schema(description = "用户历史记录实体")
public class UserHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "历史记录ID", example = "1")
    private Integer history_id;

    @Schema(description = "用户邮箱或ID", example = "example@example.com")
    private String mail_or_id;

    @Schema(description = "TMDB ID", example = "12345")
    private Long tmdb_id;

    // Constructors, getters, and setters

    public UserHistory() {
    }

    public UserHistory(Integer history_id, String mail_or_id, Long tmdb_id) {
        this.history_id = history_id;
        this.mail_or_id = mail_or_id;
        this.tmdb_id = tmdb_id;
    }

    public UserHistory(String mailOrId, Long tmdbId) {
        this.mail_or_id = mailOrId;
        this.tmdb_id = tmdbId;
    }

    public Integer getHistory_id() {
        return history_id;
    }

    public void setHistory_id(Integer history_id) {
        this.history_id = history_id;
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

