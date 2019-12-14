package com.hqyj.crm.system.dao;

import com.hqyj.crm.system.entity.Permission;

public interface PermissionMapper {
    int insert(Permission record);

    int insertSelective(Permission record);
}