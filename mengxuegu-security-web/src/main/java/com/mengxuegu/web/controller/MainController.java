package com.mengxuegu.web.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @Auther: 梦学谷 www.mengxuegu.com
 */
@Controller

public class MainController {

    @RequestMapping({"/index", "/", ""})
    public String index(Map<String, Object> map) {

        //第一种方式(登录之后的界面名字)
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (null != principal && principal instanceof UserDetails) {
            UserDetails details = (UserDetails) principal;
            String username = details.getUsername();
            //绑定，页面可根据获取key，修改对应名字
            map.put("username", username);
        }

        // resources/templates/index.html
        return "index";
    }

    /**
     * 第二种方式获取用户信息，只局限controller
     *
     * @param authentication
     * @return
     */
    @RequestMapping("/user/info")
    @ResponseBody
    public Object userInfo(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        return principal;
    }

    /**
     * 第三种方式获取用户信息，只局限controller
     *
     * @param userDetails
     * @return
     */
    @RequestMapping("/user/info2")
    @ResponseBody
    public Object userInfo2(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails;
    }
}
