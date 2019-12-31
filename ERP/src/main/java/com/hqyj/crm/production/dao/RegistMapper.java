package com.hqyj.crm.production.dao;

import java.util.List;

import com.hqyj.crm.production.entity.Regist;

public interface RegistMapper {
    int deleteByPrimaryKey(String productionId);

    int insert(Regist record);

    int insertSelective(Regist record);

    Regist selectByPrimaryKey(String productionId);

    int updateByPrimaryKeySelective(Regist record);

    int updateByPrimaryKey(Regist record);

	List<Regist> selectAllRegists(Regist regist);

	int deleteManyRegist(String[] idArr);
}