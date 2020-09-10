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
@RequestMapping("/permission")
public class SysPermissionController {

    private static final String HTML_PREFIX = "/system/permission/";

    @GetMapping(value = {"/", ""})
    public String permission() {
        String permission = HTML_PREFIX + "permission-list";
        return permission;
    }

}
