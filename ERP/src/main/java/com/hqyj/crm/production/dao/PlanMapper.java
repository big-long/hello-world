package com.hqyj.crm.production.dao;

import java.util.List;

import com.hqyj.crm.production.entity.Plan;

public interface PlanMapper {
    int deleteByPrimaryKey(String planId);

    int insert(Plan record);

    int insertSelective(Plan record);

    Plan selectByPrimaryKey(String planId);

    int updateByPrimaryKeySelective(Plan record);

    int updateByPrimaryKey(Plan record);

	List<Plan> selectAllPlan();

	int deleteManyPlan(String[] id_arr);

	List<Plan> selectPlanByKeyWord(String keyWord);
}