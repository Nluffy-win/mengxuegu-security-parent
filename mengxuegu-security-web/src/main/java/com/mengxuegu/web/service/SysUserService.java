package com.mengxuegu.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mengxuegu.web.entities.SysUser;

/**
 * IService<T> 提供了对T表操作的很多抽象方法，比如：批量操作
 * Created by Y_Coffee on 2020/9/14
 *
 * @author CoffeeY
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 根据用户名查询
     * @return
     */
    SysUser findByUsername(String username);

    /**
     * 根据手机号码查询
     * @return
     */
    SysUser findByMobile(String mobile);

}
