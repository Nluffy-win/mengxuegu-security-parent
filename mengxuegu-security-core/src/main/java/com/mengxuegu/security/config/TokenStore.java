package com.mengxuegu.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Y_Coffee on 2020/8/21
 */
@Configuration
public class TokenStore {

    @Bean
    public PasswordEncoder passwordEncoder(){
        // 明文+随机盐值》加密存储
        return new BCryptPasswordEncoder();
    }

}
