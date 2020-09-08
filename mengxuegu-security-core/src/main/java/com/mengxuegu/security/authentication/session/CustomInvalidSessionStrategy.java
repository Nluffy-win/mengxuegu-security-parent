package com.mengxuegu.security.authentication.session;


import com.mengxuegu.base.result.MengxueguResult;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义session
 * Created by Y_Coffee on 2020/9/3
 *
 * @author CoffeeY
 */
public class CustomInvalidSessionStrategy implements InvalidSessionStrategy {

    private final SessionRegistry sessionRegistry;

    public CustomInvalidSessionStrategy(SessionRegistry sessionRegistry) {
        this.sessionRegistry = sessionRegistry;
    }

    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException {
        MengxueguResult result = MengxueguResult.build(
                HttpStatus.UNAUTHORIZED.value(), "登录已过期，请重新登录");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(result.toJsonString());

        sessionRegistry.removeSessionInformation(request.getRequestedSessionId());

        //删除浏览器的cookie
        cancelCookie(request, response);
    }

    protected void cancelCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setMaxAge(0);
        cookie.setPath(getCookiePath(request));
        response.addCookie(cookie);
    }

    /**
     * 获取cookie的路径
     *
     * @param request
     * @return
     */
    private String getCookiePath(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        return contextPath.length() > 0 ? contextPath : "/";
    }
}
