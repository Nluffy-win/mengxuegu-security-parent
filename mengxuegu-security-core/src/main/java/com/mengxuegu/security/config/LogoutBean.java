package com.mengxuegu.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;

/**
 * 为了解决退出重新登录问题
 * Created by Y_Coffee on 2020/9/7
 *
 * @author CoffeeY
 */
@Configuration
public class LogoutBean {

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
}
