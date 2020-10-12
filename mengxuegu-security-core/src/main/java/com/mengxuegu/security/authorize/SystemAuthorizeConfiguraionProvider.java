//package com.mengxuegu.security.authorize;
//
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
//import org.springframework.stereotype.Component;
//
///**
// * 授权
// * Created by Y_Coffee on 2020/9/10
// *
// * @author CoffeeY
// */
//@Component
//public class SystemAuthorizeConfiguraionProvider implements AuthorizeConfigurerProvider {
//    @Override
//    public void configure(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
//
//        config
//                //匹配路径需要有什么类型权限访问
//                .antMatchers("/user").hasAuthority("sys:user")
//                //匹配路径，请求方式，有什么权限可以访问
//                .antMatchers(HttpMethod.GET, "/role").hasAuthority("sys:role")
//                //匹配路径，包含哪一种权限之一就可以访问，hasAnyRole系统默认加ROLE_,所以权限也需要加ROLE，hasAuthority不会加
//                .antMatchers("/permission")
//                .access("hasAuthority('sys:permission') or hasAnyRole('ADMIN')")
//        ;
//    }
//}
//



//                    开启后没有对应列表功能，暂时关闭，否则报403错误