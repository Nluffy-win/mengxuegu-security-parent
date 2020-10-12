package com.mengxuegu.security;

import com.mengxuegu.web.entities.SysPermission;
import com.mengxuegu.web.entities.SysUser;
import com.mengxuegu.web.service.SysPermissionService;
import com.mengxuegu.web.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 手机号查询用户的信息
 * Created by Y_Coffee on 2020/8/27
 *
 * @author CoffeeY
 */
@Slf4j
@Component("mobileUserDetailsService")
public class MobileUserDetailsService implements UserDetailsService {

    private final SysUserService sysUserService;
    private final SysPermissionService sysPermissionService;

    public MobileUserDetailsService(SysUserService sysUserService, SysPermissionService sysPermissionService) {
        this.sysUserService = sysUserService;
        this.sysPermissionService = sysPermissionService;
    }

    @Override
    public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {

        log.info("验证的手机号：" + mobile);
        //通过手机查询用户信息
        SysUser users = sysUserService.findByMobile(mobile);
        if (users == null) {
            throw new UsernameNotFoundException("手机号码尚未注册");
        }
        //如有此用户，查询对应权限
        List<SysPermission> permissions = sysPermissionService.findByID(users.getId());

        if (permissions == null) {
            return users;
        }

        // 在左侧菜单 动态渲染会使用，目前先把它都传入
        users.setPermissions(permissions);

        //封装用户信息
        List<GrantedAuthority> authorities = Lists.newArrayList();
        for (SysPermission sp : permissions) {
            String code = sp.getCode();
            //因为code是String类型，需要转换才能存入
            authorities.add(new SimpleGrantedAuthority(code));
        }

        users.setAuthorities(authorities);
//        return new User("zhaojie", "", true, true, true, true,
//                AuthorityUtils.commaSeparatedStringToAuthorityList("ADMIN"));

        return users;
    }
}
