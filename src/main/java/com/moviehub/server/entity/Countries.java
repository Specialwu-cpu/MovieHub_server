package com.moviehub.server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "countries")
@Getter
@Setter
public class Countries {
    @Id
    @Column(name = "country_abbreviate", nullable = false)
    private String countryAbbreviate;

    @Column(name = "country_name", nullable = false)
    private String countryName;

}
