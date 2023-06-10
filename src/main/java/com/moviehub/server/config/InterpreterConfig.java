package com.moviehub.server.config;

import com.moviehub.server.authorization.interceptor.AuthorizationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterpreterConfig implements WebMvcConfigurer {
    private final AuthorizationInterceptor authorizationInterceptor;
    public InterpreterConfig(AuthorizationInterceptor myInterceptor) {
        this.authorizationInterceptor = myInterceptor;
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //这种方式会拦截所有请求
        //registry.addInterceptor(new MyInterceptor());
        //这种方式会拦截指定的请求
        registry.addInterceptor(authorizationInterceptor).addPathPatterns("/choice");
        registry.addInterceptor(authorizationInterceptor).addPathPatterns("/singleMovie/rate");
    }
}
