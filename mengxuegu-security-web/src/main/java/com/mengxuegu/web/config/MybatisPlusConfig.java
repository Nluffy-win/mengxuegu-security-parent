package com.mengxuegu.web.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Y_Coffee on 2020/9/14
 *
 * @author CoffeeY
 * @EnableTransactionManagement 开启事物管理
 * @MapperScan("") 扫描mapper接口
 */
@EnableTransactionManagement
@MapperScan("com.mengxuegu.web.mapper")
@Configuration
public class MybatisPlusConfig {

    /**
     * 分页查询插件
     *
     * @return
     */

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
