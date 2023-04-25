package com.moviehub.server.service;

import com.moviehub.server.entity.User;
import com.moviehub.server.util.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * @Project ：server
 * @File ：IUserService.java
 * @IDE ：IntelliJ IDEA
 * @Author ：wsh
 * @Date ：2023/3/15 10:06
 **/

public interface IUserService {
    BaseResponse login(String mail_or_id, String password);

    BaseResponse register(String mail_or_id, String password, String user_name, String verify_code);

    User save(String mail_or_id, String user_name, String password);

    List<User> findAll();

    boolean emailInDatabase(String mail_or_id);

    List<User> emailPasswordLogin(String mail_or_id, String password);

    boolean invalidPassword(String password);

    boolean invalidUserName(String userName);
}
