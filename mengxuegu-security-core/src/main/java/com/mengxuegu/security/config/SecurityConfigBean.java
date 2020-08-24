package com.mengxuegu.security.config;

import com.mengxuegu.security.authentication.mobile.SmsCodeSender;
import com.mengxuegu.security.authentication.mobile.SmsSend;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Y_Coffee on 2020/8/24
 * @author CoffeeY
 */
@Configuration
public class SecurityConfigBean {

    /**
     * @ConditionalOnMissingBean(SmsSend.class)
     * 默认采用SmsCodeSender实例，但是如果bean容器有其他smssend类型实例，则当前实例失效
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(SmsSend.class)
    public SmsSend smsSend(){
        return new SmsCodeSender();
    }
}
