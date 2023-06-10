package com.moviehub.server.authorization.interceptor;
/**
 * 自定义拦截器，判断此次请求是否有权限
 * @see com.moviehub.server.authorization.annotation.Authorization
 * @author wsh ruan
 * @date 2023/3/14.
 */


import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviehub.server.authorization.annotation.Authorization;
import com.moviehub.server.util.*;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import redis.clients.jedis.Jedis;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
    /**
     * 目标方法执行之前
     * 登录检查写在这里，如果没有登录，就不执行目标方法
     * @param request handler response
     * @return true false
     * @throws Exception
     */
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws IOException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        //只拦截method
//        if (!(handler instanceof HandlerMethod)) {
//            return true;
//        }
//        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        Method method = handlerMethod.getMethod();
//
//        //只在有Authorization或RequestMapping注释拦截
//        if (method.getAnnotation(Authorization.class) == null || method.getAnnotation(RequestMapping.class) == null) {
//            return true;
//        }
        String token = request.getHeader("token");
        if (token == null){
            request.setAttribute("isLoggedIn", false);
            return true;
        }
        System.out.println(token);
        String[] attributions = AESEncryptor.decipher(token).split(",");
        for (int i = 0; i < attributions.length; i++) {
            System.out.println(attributions[i]);
        }
        if (attributions.length != 2) {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonResponse;
            response.setContentType("application/json; charset=utf-8");

            jsonResponse = objectMapper.writeValueAsString(BaseResponse.error("Token Error! May be Attacked!"));

            response.getWriter().write(jsonResponse);

           // response.sendRedirect("user/loginWithPassword.html");

            return false;
        }
        String email = attributions[1];

        String time = attributions[0];

//        if (!"MOVIE HUB".equals(compareKey)) {
//            ObjectMapper objectMapper = new ObjectMapper();
//            String jsonResponse;
//            response.setContentType("application/json; charset=utf-8");
//
//            jsonResponse = objectMapper.writeValueAsString(BaseResponse.error("Token Error! May be Attacked!"));
//
//            response.getWriter().write(jsonResponse);
//
//
//            return false;
//        }

        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        //小于十分钟，在parameterMap里面添加参数，让controller能收到
        if (TimeManager.getLapseHitherto(Timestamp.valueOf(time)) < 6000) {
            request.setAttribute("isLoggedIn", true);
            request.setAttribute("email", email);
            //更新redis数据库
            valueOperations.set(email, token, 30, TimeUnit.MINUTES);
            return true;
        }
        else {
            //查找redis数据库  如果有就更新并通过，没有就导到登录点
            if (valueOperations.get(email) != null) {
                valueOperations.set(email, token, 30, TimeUnit.MINUTES);
                request.setAttribute("isLoggedIn", true);
                request.setAttribute("email", email);
                return true;
            }
            else {
                //没有了  这边要跳转到登录界面了
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonResponse;
                response.setContentType("application/json; charset=utf-8");

                jsonResponse = objectMapper.writeValueAsString(BaseResponse.error("Login Expire!"));

                response.getWriter().write(jsonResponse);


                return false;
            }
        }
    }
}

