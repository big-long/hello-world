package com.hqyj.crm.production.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hqyj.crm.baseinfo.entity.Product;
import com.hqyj.crm.baseinfo.service.ProductService;
import com.hqyj.crm.common.entity.Result;
import com.hqyj.crm.production.entity.Plan;
import com.hqyj.crm.production.service.PlanService;

@Controller
@RequestMapping("/plan")
public class PlanController {
	@Autowired
	private PlanService planService;
	@Autowired
	private ProductService productService;
	@RequestMapping("/list")
	public String  toPage(Model model) {
		List<Product> products = productService.queryProducts();
		model.addAttribute("products", products);
		return "production/planList";
	}
	@RequestMapping("/getPageInfo")
	@ResponseBody
	public Result getPageInfo(@RequestParam(defaultValue="1") Integer pageNum,@RequestParam(defaultValue="8") Integer pageSize) {
		return planService.queryPlanPage(pageNum,pageSize);
	}
	@RequestMapping("/generatePlanId")
	@ResponseBody
	public Result getPlanId() {
		String planId="p"+System.currentTimeMillis();
		return new Result(200, "success",planId);
	}
	@RequestMapping("/updatePlan")
	@ResponseBody
	public Result updatePlan(Plan plan) {
		return planService.updatePlanByPlan(plan);
	}
	@RequestMapping("/getPlanInfo")
	@ResponseBody
	public Result getPlanInfo(String planId) {
		return planService.queryPlanInfo(planId);
	}
	@RequestMapping("/deletePlan")
	@ResponseBody
	public Result deletePlan(String planId) {
		return planService.deletePlanInfo(planId);
	}
	@RequestMapping("/deleteMany")
	@ResponseBody
	public Result deleteMany(String[] id_arr) {
		return planService.deleteManyPlan(id_arr);
	}
	@RequestMapping("/getPlanByKeyWord")
	@ResponseBody
	public Result getPlanByKeyWord(String keyWord,@RequestParam(defaultValue="1") Integer pageNum,@RequestParam(defaultValue="8") Integer pageSize) {
		return planService.queryPlanByKeyWord(keyWord,pageNum,pageSize);
	}
	
}
