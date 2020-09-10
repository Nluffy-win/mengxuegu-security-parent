package com.mengxuegu.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Y_Coffee on 2020/9/9
 *
 * @author CoffeeY
 */
@Controller
@RequestMapping("/user")
public class SysUserController {

    private static final String HTML_PREFIX = "/system/user/";

    @GetMapping(value = {"/", ""})
    public String user() {
        String user = HTML_PREFIX + "user-list";
        return user;
    }

}
