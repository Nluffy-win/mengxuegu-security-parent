package com.mengxuegu.security.Controller;


import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author CoffeeY
 * @Auther: 梦学谷 www.mengxuegu.com
 */
@Slf4j
@Controller
public class CustomLoginController {

    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_KEY";
    private final DefaultKaptcha defaultKaptcha;

    public CustomLoginController(DefaultKaptcha defaultKaptcha) {
        this.defaultKaptcha = defaultKaptcha;
    }

    @RequestMapping("/login/page")
    public String toLogin() {
        // classpath: /templates/login.html
        return "login";
    }

    @RequestMapping("/code/image")
    public void imageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取验证码的字符串
        String code = defaultKaptcha.createText();

        log.info("获取验证码的字符串:" + code);

        //把字符串写进session中
        request.getSession().setAttribute(SESSION_KEY, code);
        //获取图片验证码
        BufferedImage image = defaultKaptcha.createImage(code);
        //把图片验证码写出来
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
    }
}
