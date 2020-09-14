package com.mengxuegu.web.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Lists;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Created by Y_Coffee on 2020/9/14
 *
 * @author CoffeeY
 */
@Data
public class SysUser implements UserDetails {

    /**
     * 主键自增长
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    /**
     * 密码需要加密后存储
     */
    private String password;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;
    private String nickName;
    private String mobile;
    private String email;
    private String createDate;
    private String updateDate;

    /**
     * 指定该字段在数据库不存在
     */
    @TableField(exist = false)
    private Collection<? extends GrantedAuthority> authorities;

    /**
     * 拥有角色的集合
     */
    @TableField(exist = false)
    private List<SysRole> roleList;

    /**
     * 获取所有角色id
     */
    @TableField(exist = false)
    private List<Integer> roleIds;

    public List<Integer> getRoleIds() {
        //判断是否不是空，不是空的就true
        if (CollectionUtils.isNotEmpty(roleList)) {
            roleIds = Lists.newArrayList();
            for (SysRole role : roleList) {
                roleIds.add(role.getId());
            }
        }
        return roleIds;
    }

    /**
     * 封装当前用户拥有的权限资源对象
     */
    @TableField(exist = false)
    private List<SysPermission> permissionList;
}
