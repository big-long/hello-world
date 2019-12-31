package com.hqyj.crm.production.service;

import com.hqyj.crm.common.entity.Result;
import com.hqyj.crm.production.entity.Plan;

public interface PlanService {

	Result queryPlanPage(Integer pageNum, Integer pageSize);

	Result updatePlanByPlan(Plan plan);

	Result queryPlanInfo(String planId);

	Result deletePlanInfo(String planId);

	Result deleteManyPlan(String[] id_arr);

	Result queryPlanByKeyWord(String keyWord,Integer pageNum,Integer pageSize);


}
