package com.hqyj.crm.pStock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hqyj.crm.common.entity.Result;
import com.hqyj.crm.pStock.service.ProStockService;

@Controller
@RequestMapping("/proStock")
public class ProStockController {
	
	@Autowired
	private ProStockService proStockService;
	
	@RequestMapping("/list")
	public String toPage() {
		return "pStock/proStockList";
	}
	@RequestMapping("/getPageInfo")
	@ResponseBody
	public Result  getPageInfo(@RequestParam(defaultValue="1") Integer pageNum,@RequestParam(defaultValue="8") Integer pageSize) {
		return proStockService.queryPageInfo(pageNum,pageSize);
	}
}
