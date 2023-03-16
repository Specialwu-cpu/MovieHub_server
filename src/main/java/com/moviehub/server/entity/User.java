package com.moviehub.server.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Proxy;

import java.util.Arrays;

@Data
//@Proxy(lazy = false)
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "mail_or_id", length = 10)
    private String mail_or_id;
    @Column(name = "user_name")
    private String user_name;
    @Column(name = "password")
    private String password;
    @Lob
    @Column(columnDefinition="text")
    private String style_text;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name="graph", columnDefinition="longblob")
    private byte[] graph;

}
