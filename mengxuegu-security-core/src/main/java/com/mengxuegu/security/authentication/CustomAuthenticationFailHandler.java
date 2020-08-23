package com.mengxuegu.security.authentication;

import com.mengxuegu.base.result.MengxueguResult;
import com.mengxuegu.security.properties.LoginResponseType;
import com.mengxuegu.security.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Security;

/**
 * @Classname CustomAuthenticationFailHandler * @Description TODO * @Date 2020/8/21 21:41 * @Created by John
 */
@Slf4j
@Component("customAuthenticationFailHandler")
//public class CustomAuthenticationFailHandler implements AuthenticationFailureHandler
public class CustomAuthenticationFailHandler extends SimpleUrlAuthenticationFailureHandler {

    private final SecurityProperties securityProperties;

    public CustomAuthenticationFailHandler(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException e) throws IOException, ServletException {
        if (LoginResponseType.JSON.equals(securityProperties.getAuthentication().getLoginType())) {
            MengxueguResult result =
                    MengxueguResult.build(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
            log.info("错误请求转换之后：" + result);
            log.info("错误信息：" + e);
            String json = result.toJsonString();
            response.setContentType("application/json;charset=UTF-8");
            log.info("错误请求转换之后：" + result);
            response.getWriter().write(json);
        } else {
            //重定向地址
            super.setDefaultFailureUrl(securityProperties.getAuthentication().getLoginPage() + "?error");
            super.onAuthenticationFailure(request, response, e);
        }

    }
}
