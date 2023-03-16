package com.moviehub.server.controller;

import com.moviehub.server.entity.User;
import com.moviehub.server.service.IUserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService iUserService;

//    @RequestMapping("/register")
//    public User register(@RequestBody Map<String, String> map) throws SQLException {
//        String mail_or_id = map.get("mail_or_id");
//        String password = map.get("password");
//        String user_name = map.get("user_name");
//        System.out.println(map);
//
//
//        User yes_or_no = iUserService.save(mail_or_id, user_name, password);
//        List<User> aaa = iUserService.findAll();
//        System.out.println(aaa);
//        return null;
//        //业务逻辑：mail存在了怎么办，password不符合要求怎么办，user_name不符合需求怎么办
//    }

    @GetMapping("/findAll")
    public List<User> findAll() { return iUserService.findAll(); }
}
