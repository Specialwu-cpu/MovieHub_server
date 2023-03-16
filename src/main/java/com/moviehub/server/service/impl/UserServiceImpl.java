package com.moviehub.server.service.impl;

import com.moviehub.server.entity.user;
import com.moviehub.server.repository.UserRepository;
import com.moviehub.server.service.IUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @Project ：server
 * @File ：UserServiceImpl.java
 * @IDE ：IntelliJ IDEA
 * @Author ：wsh
 * @Date ：2023/3/15 10:07
 **/
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserRepository userRepository;



    @Override
    public user save(String mail_or_id, String user_name, String password) {
        if (mail_or_id != null){
            user aNewUser = new user();
            aNewUser.setMail_or_id(mail_or_id);
            if (user_name != null){
                aNewUser.setUser_name(user_name);
            }
            if (password != null){
                aNewUser.setPassword(password);
            }
            userRepository.save(aNewUser);

            return aNewUser;
        }
        else {
            System.out.printf("呜呼呜呼");
            return null;
        }
    }

    @Override
    public List<user> findAll() {
        System.out.println("我在service" + userRepository.findAll());
        return userRepository.findAll();
    }
}
