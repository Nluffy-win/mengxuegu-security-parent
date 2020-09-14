package com.mengxuegu.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mengxuegu.web.entities.SysUser;

/**
 * 继承BaseMapper<T> 接口，它提供了很多对T表的数据操作方法
 * 因为mybatis plus 已经指定mapper，所以不需要加入容器
 * Created by Y_Coffee on 2020/9/14
 *
 * @author CoffeeY
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
}
