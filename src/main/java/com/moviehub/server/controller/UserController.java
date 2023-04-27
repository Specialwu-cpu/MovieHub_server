package com.moviehub.server.controller;

import com.moviehub.server.authorization.annotation.Authorization;
import com.moviehub.server.service.IUserService;
import com.moviehub.server.service.IVerifyCodeService;
import com.moviehub.server.util.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "UserController", description = "UserController")
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService iUserService;

    @Resource
    private IVerifyCodeService iVerifyCodeService;

    @GetMapping("/{mailOrId}")
    @Operation(summary = "获取用户信息", description = "根据邮箱或者用户ID获取用户信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "获取成功"),
            @ApiResponse(responseCode = "400", description = "请求参数错误"),
            @ApiResponse(responseCode = "404", description = "用户不存在"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public BaseResponse getUserInfo(@PathVariable String mailOrId) {
        return iUserService.getUserInfo(mailOrId);
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
}
