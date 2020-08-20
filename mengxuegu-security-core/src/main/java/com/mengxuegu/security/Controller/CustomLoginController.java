package com.mengxuegu.security.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author CoffeeY
 * @Auther: 梦学谷 www.mengxuegu.com
 */
@Controller
public class CustomLoginController {

    @RequestMapping("/login/page")
    public String toLogin() {
        // classpath: /templates/login.html
        return "login";
    }

}
