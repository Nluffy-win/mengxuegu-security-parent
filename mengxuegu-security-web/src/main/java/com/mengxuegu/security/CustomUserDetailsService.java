package com.mengxuegu.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 查询数据库中的用户信息
 * Created by Y_Coffee on 2020/8/21
 *
 * @author CoffeeY
 */
@Slf4j
@Component("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {


    private final PasswordEncoder passwordEncoder;

    public CustomUserDetailsService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("请求认证的的用户名：" + username);

        // 1. 通过请求的用户名去数据库中查询用户信息
        if (!"zhaojie".equalsIgnoreCase(username)) {
            throw new UsernameNotFoundException("用户名或者密码错误");
        }

        // 假设当前这个用户在数据库当中存储的密码是8888
        String password = passwordEncoder.encode("8888");

        // 2. 查询该用户有哪一些权限

        // 3. 封装用户信息和权限信息
        // username 用户名, password 是数据库中这个用户存储的密码,
        // authorities 是权限资源标识, springsecurity会自动的判断用户是否合法,
        String authorityString = "sys:user,sys:role,ROLE_ADMIN";
        return new User(username, password,
                AuthorityUtils.commaSeparatedStringToAuthorityList(authorityString));
    }
}
