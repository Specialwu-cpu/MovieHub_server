package com.moviehub.server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_feature")
@Getter
@Setter
public class UserFeature {

    @Id
    @Column(name = "mail_or_id")
    private String mailOrId;

    @Column(name = "rating_mean")
    private Float ratingMean;

    @Column(name = "rating_count")
    private Integer ratingCount;

    @Column(name = "timestamp_max")
    private Long timestampMax;

    @Column(name = "runtime_mean")
    private Float runtimeMean;

    @Column(name = "genre_18")
    private Integer genre18;

    @Column(name = "genre_80")
    private Integer genre80;

    @Column(name = "genre_35")
    private Integer genre35;

    @Column(name = "genre_28")
    private Integer genre28;

    @Column(name = "genre_53")
    private Integer genre53;

    @Column(name = "genre_12")
    private Integer genre12;

    @Column(name = "genre_878")
    private Integer genre878;

    @Column(name = "genre_16")
    private Integer genre16;

    @Column(name = "genre_10751")
    private Integer genre10751;

    @Column(name = "genre_10749")
    private Integer genre10749;

    @Column(name = "genre_9648")
    private Integer genre9648;

    @Column(name = "genre_10402")
    private Integer genre10402;

    @Column(name = "genre_27")
    private Integer genre27;

    @Column(name = "genre_14")
    private Integer genre14;

    @Column(name = "genre_99")
    private Integer genre99;

    @Column(name = "genre_10752")
    private Integer genre10752;

    @Column(name = "genre_37")
    private Integer genre37;

    @Column(name = "genre_36")
    private Integer genre36;

    @Column(name = "genre_10769")
    private Integer genre10769;

    @Column(name = "genre_10770")
    private Integer genre10770;
}
