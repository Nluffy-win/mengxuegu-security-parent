package com.mengxuegu.security.authentication;

import com.mengxuegu.base.result.MengxueguResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Y_Coffee on 2020/8/21
 *
 * @author CoffeeY
 */
@Slf4j
@Component("customAuthenticationSuccessHandler")
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        MengxueguResult result = MengxueguResult.ok("认证成功");
        log.info("成功认证转换之前：" + result);
        log.info("authentication:" + authentication);
        response.setContentType("application/json;charset=UTF-8");
        log.info("成功认证转换之后：" + request);
        String json = result.toJsonString();
        response.getWriter().write(json);

    }
}
