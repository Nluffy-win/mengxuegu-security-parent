package com.mengxuegu.security.config;

import com.mengxuegu.security.authentication.code.ImageCodeValidateFilter;
import com.mengxuegu.security.authentication.mobile.MobileAuthenticationConfig;
import com.mengxuegu.security.authentication.mobile.MobileValidateFilter;
import com.mengxuegu.security.authorize.manager.AuthorizeConfigurerManager;
import com.mengxuegu.security.properties.AuthenticationProperties;
import com.mengxuegu.security.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;


/**
 * alt+/ 导包
 * ctrl+o 覆盖
 *
 * @author CoffeeY
 * @Auther: 梦学谷 www.mengxuegu.com
 */
@Slf4j
@Configuration
@EnableWebSecurity  // 开启springsecurity过滤链 filter
@EnableGlobalMethodSecurity(prePostEnabled = true)  //开启注解方法级权限控制
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final SecurityProperties securityProperties;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService customUserDetailsService;
    private final AuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final AuthenticationFailureHandler customAuthenticationFailHandler;
    private final ImageCodeValidateFilter imageCodeValidateFilter;
    private final JdbcTokenRepositoryImpl jdbcTokenRepository;
    private final MobileValidateFilter mobileValidateFilter;
    private final MobileAuthenticationConfig mobileAuthenticationConfig;
    private final InvalidSessionStrategy invalidSessionStrategy;
    private final SessionInformationExpiredStrategy sessionInformationExpiredStrategy;
    private final LogoutHandler CustomLogoutHandler;
    private final SessionRegistry sessionRegistry;
    private final LogoutHandler customLogoutHandler;
    private final AuthorizeConfigurerManager authorizeConfigurerManager;

    public SpringSecurityConfig(SecurityProperties securityProperties,
                                PasswordEncoder passwordEncoder,
                                UserDetailsService customUserDetailsService,
                                AuthenticationSuccessHandler customAuthenticationSuccessHandler,
                                AuthenticationFailureHandler customAuthenticationFailHandler,
                                ImageCodeValidateFilter imageCodeValidateFilter,
                                JdbcTokenRepositoryImpl jdbcTokenRepository,
                                MobileValidateFilter mobileValidateFilter,
                                MobileAuthenticationConfig mobileAuthenticationConfig,
                                InvalidSessionStrategy invalidSessionStrategy,
                                SessionInformationExpiredStrategy sessionInformationExpiredStrategy,
                                LogoutHandler customLogoutHandler, SessionRegistry sessionRegistry,
                                LogoutHandler customLogoutHandler1,
                                AuthorizeConfigurerManager authorizeConfigurerManager) {
        this.securityProperties = securityProperties;
        this.passwordEncoder = passwordEncoder;
        this.customUserDetailsService = customUserDetailsService;
        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
        this.customAuthenticationFailHandler = customAuthenticationFailHandler;
        this.imageCodeValidateFilter = imageCodeValidateFilter;
        this.jdbcTokenRepository = jdbcTokenRepository;
        this.mobileValidateFilter = mobileValidateFilter;
        this.mobileAuthenticationConfig = mobileAuthenticationConfig;
        this.invalidSessionStrategy = invalidSessionStrategy;
        this.sessionInformationExpiredStrategy = sessionInformationExpiredStrategy;
        this.CustomLogoutHandler = customLogoutHandler;
        this.sessionRegistry = sessionRegistry;
        this.customLogoutHandler = customLogoutHandler1;
        this.authorizeConfigurerManager = authorizeConfigurerManager;
    }


//    @Bean
//    public JdbcTokenRepositoryImpl jdbcTokenRepository() {
//        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
//        jdbcTokenRepository.setDataSource(dataSource);
//        //执行一次自动创表，security自带功能
//        jdbcTokenRepository.setCreateTableOnStartup(true);
//        return jdbcTokenRepository;
//    }


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

        //声明一个统一变量
        AuthenticationProperties authentication = securityProperties.getAuthentication();

        http
                .addFilterBefore(mobileValidateFilter, UsernamePasswordAuthenticationFilter.class)

                //登陆成功交给用户密码拦截器去管理，失败交给失败管理器管理
                .addFilterBefore(imageCodeValidateFilter, UsernamePasswordAuthenticationFilter.class)

                .formLogin() // 表单登录方式

                .loginPage(authentication.getLoginPage())

                // 登录表单提交处理url, 默认是/login
                .loginProcessingUrl(authentication.getLoginProcessingUrl())

                //默认的是 username
                .usernameParameter(authentication.getUsernameParameter())

                // 默认的是 password
                .passwordParameter(authentication.getPasswordParameter())

                //成功后的认证信息
                .successHandler(customAuthenticationSuccessHandler)
                //失败返回的认证信息
                .failureHandler(customAuthenticationFailHandler)

//                .and()
//
//                // 认证请求
//                .authorizeRequests()
//
//                // 放行/login/page不需要认证可访问
//                .antMatchers(authentication.getLoginPage(),
//                        authentication.getCodeImage(),
//                        authentication.getMobilePage(),
//                        authentication.getCodeMobile()).permitAll()
//
//                //匹配路径需要有什么类型权限访问
//                .antMatchers("/user").hasAuthority("sys:user")
//                //匹配路径，请求方式，有什么权限可以访问
//                .antMatchers(HttpMethod.GET, "/role").hasAuthority("sys:role")
//                //匹配路径，包含哪一种权限之一就可以访问，hasAnyRole系统默认加ROLE_,所以权限也需要加ROLE，hasAuthority不会加
//                .antMatchers("/permission")
//                .access("hasAuthority('sys:permission') or hasAnyRole('ADMIN')")
//
//                //所有访问该应用的http请求都要通过身份认证才可以访问
//                .anyRequest().authenticated()

                .and()

                //记住我功能
                .rememberMe()

                //保存登录信息
                .tokenRepository(jdbcTokenRepository)

                //设置有效时长
                .tokenValiditySeconds(authentication.getTokenValiditySeconds())

                .and()

                //session管理
                .sessionManagement()

                //设置失效后的返回
                .invalidSessionStrategy(invalidSessionStrategy)

                //设置session最大数
                .maximumSessions(1)

                //session超过最大数后的处理方式(踢掉上一个登录)
                .expiredSessionStrategy(sessionInformationExpiredStrategy)

                //当session超过最大数，开启功能(禁止另一台访问)，一般不开启
                //.maxSessionsPreventsLogin(true)

                //退出管理
                .sessionRegistry(sessionRegistry)

                .and()
                .and()
                .logout()

                //退出后清除缓存
                .addLogoutHandler(customLogoutHandler)

                //退出后跳转的路径
                .logoutSuccessUrl(authentication.getMobilePage())

                //退出路径
                .logoutUrl(authentication.getUserLogout())

                //退出后删除什么cookie
                .deleteCookies("JSESSIONID")

        ;
        ; // 注意不要少了分号

        //关闭跨站伪造，因为当前是get退出系统请求，security防止跨站，退出必须是post请求，关闭后解放限制
        http.csrf().disable();

        //将手机验证添加到过滤连上
        http.apply(mobileAuthenticationConfig);

        //权限相关配置的管理者，将所有权限管理
        authorizeConfigurerManager.configure(http.authorizeRequests());
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

//    @Bean
//    public SessionRegistry sessionRegistry(){
//        return new SessionRegistryImpl();
//    }
}
