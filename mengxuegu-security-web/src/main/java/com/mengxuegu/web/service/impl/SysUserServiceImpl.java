package com.mengxuegu.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mengxuegu.web.entities.SysUser;
import com.mengxuegu.web.mapper.SysUserMapper;
import com.mengxuegu.web.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * Created by Y_Coffee on 2020/9/14
 *
 * @author CoffeeY
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Override
    public SysUser findByUsername(String username) {
        if (StringUtils.isEmpty(username)){
            return new SysUser();
        }
        SysUser user = baseMapper.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, username));
        return user;
    }

    @Override
    public SysUser findUsername(String username) {
        if (StringUtils.isEmpty(username)){
            return new SysUser();
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username",username);
        SysUser user = baseMapper.selectOne(queryWrapper);
        return user;
    }
}
