package com.mengxuegu.security;

import com.mengxuegu.web.entities.SysUser;
import com.mengxuegu.web.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 查询数据库中的用户信息
 * Created by Y_Coffee on 2020/8/21
 *
 * @author CoffeeY
 */
@Slf4j
@Component("customUserDetailsService")
public class CustomUserDetailsService extends AbstractUserDetailsService {

    /**
     * 必须有password不然报错
     */
    private final PasswordEncoder passwordEncoder;
    private final SysUserService sysUserService;

    public CustomUserDetailsService(PasswordEncoder passwordEncoder, SysUserService sysUserService) {
        this.passwordEncoder = passwordEncoder;
        this.sysUserService = sysUserService;
    }


    @Override
    public SysUser findSysUser(String usernameOrMobile) {
        log.info("请求认证的的用户名：" + usernameOrMobile);
        return sysUserService.findByUsername(usernameOrMobile);
    }

}
