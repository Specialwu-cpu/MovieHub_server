package com.moviehub.server.service;

import com.moviehub.server.entity.User;

import java.util.List;

/**
 * @Project ：server
 * @File ：IUserService.java
 * @IDE ：IntelliJ IDEA
 * @Author ：wsh
 * @Date ：2023/3/15 10:06
 **/

public interface IUserService {

//    public User save(String mail_or_id, String user_name, String password);

    public List<User> findAll();
}
