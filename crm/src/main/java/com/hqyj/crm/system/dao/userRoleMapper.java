package com.hqyj.crm.system.dao;

import com.hqyj.crm.system.entity.userRole;

public interface userRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(userRole record);

    int insertSelective(userRole record);

    userRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(userRole record);

    int updateByPrimaryKey(userRole record);
}