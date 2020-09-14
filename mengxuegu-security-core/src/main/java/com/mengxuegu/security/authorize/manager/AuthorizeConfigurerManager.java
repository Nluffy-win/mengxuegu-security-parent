package com.mengxuegu.security.authorize.manager;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * 授权配置管理接口
 * Created by Y_Coffee on 2020/9/10
 *
 * @author CoffeeY
 */
public interface AuthorizeConfigurerManager {

    void configure(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config);
}
