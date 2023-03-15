package com.moviehub.server.authorization.resolvers;

import com.moviehub.server.authorization.annotation.CurrentUser;
import com.moviehub.server.util.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.util.Iterator;

/**
 * 增加方法注入，将含有CurrentUser注解的方法参数注入当前登录用户
 * @see com.moviehub.server.authorization.annotation.CurrentUser
 * @author wsh
 * @date 2023/3/14.
 */
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    private IUserService userService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //如果参数类型是User并且有CurrentUser注解则支持
        if (parameter.hasParameterAnnotation(CurrentUser.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        //取出鉴权时存入的登录用户token
        String token = webRequest.getHeader(Code.TOKEN);
//        String type = webRequest.getHeader(Code.TOKEN_TYPE);
        Iterator<String> s = webRequest.getHeaderNames();
        if(token != null){
            //从数据库中查询并返回
//            return userService.getUserByToken(token);
            return null;
        }else{
            throw new MissingServletRequestPartException(Code.TOKEN);
        }
    }
}
