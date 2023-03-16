package com.moviehub.server.controller;

import com.moviehub.server.entity.user;
import com.moviehub.server.repository.UserRepository;
import com.moviehub.server.service.IUserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/user")
public class userController {
    @Resource
    private IUserService service;



    @RequestMapping("/register")
    public user register(@RequestBody Map<String, String> map) throws SQLException {
        String mail_or_id = map.get("mail_or_id");
        String password = map.get("password");
        String user_name = map.get("user_name");
        System.out.println(map);

        
        
        
        user yes_or_no = service.save(mail_or_id, user_name, password);
        List<user> aaa = service.findAll();
        System.out.println(aaa);
        return null;
        //业务逻辑：mail存在了怎么办，password不符合要求怎么办，user_name不符合需求怎么办
    }
}
