package com.moviehub.server.authorization.interceptor;
///**
// * 自定义拦截器，判断此次请求是否有权限
// * @see com.moviehub.server.authorization.annotation.Authorization
// * @author wsh
// * @date 2023/3/14.
// */


import com.moviehub.server.authorization.annotation.Authorization;
import com.moviehub.server.util.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException, JSONException {

        //只拦截method
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        //只在有Authorization或RequestMapping注释拦截
        if (method.getAnnotation(Authorization.class) == null || method.getAnnotation(RequestMapping.class) == null) {
            return true;
        }

        String token = request.getHeader(Code.TOKEN);
        String[] attributions = DES.getDecryptString(token).split(",");

        if (attributions.length != 4) {
            response.setStatus(ResponeCode.UNAUTHORIZED.value);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", ResponeCode.UNAUTHORIZED.value);
            jsonObject.put("msg", ResponeCode.UNAUTHORIZED.msg);
            jsonObject.put("data", null);

            response.setCharacterEncoding("UTF-8");

            response.setContentType("application/json; charset=utf-8");

            PrintWriter out = response.getWriter();
            out.write(jsonObject.toString());
           // response.sendRedirect("user/loginWithPassword.html");
            out.flush();
            out.close();

            return false;
        }
        String email = attributions[0];
        String Password = attributions[1];
        String compareKey = attributions[2];
        String time = attributions[3];





        if (!compareKey.equals("MOVIEHUB")) {
            response.setStatus(ResponeCode.UNAUTHORIZED.value);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", ResponeCode.UNAUTHORIZED.value);
            jsonObject.put("msg", ResponeCode.UNAUTHORIZED.msg);
            jsonObject.put("data", null);

            response.setCharacterEncoding("UTF-8");

            response.setContentType("application/json; charset=utf-8");

            PrintWriter out = response.getWriter();
            out.write(jsonObject.toString());
          //  response.sendRedirect("user/loginWithPassword.html");
            out.flush();
            out.close();

            return false;
        }

        Jedis jedis = RedisUtil.getJedis();
        //小于十分钟，在paramap里面添加参数，让controller能收到
        if (TimeManager.getLapseHitherto(Timestamp.valueOf(time)) < 6000) {
            Map<String, String[]> parameterMap = request.getParameterMap();
            Method modifyMethod = parameterMap.getClass().getMethod("setLocked",
                    boolean.class);
            modifyMethod.invoke(parameterMap, false);
            parameterMap.put("emailFromParameterMap", new String[] {email});

            //更新redis数据库
            modifyMethod.invoke(parameterMap, true);
            jedis.expire(email, 18000L);
            return true;
        }
        else {
            //查找redis数据库  如果有就更新并通过，没有就导到登录点
            if (jedis.exists(email)) {
                jedis.expire(email, 18000L);
                Map<String, String[]> parameterMap = request.getParameterMap();
                Method modifyMethod = parameterMap.getClass().getMethod("setLocked",
                        boolean.class);
                modifyMethod.invoke(parameterMap, false);
                parameterMap.put("emailFromParameterMap", new String[] {email});

                //更新redis数据库
                modifyMethod.invoke(parameterMap, true);

                return true;
            }
            else {
                //没有了  这边要跳转到登录界面了
                response.setStatus(ResponeCode.UNAUTHORIZED.value);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("code", ResponeCode.UNAUTHORIZED.value);
                jsonObject.put("msg", ResponeCode.UNAUTHORIZED.msg);
                jsonObject.put("data", null);

                response.setCharacterEncoding("UTF-8");

                response.setContentType("application/json; charset=utf-8");

                PrintWriter out = response.getWriter();
                out.write(jsonObject.toString());
            //    response.sendRedirect("user/loginWithPassword.html");
                out.flush();
                out.close();

                return false;
            }

        }




    }
}

//public class AuthorizationInterceptor implements HandlerInterceptor {
//    private final static String KEY = "yunmuchang";
//
//    @Override
//    public boolean preHandle(HttpServletRequest request,
//                             HttpServletResponse response, Object handler) throws Exception {
//        //如果不是映射到方法直接通过
//        if (!(handler instanceof HandlerMethod)) {
//            return true;
//        }
//        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        Method method = handlerMethod.getMethod();
//        //从header中得到token
//        String token = request.getHeader(Code.TOKEN);
//        String time = request.getHeader(Code.REQUEST_TIME);
//        String sig = request.getHeader(Code.SIG);
//        String compareSig = "";
//        if(token!=null){
//            compareSig = MD5.GetMD5Code(token + KEY + time);
//        }else{
//            compareSig = MD5.GetMD5Code(KEY+time);
//        }
//        if((sig!=null)&&(compareSig.trim().equals(sig.trim()))){
//            if((Calendar.getInstance().getTimeInMillis()-Long.parseLong(time))/1000<=300){
//                return true;
//            }
//        }
//        if (method.getAnnotation(Authorization.class) != null) {
//            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//            return false;
//        }
//        return true;
////        //验证token
////        TokenModel model = manager.getToken(authorization);
////        if (manager.checkToken(model)) {
////            //如果token验证成功，将token对应的用户id存在request中，便于之后注入
////            request.setAttribute(Code.CURRENT_USER_ID, model.getUserId());
////            return true;
////        }
////        //如果验证token失败，并且方法注明了Authorization，返回401错误
////        if (method.getAnnotation(Authorization.class) != null) {
////            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
////            return false;
////        }
////        return true;
//    }
//}
