package com.moviehub.server.controller;

import com.moviehub.server.authorization.annotation.Authorization;
import com.moviehub.server.service.IUserService;
import com.moviehub.server.service.IVerifyCodeService;
import com.moviehub.server.util.*;
//import io.swagger.annotations.Api;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

//@Api(tags = "用户接口")
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService iUserService;

    @Resource
    private IVerifyCodeService iVerifyCodeService;

    @PostMapping(value = "loginWithPassword")
    public BaseResponse login(@RequestBody Map<String, String> map) {
        return iUserService.login(map.get("mail_or_id"), map.get("password"));
    }

    @PostMapping(value = "/register")
    public BaseResponse register(@RequestBody Map<String, String> map) {
        return iUserService.register(map.get("mail_or_id"), map.get("password"), map.get("user_name"), map.get("verify_code"));
    }

//    @Authorization
    @PostMapping(value = "/sendVerifyCode")
    public BaseResponse sendVerifyCode(@RequestBody Map<String, String> map) {
        try {
            return iVerifyCodeService.sendVerifyCode(map.get("email"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
