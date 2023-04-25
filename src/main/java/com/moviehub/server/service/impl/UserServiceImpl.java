package com.moviehub.server.service.impl;

import com.moviehub.server.entity.User;
import com.moviehub.server.repository.UserRepository;
import com.moviehub.server.service.IUserService;
import com.moviehub.server.service.IVerifyCodeService;
import com.moviehub.server.util.BaseResponse;
import com.moviehub.server.util.DES;
import com.moviehub.server.util.RedisUtil;
import com.moviehub.server.util.TimeManager;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;


/**
 * @Project ：server
 * @File ：UserServiceImpl.java
 * @IDE ：IntelliJ IDEA
 * @Author ：wsh ruan
 * @Date ：2023/3/15 10:07
 **/
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private IVerifyCodeService iVerifyCodeService;

    @Override
    public BaseResponse login(String mail_or_id, String password) {
        if (!emailInDatabase(mail_or_id)) {
//            return JsonCreater.getJson(ResponeCode.BadGateway.value, null, "该账号未注册");
            return BaseResponse.error("该账号未注册");
        }
        List<User> theUser = emailPasswordLogin(mail_or_id, password);
        if (theUser.isEmpty()){
//            return JsonCreater.getJson(ResponeCode.BadGateway.value, null, "该账号密码错误");
            return BaseResponse.error("该账号密码错误");
        }
        User thisUser = theUser.get(0);
        String takeMail = thisUser.getMail_or_id();
        String takePassword = thisUser.getPassword();
        String key = "MOVIE HUB";
        String now = TimeManager.getNowDateTime().toString();
        String token = DES.getEncryptString(takeMail + "," + takePassword + "," + key + "," + now);
        HashMap<String, String> data = new HashMap<>();
        data.put("token", token);

        //设置redis数据库
        Jedis jedis = RedisUtil.getJedis();
        jedis.set(takeMail, token);
        jedis.expire(takeMail, 18000L);
//        return JsonCreater.getJson(data);
        return BaseResponse.success(data);
    }

    @Override
    public BaseResponse register(String mail_or_id, String password, String user_name, String verify_code) {
        if (emailInDatabase(mail_or_id)) {
//            return JsonCreater.getJson(ResponeCode.BadGateway.value, null, "该账号已注册");
            return BaseResponse.error("该账号已注册");
        }
        if (invalidPassword(password)){
//            return JsonCreater.getJson(ResponeCode.BadGateway.value, null, "密码不符合");
            return BaseResponse.error("密码不符合");
        }
        if (invalidUserName(user_name)) {
//            return JsonCreater.getJson(ResponeCode.BadGateway.value, null, "用户名不符合");
            return BaseResponse.error("用户名不符合");
        }
        if (iVerifyCodeService.wrongVerifyCode(mail_or_id, verify_code)) {
//            return JsonCreater.getJson(ResponeCode.BadGateway.value, null, "验证码错误或失效");
            return BaseResponse.error("验证码错误或失效");
        }

        User newUser = save(mail_or_id, user_name, password);
        if (newUser == null) {
//            return JsonCreater.getJson(ResponeCode.INTERNAL_SERVER_ERROR.value, null, ResponeCode.INTERNAL_SERVER_ERROR.msg);
            return BaseResponse.badrequest("注册失败");
        }
        return BaseResponse.success(newUser);
        //业务逻辑：mail存在了怎么办，password不符合要求怎么办，user_name不符合需求怎么办
    }

    @Override
    public User save(String mail_or_id, String user_name, String password) {
        if (mail_or_id != null){
            User aNewUser = new User();
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
    public List<User> findAll() {
        System.out.println("我在service" + userRepository.findAll());
        return userRepository.findAll();
    }

    @Override
    public boolean emailInDatabase(String mail_or_id) {
        return userRepository.findById(mail_or_id).isPresent();
    }

    @Override
    public List<User> emailPasswordLogin(String mail_or_id, String password) {
        return userRepository.findByMailOrIdAndPassword(mail_or_id, password);
    }


    @Override
    public boolean invalidPassword(String password) {
        return password.length() < 6 || password.length() > 255;
    }

    @Override
    public boolean invalidUserName(String userName) {
        return userName.length() < 5 || userName.length() > 255;
    }
}
