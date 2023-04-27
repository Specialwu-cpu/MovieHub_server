//package com.moviehub.server.authorization.interceptor;
///**
// * 自定义拦截器，判断此次请求是否有权限
// * @see com.moviehub.server.authorization.annotation.Authorization
// * @author wsh ruan
// * @date 2023/3/14.
// */
//
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.moviehub.server.authorization.annotation.Authorization;
//import com.moviehub.server.util.*;
//import jakarta.annotation.Resource;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.boot.configurationprocessor.json.JSONException;
//import org.springframework.boot.configurationprocessor.json.JSONObject;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.HandlerInterceptor;
//import redis.clients.jedis.Jedis;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.sql.Timestamp;
//import java.util.Map;
//
//@Component
//public class AuthorizationInterceptor implements HandlerInterceptor {
//    /**
//     * 目标方法执行之前
//     * 登录检查写在这里，如果没有登录，就不执行目标方法
//     * @param request handler response
//     * @return true false
//     * @throws Exception
//     */
//    @Resource
//    private RedisTemplate<String, Object> redisTemplate;
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException, JSONException {
//        //只拦截method
////        if (!(handler instanceof HandlerMethod)) {
////            return true;
////        }
////        HandlerMethod handlerMethod = (HandlerMethod) handler;
////        Method method = handlerMethod.getMethod();
////
////        //只在有Authorization或RequestMapping注释拦截
////        if (method.getAnnotation(Authorization.class) == null || method.getAnnotation(RequestMapping.class) == null) {
////            return true;
////        }
//        String token = request.getHeader("token");
//        if (token == null){
//            request.setAttribute("isLoggedIn", false);
//            return true;
//        }
//        String[] attributions = DES.getDecryptString(token).split(",");
//
//        if (attributions.length != 4) {
//            ObjectMapper objectMapper = new ObjectMapper();
//            String jsonResponse;
//            response.setContentType("application/json; charset=utf-8");
//
//            jsonResponse = objectMapper.writeValueAsString(BaseResponse.error("Token Error! May be Attacked!"));
//
//            response.getWriter().write(jsonResponse);
//
//           // response.sendRedirect("user/loginWithPassword.html");
//
//            return false;
//        }
//        String email = attributions[0];
//        String Password = attributions[1];
//        String compareKey = attributions[2];
//        String time = attributions[3];
//
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
//
//        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
//        //小于十分钟，在parameterMap里面添加参数，让controller能收到
//        if (TimeManager.getLapseHitherto(Timestamp.valueOf(time)) < 6000) {
//
//            //更新redis数据库
//            modifyMethod.invoke(parameterMap, true);
//            jedis.expire(email, 18000L);
//            return true;
//        }
//        else {
//            //查找redis数据库  如果有就更新并通过，没有就导到登录点
//            if (jedis.exists(email)) {
//                jedis.expire(email, 18000L);
//                Map<String, String[]> parameterMap = request.getParameterMap();
//                Method modifyMethod = parameterMap.getClass().getMethod("setLocked",
//                        boolean.class);
//                modifyMethod.invoke(parameterMap, false);
//                parameterMap.put("emailFromParameterMap", new String[] {email});
//                //更新redis数据库
//                modifyMethod.invoke(parameterMap, true);
//                return true;
//            }
//            else {
//                //没有了  这边要跳转到登录界面了
//                response.setStatus(ResponeCode.UNAUTHORIZED.value);
//                JSONObject jsonObject = new JSONObject();
//                jsonObject.put("code", ResponeCode.UNAUTHORIZED.value);
//                jsonObject.put("msg", ResponeCode.UNAUTHORIZED.msg);
//                jsonObject.put("data", null);
//                response.setCharacterEncoding("UTF-8");
//                response.setContentType("application/json; charset=utf-8");
//
//                PrintWriter out = response.getWriter();
//                out.write(jsonObject.toString());
//    //            response.sendRedirect("user/loginWithPassword.html");
//                out.flush();
//                out.close();
//                return false;
//            }
//        }
//    }
//}

