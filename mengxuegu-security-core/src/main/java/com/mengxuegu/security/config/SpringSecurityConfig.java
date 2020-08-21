package com.mengxuegu.security.config;

import com.mengxuegu.security.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


/**
 * alt+/ 导包
 * ctrl+o 覆盖
 *
 * @Auther: 梦学谷 www.mengxuegu.com
 */
@Slf4j
@Configuration
@EnableWebSecurity  // 开启springsecurity过滤链 filter
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final SecurityProperties securityProperties;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService customUserDetailsService;
    private final AuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final AuthenticationFailureHandler customAuthenticationFailHandler;

    public SpringSecurityConfig(SecurityProperties securityProperties,
                                PasswordEncoder passwordEncoder,
                                UserDetailsService customUserDetailsService,
                                AuthenticationSuccessHandler customAuthenticationSuccessHandler,
                                AuthenticationFailureHandler customAuthenticationFailHandler) {
        this.securityProperties = securityProperties;
        this.passwordEncoder = passwordEncoder;
        this.customUserDetailsService = customUserDetailsService;
        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
        this.customAuthenticationFailHandler = customAuthenticationFailHandler;
    }

    /**
     * 认证管理器：
     * 1. 认证信息（用户名，密码）
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 数据库存储的密码必须是加密后的，不然会报错：There is no PasswordEncoder mapped for the id "null"
//        String password = passwordEncoder.encode("8888");
//        log.info("加密之后存储的密码：" + password);
//        auth.inMemoryAuthentication().withUser("zhaojie")
//                .password(password).authorities("ADMIN");
        auth.userDetailsService(customUserDetailsService);

    }

    /**
     * 当你认证成功之后 ，springsecurity它会重写向到你上一次请求上
     * 资源权限配置：
     * 1. 被拦截的资源
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic() // 采用 httpBasic认证方式
        http.formLogin() // 表单登录方式
                .loginPage(securityProperties.getAuthentication().getLoginPage())

                // 登录表单提交处理url, 默认是/login
                .loginProcessingUrl(securityProperties.getAuthentication().getLoginProcessingUrl())

                //默认的是 username
                .usernameParameter(securityProperties.getAuthentication().getUsernameParameter())

                // 默认的是 password
                .passwordParameter(securityProperties.getAuthentication().getPasswordParameter())

                //返回成功后的认证信息
                .successHandler(customAuthenticationSuccessHandler)
                //失败返回的认证信息
                .failureHandler(customAuthenticationFailHandler)

                .and()

                // 认证请求
                .authorizeRequests()

                // 放行/login/page不需要认证可访问
                .antMatchers(securityProperties.getAuthentication().getLoginPage()).permitAll()

                //所有访问该应用的http请求都要通过身份认证才可以访问
                .anyRequest().authenticated()
        ; // 注意不要少了分号
    }

    /**
     * 一般是针对静态资源放行
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(securityProperties.getAuthentication().getStaticPaths());
    }
}
