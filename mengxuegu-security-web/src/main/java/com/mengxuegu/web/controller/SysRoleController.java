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
@RequestMapping("/role")
public class SysRoleController {

    private static final String HTML_PREFIX = "/system/role/";

    @GetMapping(value = {"/",""})
    public String role() {
        String role = HTML_PREFIX + "role-list";
        return role;
    }

}
