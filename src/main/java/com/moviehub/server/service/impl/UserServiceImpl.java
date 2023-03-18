package com.moviehub.server.service.impl;

import com.moviehub.server.authorization.annotation.Authorization;
import com.moviehub.server.entity.user;
import com.moviehub.server.repository.UserRepository;
import com.moviehub.server.service.IUserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
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
            return null;
        }
    }

    @Override
    public List<user> findAll() {
        System.out.println("我在service" + userRepository.findAll());
        return userRepository.findAll();
    }

    @Override
    public boolean emailInDatabase(String mail_or_id) {

        return userRepository.findById(mail_or_id).isPresent();
    }

    @Override
    public List<user> emailPasswordLogin(String mail_or_id, String password) {
        return userRepository.findByMail_or_idAndPassword(mail_or_id, password);
    }


    @Override
    public boolean invalidPassword(String password) {
        if (password.length() < 6 || password.length() > 255){
            return true;
        }
        return false;
    }

    @Override
    public boolean invalidUserName(String userName) {
        if (userName.length() < 5 || userName.length() > 255) {
            return true;
        }
        return false;
    }
}
