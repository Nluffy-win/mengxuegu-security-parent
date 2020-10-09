package com.mengxuegu.web.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Lists;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Y_Coffee on 2020/9/14
 *
 * @author CoffeeY
 * Serializable 实现序列化，为了远程数据传输，不然会报错
 */
@Data
public class SysRole implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色描述
     */
    private String remark;

    private Date createDate;
    private Date updateDate;

    /**
     * 存储当前角色的权限资源对象集合
     * 修改角色时用到
     */
    @TableField(exist = false)
    private List<SysPermission> perList = Lists.newArrayList();

    /**
     * 查询当前角色所拥有的全部权限，放入当前list集合
     * 存储当前角色的权限资源ID集合
     * 修改角色时用到
     */
    @TableField(exist = false)
    private List<Integer> perIds = Lists.newArrayList();

    public List<Integer> getPerIds() {
        if (CollectionUtils.isNotEmpty(perList)) {
            perIds = Lists.newArrayList();
            for (SysPermission per : perList) {
                perIds.add(per.getId());
            }
        }
        return perIds;
    }
}
