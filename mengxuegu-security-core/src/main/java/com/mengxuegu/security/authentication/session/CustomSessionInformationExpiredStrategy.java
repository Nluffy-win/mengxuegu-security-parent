package com.mengxuegu.security.authentication.session;


import com.mengxuegu.security.authentication.CustomAuthenticationFailHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * 当
 * Created by Y_Coffee on 2020/9/4
 *
 * @author CoffeeY
 */
public class CustomSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {

    @Autowired
    private CustomAuthenticationFailHandler customAuthenticationFailHandler;

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException {
        //获取用户信息
        UserDetails userDetails = (UserDetails) event.getSessionInformation().getPrincipal();
        AuthenticationException exception =
                new AuthenticationServiceException(String.format("[%s]用户在另外一台电脑登录，您已被迫下线", userDetails.getUsername()));

        try {
            //设置值，并赋到event.getRequest()
            event.getRequest().setAttribute("toAuthentication", true);
            customAuthenticationFailHandler.onAuthenticationFailure(event.getRequest(), event.getResponse(), exception);
        } catch (ServletException e) {
            e.printStackTrace();
        }


    }
}
