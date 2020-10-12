package com.mengxuegu.web.controller;

import com.mengxuegu.base.result.MengxueguResult;
import com.mengxuegu.web.entities.SysPermission;
import com.mengxuegu.web.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Y_Coffee on 2020/9/9
 *
 * @author CoffeeY
 */
@Controller
@RequestMapping("/permission")
public class SysPermissionController {

    private static final String HTML_PREFIX = "/system/permission/";

    //@PreAuthorize("hasAuthority('sys:permission')")
    @GetMapping(value = {"/", ""})
    public String permission() {
        String permission = HTML_PREFIX + "permission-list";
        return permission;
    }

    @Autowired
    private SysPermissionService sysPermissionService;

    //@PreAuthorize("hasAuthority('sys:permission:list')")
    @PostMapping("/list") // /permission/list
    @ResponseBody // 不要少了,返回json
    public MengxueguResult list() {
        // MyBatis-plus已经提供的,查询SysPermission表中的所有记录
        List<SysPermission> list = sysPermissionService.list();
        return MengxueguResult.ok(list);
    }
}
