package com.mengxuegu.security.authorize;

import com.mengxuegu.security.properties.AuthenticationProperties;
import com.mengxuegu.security.properties.SecurityProperties;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * 身份认证请求相关配置
 * Created by Y_Coffee on 2020/9/10
 *
 * @author CoffeeY
 */
@Component
@Order(Integer.MAX_VALUE)  //值越大，越最后执行这个类。防止anyRequest放行通过验证出错
public class CustomAuthorizeConfigurationProvider implements AuthorizeConfigurerProvider {

    private final SecurityProperties securityProperties;

    public CustomAuthorizeConfigurationProvider(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Override
    public void configure(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {

        //声明一个统一变量
        AuthenticationProperties authentication = securityProperties.getAuthentication();

        // 认证请求
        config
                // 放行/login/page不需要认证可访问
                .antMatchers(authentication.getLoginPage(),
                        authentication.getCodeImage(),
                        authentication.getMobilePage(),
                        authentication.getCodeMobile())
                .permitAll()
        ;

        //所有访问该应用的http请求都要通过身份认证才可以访问
        //config.anyRequest().authenticated();
    }
}
