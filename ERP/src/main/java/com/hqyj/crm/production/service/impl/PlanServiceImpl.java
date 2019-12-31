package com.hqyj.crm.production.service.impl;

import java.util.List;

import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.crm.common.entity.Result;
import com.hqyj.crm.production.dao.PlanMapper;
import com.hqyj.crm.production.entity.Plan;
import com.hqyj.crm.production.service.PlanService;
@Service
public class PlanServiceImpl implements PlanService {
	@Autowired
	private PlanMapper planMapper;

	@Override
	public Result queryPlanPage(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Plan> plans=planMapper.selectAllPlan();
		return new Result(200, "success",new PageInfo<Plan>(plans)) ;
	}

	@Override
	public Result updatePlanByPlan(Plan plan) {
		String planId = plan.getPlanId();
		if(planId==null ||"".equals(planId)) {
			return new Result(500,"编号为空");
		}
		Plan plan_db=planMapper.selectByPrimaryKey(planId);
		if(plan_db==null) {
			int n=planMapper.insertSelective(plan);
			if(n==0) {
				return new Result(500, "新增失败");
			}
		}else {
			int n=planMapper.updateByPrimaryKey(plan);
			if(n==0) {
				return new Result(200, "修改失败");
			}
		}
		
		return new Result(200, "success");
	}

	@Override
	public Result queryPlanInfo(String planId) {
		Plan plan = planMapper.selectByPrimaryKey(planId);
		return new Result(200,"success",plan);
	}

	@Override
	public Result deletePlanInfo(String planId) {
		int n=planMapper.deleteByPrimaryKey(planId);
		if(n==0) {
			return new Result(500,"删除失败");
		}
		return new Result(200,"success");
	}

	@Override
	public Result deleteManyPlan(String[] id_arr) {
		if(id_arr==null ||id_arr.length==0) {
			return new Result(500,"请至少选择一条数据");
		}
		int n=planMapper.deleteManyPlan(id_arr);
		if(n==id_arr.length) {
			return new Result(200,"success");
		}
		return new Result(500,"系统错误");
	}

	@Override
	public Result queryPlanByKeyWord(String keyWord,Integer pageNum,Integer pageSize) {
		keyWord ="%"+keyWord+"%";
		List<Plan> plans = planMapper.selectPlanByKeyWord(keyWord);
		PageHelper.startPage(pageNum, pageSize);
		return new Result(200,"success",new PageInfo<Plan>(plans));
	}
}
