package com.mengxuegu.security.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * Created by Y_Coffee on 2020/8/21
 *
 * @author CoffeeY
 */
@Configuration
@Slf4j
public class ReloadMessageConfig {

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {

        //配置中文的信息
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages_zh_CN");
        log.info("调用：" + messageSource);
        return messageSource;
    }
}
