package com.moviehub.server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "verify_code")
public class verifyCodeTable {
    @Id
    @Column(name = "register_id")
    private String register_id;

    @Column(name = "verify_code", length = 10)
    private String verify_code;

    @Column(name = "code_time")
    private Timestamp code_time;

    public String getRegister_id() {
        return register_id;
    }

    public void setRegister_id(String register_id) {
        this.register_id = register_id;
    }

    public String getVerify_code() {
        return verify_code;
    }

    public void setVerify_code(String verify_code) {
        this.verify_code = verify_code;
    }

    public Timestamp getCode_time() {
        return code_time;
    }

    public void setCode_time(Timestamp code_time) {
        this.code_time = code_time;
    }
}
