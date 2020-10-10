package com.mengxuegu.security;

import com.mengxuegu.web.entities.SysPermission;
import com.mengxuegu.web.entities.SysUser;
import com.mengxuegu.web.service.SysPermissionService;
import com.mengxuegu.web.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Lists;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 查询数据库中的用户信息
 * Created by Y_Coffee on 2020/8/21
 *
 * @author CoffeeY
 */
@Slf4j
@Component("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {


    private final PasswordEncoder passwordEncoder;
    private final SysUserService sysUserService;
    private final SysPermissionService sysPermissionService;

    public CustomUserDetailsService(PasswordEncoder passwordEncoder, SysUserService sysUserService, SysPermissionService sysPermissionService) {
        this.passwordEncoder = passwordEncoder;
        this.sysUserService = sysUserService;
        this.sysPermissionService = sysPermissionService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("请求认证的的用户名：" + username);

//        if (!"zhaojie".equalsIgnoreCase(username)) {
//            throw new UsernameNotFoundException("用户名或者密码错误");
//        }
//        // 假设当前这个用户在数据库当中存储的密码是8888
//        String password = passwordEncoder.encode("8888");

        // 1. 通过请求的用户名去数据库中查询用户信息
        SysUser users = sysUserService.findByUsername(username);

        if (users == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }

        // 2. 查询该用户有哪一些权限
        List<SysPermission> permissions = sysPermissionService.findByID(users.getId());

        if (CollectionUtils.isEmpty(permissions)) {
            return users;
        }

        // 在左侧菜单 动态渲染会使用，目前先把它都传入
        users.setPermissions(permissions);

        // 3. 封装用户信息和权限信息
        //用户信息sysuser自动封装
        //封装权限
        List<GrantedAuthority> authorities = Lists.newArrayList();
        for (SysPermission sp : permissions) {
            String code = sp.getCode();
            //因为code是String类型，需要转换才能存入
            authorities.add(new SimpleGrantedAuthority(code));
        }
        users.setAuthorities(authorities);
        // 4. 交给springsecurity自动进行身份认证

//        // username 用户名, password 是数据库中这个用户存储的密码,
//        // authorities 是权限资源标识, springsecurity会自动的判断用户是否合法,
//        String authorityString = "sys:user,sys:role,ROLE_ADMIN,sys:user:add";
//        return new User(username, password,
//                AuthorityUtils.commaSeparatedStringToAuthorityList(authorityString));
        return users;
    }
}
