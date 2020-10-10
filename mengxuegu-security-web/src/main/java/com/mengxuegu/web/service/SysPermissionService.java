package com.mengxuegu.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mengxuegu.web.entities.SysPermission;

import java.util.List;

/**
 * Created by Y_Coffee on 2020/9/15
 * @author CoffeeY
 */
public interface SysPermissionService extends IService<SysPermission> {

    /**
     * 根据id查询
     * @param userId
     * @return
     */
    List<SysPermission> findByID(Integer userId);

}
