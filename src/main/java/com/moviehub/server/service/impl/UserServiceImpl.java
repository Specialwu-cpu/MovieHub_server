package com.moviehub.server.service.impl;

import com.moviehub.server.entity.Movie;
import com.moviehub.server.entity.User;
import com.moviehub.server.entity.UserCollection;
import com.moviehub.server.entity.UserHistory;
import com.moviehub.server.repository.MovieRepository;
import com.moviehub.server.repository.UserCollectionRepository;
import com.moviehub.server.repository.UserHistoryRepository;
import com.moviehub.server.repository.UserRepository;
import com.moviehub.server.service.IUserService;
import com.moviehub.server.service.IVerifyCodeService;
import com.moviehub.server.util.BaseResponse;
import com.moviehub.server.util.DES;
import com.moviehub.server.util.TimeManager;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;


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
    private UserHistoryRepository userHistoryRepository;

    @Resource
    private UserCollectionRepository userCollectionRepository;

    @Resource
    private MovieRepository movieRepository;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

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
        ValueOperations<String, Object>valueOperations = redisTemplate.opsForValue();
        valueOperations.set(takeMail, token, 30, TimeUnit.MINUTES);

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
    public BaseResponse resetPassword(String mailOrId, String oldPasswordOne, String oldPasswordTwo, String newPassword) {
        if (oldPasswordOne != oldPasswordTwo) {
            return BaseResponse.error("两次输入密码不一致");
        }
        User user = userRepository.findByMailOrId(mailOrId);
        if (user.getPassword() != oldPasswordOne) {
            return BaseResponse.error("密码错误");
        }
        if (invalidPassword(newPassword)){
//            return JsonCreater.getJson(ResponeCode.BadGateway.value, null, "密码不符合");
            return BaseResponse.error("密码不符合");
        }
        if (mailOrId != null){
            User aNewUser = new User();
            aNewUser.setMail_or_id(mailOrId);
            if (newPassword != null){
                aNewUser.setPassword(newPassword);
            }
            userRepository.save(aNewUser);
            return BaseResponse.success("修改密码成功");
        }
        else {
            return BaseResponse.error("修改密码失败");
        }
    }

    @Override
    public BaseResponse forgetPassword(String mail_or_id, String newPasswordOne, String newPasswordTwo, String verifyCode) {
        if (newPasswordOne != newPasswordTwo) {
            return BaseResponse.error("两次输入密码不一致");
        }
        if (emailInDatabase(mail_or_id)) {
//            return JsonCreater.getJson(ResponeCode.BadGateway.value, null, "该账号已注册");
            return BaseResponse.error("该账号已注册");
        }
        if (invalidPassword(newPasswordOne)){
//            return JsonCreater.getJson(ResponeCode.BadGateway.value, null, "密码不符合");
            return BaseResponse.error("密码不符合");
        }
        if (iVerifyCodeService.wrongVerifyCode(mail_or_id, verifyCode)) {
//            return JsonCreater.getJson(ResponeCode.BadGateway.value, null, "验证码错误或失效");
            return BaseResponse.error("验证码错误或失效");
        }
        User newUser = save(mail_or_id, newPasswordOne);
        if (newUser == null) {
//            return JsonCreater.getJson(ResponeCode.INTERNAL_SERVER_ERROR.value, null, ResponeCode.INTERNAL_SERVER_ERROR.msg);
            return BaseResponse.badrequest("注册失败");
        }
        return BaseResponse.success(newUser);
        //业务逻辑：mail存在了怎么办，password不符合要求怎么办，user_name不符合需求怎么办
    }

    @Override
    public BaseResponse updateUser(String mailOrId, String userName, String styleText) {
        User user = userRepository.findByMailOrId(mailOrId);
        if (user == null) {
            return BaseResponse.error("User not found");
        }
        user.setUser_name(userName);
        user.setStyle_text(styleText);
        userRepository.save(user);
        return BaseResponse.success();
    }

    @Override
    public BaseResponse updateAvatar(String mailOrId, MultipartFile file) {
        User user = userRepository.findByMailOrId(mailOrId);
        if (user == null) {
            return BaseResponse.error("User not found");
        }

        // 处理上传的头像文件
        try {
            byte[] avatarBytes = file.getBytes();
            user.setGraph(avatarBytes);
            // 可以根据需要对头像进行进一步处理，例如压缩、裁剪等

            // 保存更新后的用户信息
            userRepository.save(user);
            return BaseResponse.success();
        } catch (IOException e) {
            e.printStackTrace();
            return BaseResponse.error("Failed to update avatar");
        }
    }

    private User save(String mailOrId, String newPassword) {
        if (mailOrId != null){
            User aNewUser = new User();
            aNewUser.setMail_or_id(mailOrId);
            if (newPassword != null){
                aNewUser.setPassword(newPassword);
            }
            userRepository.save(aNewUser);
            return aNewUser;
        }
        else {
            return null;
        }
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

    @Override
    public BaseResponse getUserInfo(String mailOrId) {
        User user = userRepository.findByMailOrId(mailOrId);
        return BaseResponse.success(user);
    }

    @Override
    public BaseResponse getUserHistory(String mailOrId, int page) {
        final String key = "user:history_start" + page;
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();

        if (page == 0){
            HashMap<String, List<Movie>> data = (HashMap<String, List<Movie>>) valueOperations.get(key);

            if (data == null){
                List<Movie> movieHistory = movieRepository.findMoviesByHistoryId(mailOrId, 20, 20 * page);

                data = new HashMap<>();
                data.put("movie history", movieHistory);
                valueOperations.set(key, data, 1, TimeUnit.HOURS);
            }
            //都给客户端
            return BaseResponse.success(data);
        }
        else {
            List<UserHistory> data = (List<UserHistory>) valueOperations.get(key);
            if (data == null) {
                List<Movie> newData = movieRepository.findMoviesByHistoryId(mailOrId, 20, 20 * page);
                valueOperations.set(key, newData, 60 - page, TimeUnit.MINUTES);

                return BaseResponse.success(newData);
            }
            return BaseResponse.success(data);
        }
    }

    @Override
    public BaseResponse addUserHistory(String mailOrId, Long tmdbId) {
        UserHistory userHistory = new UserHistory(mailOrId, tmdbId);
        userHistoryRepository.save(userHistory);
        return BaseResponse.success();
    }

    @Override
    public BaseResponse deleteUserHistory(String mailOrId, Long historyId) {
        UserHistory userHistory = new UserHistory(historyId, mailOrId);
        userHistoryRepository.delete(userHistory);
        return BaseResponse.success();
    }

    @Override
    public BaseResponse getUserCollection(String mailOrId, int page) {
        final String key = "user:collection_start" + page;
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();

        if (page == 0){
            HashMap<String, List<Movie>> data = (HashMap<String, List<Movie>>) valueOperations.get(key);

            if (data == null){
                List<Movie> movieCollection = movieRepository.findMoviesByCollectionId(mailOrId, 20, 20 * page);

                data = new HashMap<>();
                data.put("movie collection", movieCollection);
                valueOperations.set(key, data, 1, TimeUnit.HOURS);
            }
            //都给客户端
            return BaseResponse.success(data);
        }
        else {
            List<UserHistory> data = (List<UserHistory>) valueOperations.get(key);
            if (data == null) {
                List<Movie> newData = movieRepository.findMoviesByCollectionId(mailOrId, 20, 20 * page);
                valueOperations.set(key, newData, 60 - page, TimeUnit.MINUTES);

                return BaseResponse.success(newData);
            }
            return BaseResponse.success(data);
        }
    }

    @Override
    public BaseResponse addUserCollection(String mailOrId, Long tmdbId) {
        UserCollection userCollection = new UserCollection(mailOrId, tmdbId);
        userCollectionRepository.save(userCollection);
        return BaseResponse.success();
    }

    @Override
    public BaseResponse deleteUserCollection(String mailOrId, Long collectionId) {
        UserCollection userCollection = new UserCollection(collectionId, mailOrId);
        userCollectionRepository.delete(userCollection);
        return BaseResponse.success();
    }
}
