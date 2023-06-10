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
    private Long historyId;

    @Schema(description = "用户邮箱或ID", example = "example@example.com")
    private String mailOrId;

    @Schema(description = "TMDB ID", example = "12345")
    private Long tmdbId;

    // Constructors, getters, and setters

    public UserHistory() {
    }

    public UserHistory(String mailOrId, Long tmdbId) {
        this.mailOrId = mailOrId;
        this.tmdbId = tmdbId;
    }

    public UserHistory(Long historyId, String mailOrId) {
        this.historyId = historyId;
        this.mailOrId = mailOrId;
    }

    public Long getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Long historyId) {
        this.historyId = historyId;
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

