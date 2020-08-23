package com.mengxuegu.security.authentication.code;

import com.mengxuegu.security.Controller.CustomLoginController;
import com.mengxuegu.security.authentication.CustomAuthenticationFailHandler;
import com.mengxuegu.security.authentication.exception.ValidateCodeExcetipn;
import com.mengxuegu.security.properties.SecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 正在所有请求前执行一次过滤器
 *
 * @Classname ImageCodeValidateFilter * @Description TODO * @Date 2020/8/23 20:21 * @Created by John
 */
@Component("imageCodeValidateFilter")
public class ImageCodeValidateFilter extends OncePerRequestFilter {

    private SecurityProperties securityProperties;
    private CustomAuthenticationFailHandler customAuthenticationFailHandler;

    public ImageCodeValidateFilter(SecurityProperties securityProperties,
                                   CustomAuthenticationFailHandler customAuthenticationFailHandler) {
        this.securityProperties = securityProperties;
        this.customAuthenticationFailHandler = customAuthenticationFailHandler;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String loginProcessingUrl = securityProperties.getAuthentication().getLoginProcessingUrl();

        // 1. 如果是post方式 的登录请求，则校验输入的验证码是否正确
        if (loginProcessingUrl.equals(request.getRequestURI())
                && request.getMethod().equals("post")) {
            try {
                // 校验验证码合法性
                validate(request);
            } catch (AuthenticationException e) {
                // 交给失败处理器进行处理异常
                customAuthenticationFailHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        //请求放行
        filterChain.doFilter(request,response);
    }

    private void validate(HttpServletRequest request) {
        // 先获取seesion中的验证码
        String sessionCode = (String) request.getSession().getAttribute(CustomLoginController.SESSION_KEY);
        // 获取用户输入的验证码
        String inputCode = request.getParameter("code");

        // 判断是否正确
        if (StringUtils.isBlank(inputCode)) {
            throw new ValidateCodeExcetipn("验证码不能为空");
        }
        if (!inputCode.equalsIgnoreCase(sessionCode)) {
            throw new ValidateCodeExcetipn("验证码错误");
        }
    }
}
