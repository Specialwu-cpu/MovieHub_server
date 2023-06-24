package com.moviehub.server.controller;

import com.moviehub.server.service.IUserService;
import com.moviehub.server.service.IVerifyCodeService;
import com.moviehub.server.util.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@Tag(name = "UserController", description = "用户操作接口")
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {
    @Resource
    private IUserService iUserService;

    @Resource
    private IVerifyCodeService iVerifyCodeService;

    @Operation(summary = "获取用户信息", description = "根据邮箱或者用户ID获取用户信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "获取成功"),
            @ApiResponse(responseCode = "400", description = "请求参数错误"),
            @ApiResponse(responseCode = "404", description = "用户不存在"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    @GetMapping("/info")
//    public BaseResponse getUserInfo(@RequestBody Map<String, String> map) {
    public BaseResponse getUserInfo(HttpServletRequest request) {
        Boolean isLoggedIn = (Boolean) request.getAttribute("isLoggedIn");
        String email = (String) request.getAttribute("email");
        if (isLoggedIn){
            return iUserService.getUserInfo(email);
        }
        else {
            System.out.println("我喜欢我");
            return BaseResponse.error("请登录");
        }
//        return iUserService.getUserInfo(map.get("mail_or_id"));
    }

    @PutMapping("/info")
    public BaseResponse updateUser(
            HttpServletRequest request,
            @RequestParam("file") byte[] file,
            @RequestParam("userName") String userName,
            @RequestParam("styleText") String styleText) {
        Boolean isLoggedIn = (Boolean) request.getAttribute("isLoggedIn");
        String email = (String) request.getAttribute("email");
        if (isLoggedIn){
            return iUserService.updateUser(email, userName, styleText, file);
        }
        else {
            System.out.println("我喜欢我");
            return BaseResponse.error("请登录");
        }
//        return iUserService.updateUser(map.get("mail_or_id"), map.get("user_name"), map.get("style_text"));
    }

    @PutMapping("/avatar")
    public BaseResponse updateAvatar(
            HttpServletRequest request,
            @RequestParam("file") MultipartFile file) {
        Boolean isLoggedIn = (Boolean) request.getAttribute("isLoggedIn");
        String email = (String) request.getAttribute("email");
        if (isLoggedIn){
            return iUserService.updateAvatar(email, file);
        }
        else {
            System.out.println("我喜欢我");
            return BaseResponse.error("请登录");
        }
//        return iUserService.updateAvatar(mail_or_id, file);
    }

    @Operation(summary = "login", description = "login with mail_or_id and password, you should send me request twice time. First time" +
            " with 'mail_or_id'. and i will return you a 'nonce', you should concat nonce with password and use SHA256 hash function" +
            "to generate a cipherText. Second time, you should send me two parameters, one of which is 'mail_or_id', and another is " +
            "'cipherText', i will return you token and it should be used to identify your identity",
            parameters = {@Parameter(name = "mail_or_id", description = "mail or id"),
                    @Parameter(name = "cipherText", description = "SHA256(nonce + password)")})
    @ApiResponse(responseCode = "200", description = "login success")
    @ApiResponse(responseCode = "400", description = "mail_or_id not exist or password error")
    @PostMapping(value = "/login")
    public BaseResponse login(@RequestBody Map<String, String> map) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        return iUserService.login(map.get("mailOrId"), map.get("cipherText"));
    }

    @Operation(summary = "register", description = "use mail_or_id register",
            parameters = {@Parameter(name = "mailOrId", description = "mail or id"),
                    @Parameter(name = "password", description = "password"),
                    @Parameter(name = "userName", description = "user name"),
                    @Parameter(name = "verifyCode", description = "verify code")})
    @ApiResponse(responseCode = "200", description = "register success")
    @PostMapping(value = "/register")
    public BaseResponse register(@RequestBody Map<String, String> map) {
        return iUserService.register(map.get("mailOrId"), map.get("password"), map.get("userName"), map.get("verifyCode"));
    }

    @PutMapping("/resetPassword")
    @Operation(summary = "重置密码", description = "重置用户密码")
    public BaseResponse resetPassword(HttpServletRequest request, @RequestBody Map<String, String> map) {
        Boolean isLoggedIn = (Boolean) request.getAttribute("isLoggedIn");
        String email = (String) request.getAttribute("email");
        if (isLoggedIn){
            return iUserService.resetPassword(email, map.get("oldPasswordOne"), map.get("oldPasswordTwo"), map.get("newPassword"));
        }
        else {
            System.out.println("我喜欢我");
            return BaseResponse.error("快去登录");
        }
    }

    @PutMapping("/forgetPassword")
    @Operation(summary = "忘记密码", description = "重置用户密码")
    public BaseResponse forgetPassword(@RequestBody Map<String, String> map) {
        return iUserService.forgetPassword(map.get("mailOrId"), map.get("newPasswordOne"), map.get("newPasswordTwo"), map.get("verifyCode"));
    }

    //    @Authorization
    @Operation(summary = "sendVerifyCode", description = "send verify code to email",
            parameters = {@Parameter(name = "email", description = "email")})
    @ApiResponse(responseCode = "200", description = "send verify code success")
    @ApiResponse(responseCode = "400", description = "email not exist")
    @ApiResponse(responseCode = "500", description = "send verify code failed")
    @GetMapping(value = "/sendVerifyCode")
    public BaseResponse sendVerifyCode(@RequestBody Map<String, String> map) {
        try {
            return iVerifyCodeService.sendVerifyCode(map.get("email"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/history")
    @Operation(summary = "查看用户历史记录", description = "根据用户ID查看用户的历史记录")
    public BaseResponse getUserHistory(
            HttpServletRequest request,
            @RequestParam(value = "page") int page) {
        Boolean isLoggedIn = (Boolean) request.getAttribute("isLoggedIn");
        String email = (String) request.getAttribute("email");
        if (isLoggedIn){
            return iUserService.getUserHistory(email, page);
        }
        else {
            System.out.println("我喜欢我");
            return BaseResponse.error("nimasile");
        }
    }

    @PostMapping("/history/{tmdbId}")
    @Operation(summary = "添加历史记录", description = "为用户添加历史记录")
    public BaseResponse addUserHistory(
            HttpServletRequest request,
            @PathVariable("tmdbId") Long tmdbId) {
        Boolean isLoggedIn = (Boolean) request.getAttribute("isLoggedIn");
        String email = (String) request.getAttribute("email");
        if (isLoggedIn){
            return iUserService.addUserHistory(email, tmdbId);
        }
        else {
            System.out.println("我喜欢我");
            return BaseResponse.error("去登录");
        }
    }

    @DeleteMapping("/history/{tmdbId}")
    @Operation(summary = "删除历史记录", description = "删除用户的历史记录")
    public BaseResponse deleteUserHistory(
            HttpServletRequest request,
            @PathVariable("tmdbId") Long tmdbId) {
        Boolean isLoggedIn = (Boolean) request.getAttribute("isLoggedIn");
        String email = (String) request.getAttribute("email");
        if (isLoggedIn){
            return iUserService.deleteUserHistory(email, tmdbId);
        }
        else {
            System.out.println("我喜欢我");
            return BaseResponse.error("去登录");
        }
    }

    @GetMapping("/collection")
    @Operation(summary = "查看用户收藏记录", description = "根据用户ID查看用户的收藏记录")
    public BaseResponse getUserCollection(
            HttpServletRequest request,
            @RequestParam(value = "page") int page) {
        Boolean isLoggedIn = (Boolean) request.getAttribute("isLoggedIn");
        String email = (String) request.getAttribute("email");
        if (isLoggedIn){
            return iUserService.getUserCollection(email, page);
        }
        else {
            System.out.println("我喜欢我");
            return BaseResponse.error("去登录");
        }
    }

    @PostMapping("/collection/{tmdbId}")
    @Operation(summary = "添加收藏记录", description = "为用户添加收藏记录")
    public BaseResponse addUserCollection(
            HttpServletRequest request,
            @PathVariable("tmdbId") Long tmdbId) {
        Boolean isLoggedIn = (Boolean) request.getAttribute("isLoggedIn");
        String email = (String) request.getAttribute("email");
        if (isLoggedIn){
            return iUserService.addUserCollection(email, tmdbId);
        }
        else {
            System.out.println("我喜欢我");
            return BaseResponse.error("去登录");
        }
    }

    @DeleteMapping("/collection/{tmdbId}")
    @Operation(summary = "删除收藏记录", description = "删除用户的收藏记录")
    public BaseResponse deleteUserCollection(
            HttpServletRequest request,
            @PathVariable("tmdbId") Long tmdbId) {
        Boolean isLoggedIn = (Boolean) request.getAttribute("isLoggedIn");
        String email = (String) request.getAttribute("email");
        if (isLoggedIn){
            return iUserService.deleteUserCollection(email, tmdbId);
        }
        else {
            System.out.println("我喜欢我");
            return BaseResponse.error("去登录");
        }
    }
}
