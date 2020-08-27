package com.mengxuegu.security.authentication.mobile;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 手机认证处理的提供者
 * Created by Y_Coffee on 2020/8/26
 *
 * @author CoffeeY
 */
@Slf4j
public class MobileAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * 认证处理
     * 1.通过手机号码查询用户信息(userDetailsService去实现)
     * 2.查询到用户信息，则认为通过，封装authentication信息
     *
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        MobileAuthenticationToken mobileAuthenticationToken =
                (MobileAuthenticationToken) authentication;
        //获取手机号
        String mobile = (String) mobileAuthenticationToken.getPrincipal();

        //通过手机号码查询用户信息(userDetailsService去实现)
        //user里面装用户所有信息
        UserDetails user = userDetailsService.loadUserByUsername(mobile);
        if (null == user) {
            //因为AuthenticationException是抽象类方法，不能new，使用子类
            throw new AuthenticationServiceException("手机号码尚未注册");
        }

        //查询到了，封装进MobileAuthenticationToken
        MobileAuthenticationToken authenticationToken =
                new MobileAuthenticationToken(user, user.getAuthorities());
        //存入session，token
        authenticationToken.setDetails(mobileAuthenticationToken.getDetails());
        log.info("完整的用户信息" + authenticationToken);
        log.info("setDetails信息：" + mobileAuthenticationToken.getDetails());
        return authenticationToken;
    }

    /**
     * 第一步
     * 通过这个方法调用对应的provider提供者
     * 返回true调用MobileAuthenticationProvider，false返回别的provider
     *
     * @param authentication 权限
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return MobileAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
