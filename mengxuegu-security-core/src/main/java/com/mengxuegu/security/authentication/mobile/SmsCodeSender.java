package com.mengxuegu.security.authentication.mobile;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Y_Coffee on 2020/8/24
 *
 * @author CoffeeY
 */
@Slf4j
public class SmsCodeSender implements SmsSend {

    @Override
    public boolean sendSms(String mobile, String content) {
        String sendContent = String.format("梦学谷,验证码：%s", content);
        log.info("向手机号：" + mobile + "发送验证码，验证码为：" + sendContent);
        return true;
    }
}
