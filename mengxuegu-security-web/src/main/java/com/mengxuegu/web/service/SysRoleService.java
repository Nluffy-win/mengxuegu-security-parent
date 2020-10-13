package com.mengxuegu.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mengxuegu.web.entities.SysRole;

/**
 * @Auther: 梦学谷 www.mengxuegu.com
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 分页查询角色列表
     * @param page 分页参数
     * @param sysRole 条件查询对象，会取name属性值作为条件
     * @return
     */
    IPage<SysRole> selectPage(Page<SysRole> page, SysRole sysRole);

    /**
     * 通过角色id查询角色信息和所拥有权限信息
     * @param id
     * @return
     */
    SysRole findById(Long id);

}
