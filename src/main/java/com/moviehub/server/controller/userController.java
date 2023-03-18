package com.moviehub.server.controller;

import com.moviehub.server.authorization.annotation.Authorization;
import com.moviehub.server.entity.user;
import com.moviehub.server.service.IUserService;
import com.moviehub.server.service.IVerifyCodeService;
import com.moviehub.server.util.*;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class userController {
    @Resource
    private IUserService userservice;

    @Resource
    private IVerifyCodeService codeService;

    @RequestMapping("loginWithPassword")
    public Map<String, Object> login(@RequestBody Map<String, String> map) throws JSONException {
        String mail_or_id = map.get("mail_or_id");
        String password = map.get("password");
        if (!userservice.emailInDatabase(mail_or_id)) {
            return JsonCreater.getJson(ResponeCode.BadGateway.value, null, "该账号未注册");
        }
        List<user> theUser = userservice.emailPasswordLogin(mail_or_id, password);
        if (theUser.isEmpty()){
            return JsonCreater.getJson(ResponeCode.BadGateway.value, null, "该账号密码错误");
        }
        user thisUser = theUser.get(0);
        String takeMail = thisUser.getMail_or_id();
        String takePassword = thisUser.getPassword();
        String key = "MOVIEHUB";
        String now = TimeManager.getNowDateTime().toString();
        String token = DES.getEncryptString(takeMail + "," + takePassword + "," + key + "," + now);
        HashMap<String, String> data = new HashMap<>();
        data.put("token", token);

        //设置redis数据库
        Jedis jedis = RedisUtil.getJedis();
        jedis.set(takeMail, token);
        jedis.expire(takeMail, 18000L);

        return JsonCreater.getJson(data);

    }

    @RequestMapping("/register")
    public Map<String, Object> register(@RequestBody Map<String, String> map) throws Exception {
        String mail_or_id = map.get("mail_or_id");
        String password = map.get("password");
        String user_name = map.get("user_name");
        String veriCode = map.get("verify_code");
        if (userservice.emailInDatabase(mail_or_id)) {
            return JsonCreater.getJson(ResponeCode.BadGateway.value, null, "该账号已注册");
        }
        if (userservice.invalidPassword(password)){
            return JsonCreater.getJson(ResponeCode.BadGateway.value, null, "密码不符合");
        }
        if (userservice.invalidUserName(user_name)) {
            return JsonCreater.getJson(ResponeCode.BadGateway.value, null, "用户名不符合");
        }



        if (codeService.wrongVerifyCode(mail_or_id, veriCode)) {
            return JsonCreater.getJson(ResponeCode.BadGateway.value, null, "验证码错误或失效");
        }
        
        user newUser = userservice.save(mail_or_id, user_name, password);
        if (newUser == null) {
            return JsonCreater.getJson(ResponeCode.INTERNAL_SERVER_ERROR.value, null, ResponeCode.INTERNAL_SERVER_ERROR.msg);
        }
        return JsonCreater.getJson(newUser);
        //业务逻辑：mail存在了怎么办，password不符合要求怎么办，user_name不符合需求怎么办
    }
    @RequestMapping("/sendVerifyCode")
    @Authorization
    public Map<String, Object> sendVerifyCode(@RequestBody Map<String, String> map, HttpServletRequest request) throws Exception {
        String email = map.get("email");
//        Map<String, String[]> parameterMap = request.getParameterMap();
//        System.out.println(parameterMap.get("emaill")[0]+"我是我d email");
        boolean a = codeService.sendVerifyCode(email);
//        System.out.println(a);
        return JsonCreater.getJson(a);
    }
}
