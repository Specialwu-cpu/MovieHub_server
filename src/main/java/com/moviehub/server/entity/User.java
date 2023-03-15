package com.moviehub.server.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "User")
//@TableName("user")
public class User {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
}
