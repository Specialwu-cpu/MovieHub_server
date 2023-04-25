package com.moviehub.server.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

/**
 * @Project ：server
 * @File ：History.java
 * @IDE ：IntelliJ IDEA
 * @Author ：wsh ruan
 * @Date ：2023/3/25 10:11
 **/
@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @Schema(title = "mail_or_id")
    @Column(name = "mail_or_id")
    private String mail_or_id;
    @Schema(title = "user_name")
    @Column(name = "user_name")
    private String user_name;
    @Schema(title = "password")
    @Column(name = "password")
    private String password;
    @Lob
    @Schema(title = "style_text")
    @Column(columnDefinition="text")
    private String style_text;
    @Lob
    @Schema(title = "graph")
    @Basic(fetch = FetchType.LAZY)
    @Column(name="graph", columnDefinition="longblob")
    private byte[] graph;

    public User() {

    }

    public String getMail_or_id() {
        return mail_or_id;
    }

    public void setMail_or_id(String mail_or_id) {
        this.mail_or_id = mail_or_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStyle_text() {
        return style_text;
    }

    public void setStyle_text(String style_text) {
        this.style_text = style_text;
    }

    public byte[] getGraph() {
        return graph;
    }

    public void setGraph(byte[] graph) {
        this.graph = graph;
    }
}
