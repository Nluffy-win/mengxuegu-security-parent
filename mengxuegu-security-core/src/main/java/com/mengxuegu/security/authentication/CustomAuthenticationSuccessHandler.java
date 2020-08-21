package com.mengxuegu.security.authentication;

import com.mengxuegu.base.result.MengxueguResult;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Y_Coffee on 2020/8/21
 * @author CoffeeY
 */
@Component("customAuthenticationSuccessHandler")
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
        HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        MengxueguResult result = MengxueguResult.ok("认证成功");
        response.setContentType("application/json;charset=UTF-8");
        String json = result.toJsonString();
        response.getWriter().write(json);
    }
}
