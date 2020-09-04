//package com.mengxuegu.security.authentication.mobile;
//
//
//import com.mengxuegu.security.authentication.CustomAuthenticationFailHandler;
//import com.mengxuegu.security.authentication.CustomAuthenticationSuccessHandler;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.DefaultSecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.stereotype.Component;
//
///**
// * 用于组合其他关于手机登录的组件
// *
// * @author CoffeeY
// * @chain 过滤链
// * Created by Y_Coffee on 2020/8/27
// */
//@Component
//public class MobileAuthenticationConfig extends SecurityConfigurerAdapter
//        <DefaultSecurityFilterChain, HttpSecurity> {
//
//    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
//    private final CustomAuthenticationFailHandler customAuthenticationFailHandler;
//    private final MobileAuthenticationProvider mobileAuthenticationProvider;
//    private final UserDetailsService mobileUserDetailsService;
//
//    public MobileAuthenticationConfig(CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler,
//                                      CustomAuthenticationFailHandler customAuthenticationFailHandler,
//                                      MobileAuthenticationProvider mobileAuthenticationProvider, UserDetailsService mobileUserDetailsService) {
//        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
//        this.customAuthenticationFailHandler = customAuthenticationFailHandler;
//        this.mobileAuthenticationProvider = mobileAuthenticationProvider;
//        this.mobileUserDetailsService = mobileUserDetailsService;
//    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        MobileAuthenticationFilter mobileAuthenticationFilter = new MobileAuthenticationFilter();
//
//        //获取容器中已经存在的AuthenticationManager对象，并传入MobileAuthenticationFilter中
//        mobileAuthenticationFilter.setAuthenticationManager
//                (http.getSharedObject(AuthenticationManager.class));
//
//        //传入结果交给成功或者失败管理器管理
//        mobileAuthenticationFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
//        mobileAuthenticationFilter.setAuthenticationFailureHandler(customAuthenticationFailHandler);
//
//        MobileAuthenticationProvider provider = new MobileAuthenticationProvider();
//        provider.setUserDetailsService(mobileUserDetailsService);
//
//        //将provider绑定到HttpSecurity上,并将手机号码过滤器绑定到用户名密码之后
//        http.authenticationProvider(mobileAuthenticationProvider)
//                .addFilterAfter(mobileAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//    }
//}
