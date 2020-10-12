package com.mengxuegu.security;

import com.mengxuegu.web.entities.SysPermission;
import com.mengxuegu.web.entities.SysUser;
import com.mengxuegu.web.service.SysPermissionService;
import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * 根据用户名和手机号查询的公共方法
 * Created by Y_Coffee on 2020/10/12
 * @author CoffeeY
 */
public abstract class AbstractUserDetailsService implements UserDetailsService {

    @Autowired
    SysPermissionService sysPermissionService;

    /**
     * 将方法交给子类去查询用户名密码，手机号
     * @param usernameOrMobile
     * @return
     */
    public abstract SysUser findSysUser(String usernameOrMobile);

    @Override
    public UserDetails loadUserByUsername(String usernameOrMobile) throws UsernameNotFoundException {

        // 1. 通过请求的用户名去数据库中查询用户信息
        SysUser sysUser = findSysUser(usernameOrMobile);
        //引用
        findSysPermission(sysUser);
        return sysUser;
    }

    private void findSysPermission(SysUser sysUser){
        if (sysUser == null){
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        // 2. 查询该用户有哪一些权限
        List<SysPermission> permissions = sysPermissionService.findByID(sysUser.getId());
        if (CollectionUtils.isNotEmpty(permissions)){
            return ;
        }

        // 在左侧菜单 动态渲染会使用，目前先把它都传入
        sysUser.setPermissions(permissions);

        // 3. 封装用户信息和权限信息
        //用户信息sysuser自动封装
        //封装权限
        List<GrantedAuthority> authorities = Lists.newArrayList();
        for (SysPermission sp : permissions){
            String code = sp.getCode();
            //因为code是String类型，需要转换才能存入
            authorities.add(new SimpleGrantedAuthority(code));
        }
        sysUser.setAuthorities(authorities);

        // 4. 交给springsecurity自动进行身份认证
    }
}
