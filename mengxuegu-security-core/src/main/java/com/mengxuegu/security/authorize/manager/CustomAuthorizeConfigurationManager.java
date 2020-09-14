package com.mengxuegu.security.authorize.manager;

import com.mengxuegu.security.authorize.AuthorizeConfigurerProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 对授权权限统一的管理者
 * Created by Y_Coffee on 2020/9/10
 *
 * @author CoffeeY
 */
@Component
public class CustomAuthorizeConfigurationManager implements AuthorizeConfigurerManager {

    @Autowired
    List<AuthorizeConfigurerProvider> authorizeConfigurerProviders;

    @Override
    public void configure(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        for (AuthorizeConfigurerProvider provider : authorizeConfigurerProviders) {
            provider.configure(config);
        }
        config.anyRequest().authenticated();
    }
}
