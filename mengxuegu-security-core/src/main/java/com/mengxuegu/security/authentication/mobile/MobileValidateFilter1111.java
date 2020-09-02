//package com.mengxuegu.security.authentication.mobile;
//
//import com.mengxuegu.security.Controller.CustomLoginController;
//import com.mengxuegu.security.authentication.CustomAuthenticationFailHandler;
//import com.mengxuegu.security.authentication.exception.ValidateCodeException;
//import com.mengxuegu.security.properties.SecurityProperties;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * 手机验证码过滤器
// * Created by Y_Coffee on 2020/8/25
// *
// * @author CoffeeY
// */
//@Slf4j
//@Component("mobileValidateFilter")
//public class MobileValidateFilter extends OncePerRequestFilter {
//
//    private final SecurityProperties securityProperties;
//    private final CustomAuthenticationFailHandler customAuthenticationFailHandler;
//
//    public MobileValidateFilter(SecurityProperties securityProperties,
//                                CustomAuthenticationFailHandler customAuthenticationFailHandler) {
//        this.securityProperties = securityProperties;
//        this.customAuthenticationFailHandler = customAuthenticationFailHandler;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
//                                    FilterChain filterChain) throws ServletException, IOException {
//        String mobileForm = securityProperties.getAuthentication().getMobileForm();
//
//        // 1. 判断 请求是否为手机登录，且post请求
//        if(mobileForm.equals(request.getRequestURI())
//                && "post".equalsIgnoreCase(request.getMethod())) {
//            try {
//                validate(request);
//            } catch (AuthenticationException e) {
//                customAuthenticationFailHandler.onAuthenticationFailure(request, response, e);
//                return;
//            }
//        }
//        //放行
//        filterChain.doFilter(request, response);
//
//    }
//
//    private void validate(HttpServletRequest request) {
//        //获取短信验证码
//        log.info("验证码转换object：" + request.getSession().getAttribute(CustomLoginController.SESSION_MOBILE));
//        String code = (String) request.getSession().getAttribute(CustomLoginController.SESSION_MOBILE);
//        log.info("验证码转换String：" + code);
//        //获取用户输入的验证码
//        String inputCode = request.getParameter("code");
//        if (StringUtils.isBlank(inputCode)) {
//            throw new ValidateCodeException("请输入手机验证码");
//        }
//        if (!code.equals(inputCode)) {
//            throw new ValidateCodeException("验证码输入有误！");
//        }
//    }
//}
