package com.hqyj.crm.production.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hqyj.crm.baseinfo.entity.Goods;
import com.hqyj.crm.baseinfo.service.GoodsService;
import com.hqyj.crm.common.entity.Result;
import com.hqyj.crm.production.entity.PickList;
import com.hqyj.crm.production.service.PickListService;

@Controller
@RequestMapping("/picklist")
public class PickListController {
	@Autowired
	private PickListService pickListService;
	@Autowired
	private GoodsService goodsService;

	@RequestMapping("/list")
	public String toPage(Model model) {
		List<Goods> goods=goodsService.queryGoods();
		model.addAttribute("goodss", goods);
		return "production/picklist";
	}

	@RequestMapping("/getPageInfo")
	@ResponseBody
	public Result getPageInfo(@RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "8") Integer pageSize) {
		return pickListService.queryPicklistPage(pageNum, pageSize);
	}

	@RequestMapping("/generatePicklistId")
	@ResponseBody
	public Result getPicklistId() {
		String PicklistId = "pl" + System.currentTimeMillis();
		return new Result(200, "success", PicklistId);
	}

	@RequestMapping("/update")
	@ResponseBody
	public Result updatePicklist(PickList pickList) {
		return pickListService.updatePicklistByPicklist(pickList);
	}

	@RequestMapping("/getPicklistInfo")
	@ResponseBody
	public Result getPicklistInfo(String plId) {
		return pickListService.queryPicklistInfo(plId);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Result deletePicklist(String plId) {
		return pickListService.deletePicklistInfo(plId);
	}

	@RequestMapping("/deleteMany")
	@ResponseBody
	public Result deleteMany(String[] id_arr) {
		return pickListService.deleteManyPicklist(id_arr);
	}

	@RequestMapping("/getPicklistByKeyWord")
	@ResponseBody
	public Result getPicklistByKeyWord(String keyWord, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "8") Integer pageSize) {
		return pickListService.queryPicklistByKeyWord(keyWord, pageNum, pageSize);
	}
}
