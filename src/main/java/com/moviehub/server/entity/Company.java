package com.moviehub.server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Table(name = "company")
@Setter
public class Company {
    @Id
    @Column(name = "company_id", nullable = false)
    private Integer companyID;

    @Column(name = "company_name", nullable = false)
    private String companyName;
}
