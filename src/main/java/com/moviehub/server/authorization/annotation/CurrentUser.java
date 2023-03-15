package com.moviehub.server.authorization.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在Controller的方法上使用此注解，该方法在映射时会检查用户是否登录，未登录返回401错误
 * @see com.moviehub.server.authorization.resolvers.CurrentUserMethodArgumentResolver
 * @author wsh
 * @date 2023/3/14.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrentUser {
}
