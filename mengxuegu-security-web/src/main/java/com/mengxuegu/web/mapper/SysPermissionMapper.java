package com.mengxuegu.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mengxuegu.web.entities.SysPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Y_Coffee on 2020/9/15
 * @author CoffeeY
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    /**
     * 根据id查询对应权限
     * @param userId
     * @return
     */
    List<SysPermission> selectPermissionByUserId(@Param("userId") Integer userId);

}
