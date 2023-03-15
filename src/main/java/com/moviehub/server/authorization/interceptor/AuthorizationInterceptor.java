//package com.moviehub.server.authorization.interceptor;
//
//import com.moviehub.server.authorization.annotation.Authorization;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import java.lang.reflect.Method;
//import java.util.Calendar;
//
///**
// * 自定义拦截器，判断此次请求是否有权限
// * @see com.moviehub.server.authorization.annotation.Authorization
// * @author wsh
// * @date 2023/3/14.
// */
//@Component
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
