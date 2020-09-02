package com.mengxuegu.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * 手机号查询
 * Created by Y_Coffee on 2020/8/27
 *
 * @author CoffeeY
 */
@Slf4j
@Component("mobileUserDetailsService")
public class MobileUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {
        log.info("验证的手机号：" + mobile);
        //通过手机查询用户信息
        //如有此用户，查询对应权限
        //封装用户信息
        return new User("meng", "", true, true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("ADMIN"));
    }
}
