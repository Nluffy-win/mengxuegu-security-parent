package com.mengxuegu.security.authentication.mobile;

/**
 * 短信发送的接口
 * Created by Y_Coffee on 2020/8/24
 * @author CoffeeY
 */
public interface SmsSend {

    /**
     *
     * @param mobile  手机号
     * @param content  发送短信的内容
     * @return
     */
    boolean sendSms(String mobile,String content);
}
