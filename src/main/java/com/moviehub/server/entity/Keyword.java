package com.moviehub.server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "keyword")
@Getter
@Setter
public class Keyword {
    @Id
    @Column(name = "keyword_id", nullable = false)
    private Integer keywordID;

    @Column(name = "keyword_name", nullable = false)
    private String keywordName;
}
