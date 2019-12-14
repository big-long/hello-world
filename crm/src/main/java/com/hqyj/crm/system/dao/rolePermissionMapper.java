package com.hqyj.crm.system.dao;

import com.hqyj.crm.system.entity.rolePermission;

public interface rolePermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(rolePermission record);

    int insertSelective(rolePermission record);

    rolePermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(rolePermission record);

    int updateByPrimaryKey(rolePermission record);
}