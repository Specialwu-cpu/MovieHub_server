package com.moviehub.server.service.impl;

import com.moviehub.server.repository.UserRepository;
import com.moviehub.server.service.IUserService;
import jakarta.annotation.Resource;

/**
 * @Project ：server
 * @File ：UserServiceImpl.java
 * @IDE ：IntelliJ IDEA
 * @Author ：wsh
 * @Date ：2023/3/15 10:07
 **/
public class UserServiceImpl implements IUserService {
    @Resource
    private UserRepository userRepository;
}
