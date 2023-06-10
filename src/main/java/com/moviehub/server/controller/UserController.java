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

    @PostMapping("/info")
    @Operation(summary = "获取用户信息", description = "根据邮箱或者用户ID获取用户信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "获取成功"),
            @ApiResponse(responseCode = "400", description = "请求参数错误"),
            @ApiResponse(responseCode = "404", description = "用户不存在"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
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

    @PutMapping("/change/info")
    public BaseResponse updateUser(
            HttpServletRequest request,
            @RequestParam("file") MultipartFile file,
            @RequestParam("user_name") String user_name,
            @RequestParam("style_text") String style_text) {
        Boolean isLoggedIn = (Boolean) request.getAttribute("isLoggedIn");
        String email = (String) request.getAttribute("email");
        if (isLoggedIn){
            return iUserService.updateUser(email, user_name, style_text, file);
        }
        else {
            System.out.println("我喜欢我");
            return BaseResponse.error("请登录");
        }
//        return iUserService.updateUser(map.get("mail_or_id"), map.get("user_name"), map.get("style_text"));
    }

    @PutMapping("/change/avatar")
    public BaseResponse updateAvatar(
            HttpServletRequest request,
            @RequestParam("file") MultipartFile file,
            @RequestParam("mail_or_id") String mail_or_id) {
        return iUserService.updateAvatar(mail_or_id, file);
    }

    /**
     * @api {Post} /user/loginWithPassword 登录
     * @apiDescription 登录
     * @apiGroup UserController
     * @apiParam {String} mail_or_id 邮箱或者用户名
     * @apiParam {String} password 密码
     * @apiSuccessExample {json} Success-Response:
     * {
     * "code": 200,
     * "message": "success",
     * "data": {
     * <p>
     * }
     * }
     * @apiErrorExample {json} Error-Response:
     * {
     * "code": 400,
     * "message": "该账号未注册",
     * "data": null
     * }
     * {
     * "code": 400,
     * "message": "该账号密码错误",
     * "data": null
     * }
     * {
     * "code": 400,
     * "message": "验证码错误",
     * "data": null
     * }
     * {
     * "code": 400,
     * "message": "验证码已过期",
     * "data": null
     * }
     * @apiVersion 1.0.0
     */
    @Operation(summary = "login", description = "login with mail_or_id and password",
            parameters = {@Parameter(name = "mail_or_id", description = "mail or id"),
                    @Parameter(name = "password", description = "password")})
    @ApiResponse(responseCode = "200", description = "login success")
    @ApiResponse(responseCode = "400", description = "mail_or_id not exist or password error")
    @PostMapping(value = "loginWithPassword")
    public BaseResponse login(@RequestBody Map<String, String> map) {
        return iUserService.login(map.get("mail_or_id"), map.get("password"));
    }

    /**
     * @api {POST} /user/register 注册
     * @apiDescription 注册
     * @apiGroup UserController
     * @apiHeader {String} key=desc
     * @apiParam {type} name desc
     * @apiParamExample {json} 请求示例:
     * {
     * <p>
     * }
     * @apiSuccessExample {json} 成功响应:
     * HTTP/1.1 200 OK
     * {
     * "code":"200",
     * "message":"success",
     * "data":{
     * }
     * }
     * @apiVersion 1.0.0
     */
    @Operation(summary = "register", description = "use mail_or_id register",
            parameters = {@Parameter(name = "mail_or_id", description = "mail or id"),
                    @Parameter(name = "password", description = "password"),
                    @Parameter(name = "user_name", description = "user name"),
                    @Parameter(name = "verify_code", description = "verify code")})
    @ApiResponse(responseCode = "200", description = "register success")
    @ApiResponse(responseCode = "400", description = "mail_or_id already exist or verify code error or verify code expired")
    @PostMapping(value = "/register")
    public BaseResponse register(@RequestBody Map<String, String> map) {
        return iUserService.register(map.get("mail_or_id"), map.get("password"), map.get("user_name"), map.get("verify_code"));
    }

    @PostMapping("/reset-password")
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

    @PostMapping("/forget-password")
    @Operation(summary = "忘记密码", description = "重置用户密码")
    public BaseResponse forgetPassword(@RequestBody Map<String, String> map) {
        return iUserService.forgetPassword(map.get("mail_or_id"), map.get("newPasswordOne"), map.get("newPasswordTwo"), map.get("verify_code"));
    }

    //    @Authorization
    @Operation(summary = "sendVerifyCode", description = "send verify code to email",
            parameters = {@Parameter(name = "email", description = "email")})
    @ApiResponse(responseCode = "200", description = "send verify code success")
    @ApiResponse(responseCode = "400", description = "email not exist")
    @ApiResponse(responseCode = "500", description = "send verify code failed")
    @PostMapping(value = "/sendVerifyCode")
    public BaseResponse sendVerifyCode(@RequestBody Map<String, String> map) {
        try {
            return iVerifyCodeService.sendVerifyCode(map.get("email"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{mailOrId}/history")
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

    @PostMapping("/{mailOrId}/history/{tmdbId}")
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

    @DeleteMapping("/{mailOrId}/history/{historyId}")
    @Operation(summary = "删除历史记录", description = "删除用户的历史记录")
    public BaseResponse deleteUserHistory(
            HttpServletRequest request,
            @PathVariable("historyId") Long historyId) {
        Boolean isLoggedIn = (Boolean) request.getAttribute("isLoggedIn");
        String email = (String) request.getAttribute("email");
        if (isLoggedIn){
            return iUserService.deleteUserHistory(email, historyId);
        }
        else {
            System.out.println("我喜欢我");
            return BaseResponse.error("去登录");
        }
    }

    @GetMapping("/{mailOrId}/collection")
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

    @PostMapping("/{mailOrId}/collection/{tmdbId}")
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

    @DeleteMapping("/{mailOrId}/collection/{collectionId}")
    @Operation(summary = "删除收藏记录", description = "删除用户的收藏记录")
    public BaseResponse deleteUserCollection(
            HttpServletRequest request,
            @PathVariable("collectionId") Long collectionId) {
        Boolean isLoggedIn = (Boolean) request.getAttribute("isLoggedIn");
        String email = (String) request.getAttribute("email");
        if (isLoggedIn){
            return iUserService.deleteUserCollection(email, collectionId);
        }
        else {
            System.out.println("我喜欢我");
            return BaseResponse.error("去登录");
        }
    }
}
