package com.mengxuegu;

import com.mengxuegu.web.entities.SysPermission;
import com.mengxuegu.web.entities.SysRole;
import com.mengxuegu.web.entities.SysUser;
import com.mengxuegu.web.service.SysPermissionService;
import com.mengxuegu.web.service.SysRoleService;
import com.mengxuegu.web.service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by Y_Coffee on 2020/9/14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestWebApplication {

    @Autowired
    SysUserService sysUserService;

    @Test
    public void testSysUser() {
//        List<SysUser> list = sysUserService.list();
//        System.out.println("list:" + list);
        SysUser admin = sysUserService.findByUsername("admin");
        System.out.println(admin);
    }

    @Test
    public void testSysUser1() {
        SysUser admin = sysUserService.findByMobile("admin");
        System.out.println(admin);
    }

    @Test
    public void test111() {
        System.out.println("************");
    }

    @Autowired
    SysRoleService sysRoleService;

    @Test
    public void testSysRole() {
        SysRole role = sysRoleService.getById(9);
        System.out.println(role);
    }

    @Autowired
    SysPermissionService sysPermissionService;

    @Test
    public void testSysPermission(){
        SysPermission permission = sysPermissionService.getById(18);
        System.out.println(permission);
    }

    @Test
    public void testSysPermissions(){
        List<SysPermission> byID = sysPermissionService.findByID(20);
        System.out.println(byID.size());
    }

}
