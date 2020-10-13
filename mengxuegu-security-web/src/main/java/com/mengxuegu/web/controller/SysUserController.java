package com.mengxuegu.web.controller;

import com.mengxuegu.base.result.MengxueguResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

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
        String form = HTML_PREFIX + "user-form";
        return form;
    }

    /**
     * @param id
     * @return
     * @PostAuthorize 返回值code等于200，则调用成功，不然抛出403
     * @RequestMapping @PathVariable将值赋予{id}
     */
    @PostAuthorize("returnObject.code == 200")
    @RequestMapping("/{id}")
    @ResponseBody
    public MengxueguResult deleteId(@PathVariable("id") Integer id) {

        if (id < 0) {
            return MengxueguResult.build(500, "参数不能小于0");
        }
        return MengxueguResult.ok();
    }

    /**
     * @param ids
     * @return filterTarget过滤哪一个参数, value是判断filterObject元素的判断条件，true都会返回
     */
    @PreFilter(filterTarget = "ids", value = "filterObject > 0")
    @RequestMapping("/delete/{ids}")
    @ResponseBody
    public MengxueguResult deleteIds(@PathVariable List<Integer> ids) {
        return MengxueguResult.ok(ids);
    }

    /**
     * @return
     * @PostFilter filterObject返回的是集合中的每一个元素，表达式为true都会返回
     */
    @PostFilter("filterObject != authentication.principal.username")
    @RequestMapping("/list")
    @ResponseBody
    public List<String> page() {
//        List<String> userList = Lists.newArrayList("zhaojie","qian","sun","li");
        List<String> users = new ArrayList<>();
        users.add("zhaojie");
        users.add("qian");
        users.add("sun");
        users.add("li");
        return users;
    }
}
