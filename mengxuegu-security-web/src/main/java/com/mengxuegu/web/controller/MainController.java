package com.mengxuegu.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: 梦学谷 www.mengxuegu.com
 */
@Controller

public class MainController {

    @RequestMapping({"/index", "/", ""})
    public String index() {
        // resources/templates/index.html
        return "index";
    }

}
