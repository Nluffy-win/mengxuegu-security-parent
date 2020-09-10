package com.mengxuegu.security.authorize;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * 授权配置统一接口，授权配置类都使用此模块
 * Created by Y_Coffee on 2020/9/10
 *
 * @author CoffeeY
 * 接口所有方法自带public
 */

public interface AuthorizeConfigurerProvider {

    /**
     * @param config
     *参数为.authorizeRequests()的返回值
     */
    void configure(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config);

}
