package com.mengxuegu.web.controller;

import com.mengxuegu.base.result.MengxueguResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Y_Coffee on 2020/9/9
 *
 * @author CoffeeY
 */
@Controller
@RequestMapping("/user")
@Slf4j
public class SysUserController {

    private static final String HTML_PREFIX = "/system/user/";

    @PreAuthorize("hasAuthority('sys:user')")
    @GetMapping(value = {"/", ""})
    public String user() {
        String user = HTML_PREFIX + "user-list";
        return user;
    }

    @PreAuthorize("hasAnyAuthority('sys:user:add','sys:user:edit')")
    @GetMapping("/form")
    public String form() {
        return HTML_PREFIX + "user-form";
    }


    @PostAuthorize("returnObject.code == 200")  //返回值code等于200，则调用成功，不然抛出403
    @RequestMapping("/{id}")  //@PathVariable将值赋予{id}
    @ResponseBody
    public MengxueguResult deleteId(@PathVariable("id") Integer id) {

        if (0 >= id) {
            return MengxueguResult.build(500, "参数不能小于0");
        }
        return MengxueguResult.ok();
    }
}
