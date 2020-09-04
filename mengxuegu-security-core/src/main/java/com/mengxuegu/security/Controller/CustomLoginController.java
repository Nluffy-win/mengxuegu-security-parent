package com.mengxuegu.security.Controller;


import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.mengxuegu.security.authentication.mobile.SmsSend;
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
public class  CustomLoginController {

    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_KEY";
    public static final String SESSION_MOBILE = "SESSION_KEY_MOBILE_KEY";
    private final DefaultKaptcha defaultKaptcha;
    private final SmsSend smsSend;

    public CustomLoginController(DefaultKaptcha defaultKaptcha, SmsSend smsSend) {
        this.defaultKaptcha = defaultKaptcha;
        this.smsSend = smsSend;
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

//    /**
//     * 登录手机短信登录界面
//     *
//     * @return
//     */
//    @RequestMapping("/mobile/page")
//    public String toMobilePage() {
//        return "login-mobile";
//    }
//
//    /**
//     * 发送短信验证码
//     *
//     * @return
//     */
//    @ResponseBody //响应json
//    @RequestMapping("/code/mobile")
//    public MengxueguResult code(HttpServletRequest request) {
//        //生成一个随机验证码
//        String code = RandomStringUtils.randomNumeric(4);
//        //放入session中方便过滤器校验
//        request.getSession().setAttribute(SESSION_MOBILE, code);
//        //获取手机号
//        String mobile = request.getParameter("mobile");
//        //发送信息到手机验证到手机号上
//        smsSend.sendSms(mobile,code);
//        return MengxueguResult.ok();
//    }
}
