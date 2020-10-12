package com.mengxuegu.security;

import com.mengxuegu.web.entities.SysUser;
import com.mengxuegu.web.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 手机号查询用户的信息
 * Created by Y_Coffee on 2020/8/27
 *
 * @author CoffeeY
 */
@Slf4j
@Component("mobileUserDetailsService")
public class MobileUserDetailsService extends AbstractUserDetailsService {

    private final SysUserService sysUserService;

    public MobileUserDetailsService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    @Override
    public SysUser findSysUser(String usernameOrMobile) {
        log.info("验证的手机号：" + usernameOrMobile);
        //通过手机查询用户信息
        SysUser users = sysUserService.findByMobile(usernameOrMobile);
        return users;
    }

}
