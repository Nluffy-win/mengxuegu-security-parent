package com.mengxuegu.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mengxuegu.web.entities.SysPermission;
import com.mengxuegu.web.mapper.SysPermissionMapper;
import com.mengxuegu.web.service.SysPermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Y_Coffee on 2020/9/15
 * @author CoffeeY
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {
    @Override
    public List<SysPermission> findByID(Integer userId) {
        if (userId == null){
            return null;
        }
        List<SysPermission> sysPermissions = baseMapper.selectPermissionByUserId(userId);
        //如果查询到的用户是空，把null移除，否则list.size把null显示出1
        sysPermissions.remove(null);
        return sysPermissions;
    }
}
