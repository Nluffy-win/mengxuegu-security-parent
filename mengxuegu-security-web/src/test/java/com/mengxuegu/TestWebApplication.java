package com.mengxuegu;

import com.mengxuegu.web.entities.SysUser;
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
        List<SysUser> list = sysUserService.list();
        System.out.println("list:" + list);
    }

    @Test
    public void test111(){
        System.out.println("************");
    }

}
