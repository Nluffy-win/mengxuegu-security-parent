package com.mengxuegu.security;

import com.mengxuegu.security.authentication.mobile.SmsSend;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Y_Coffee on 2020/8/24
 *
 * @author CoffeeY
 */
@Slf4j
public class MobileSmsCodeSender implements SmsSend {
    @Override
    public boolean sendSms(String mobile, String content) {
        log.info("Web应用新的短信验证码接口---向手机号" + mobile + "发送了验证码为：" + content);

        return false;
    }
}
