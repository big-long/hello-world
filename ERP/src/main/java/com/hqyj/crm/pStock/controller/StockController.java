package com.hqyj.crm.pStock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hqyj.crm.common.entity.Result;
import com.hqyj.crm.pStock.entity.Stock;
import com.hqyj.crm.pStock.service.StockService;
import com.hqyj.crm.production.entity.Regist;
import com.hqyj.crm.production.service.RegistService;

@Controller
@RequestMapping("/stock")
public class StockController {
	@Autowired
	private StockService stockService;
	@Autowired
	private RegistService registService;

	@RequestMapping("/list")
	public String toPage(Model model) {
		List<Regist> regists =registService.queryAllRegist();
		model.addAttribute("regists", regists);
		return "pStock/stockList";
	}
	
	@RequestMapping("/getPageInfo")
	@ResponseBody
	public Result getPageInfo(@RequestParam(defaultValue="1") Integer pageNum,@RequestParam (defaultValue="8") Integer  pageSize) {
		return stockService.queryPageInfo(pageNum,pageSize);
	}
	@RequestMapping("/update")
	@ResponseBody
	public Result update(Stock stock) {
		return stockService.updateStock(stock);
	}
	@RequestMapping("/generateStockId")
	@ResponseBody
	public Result getStockId( ) {
		String stockId="SK"+System.currentTimeMillis();
		return new Result(200, "success",stockId);
	}
	@RequestMapping("/getRegistInfo")
	@ResponseBody
	public Result getRegistInfo(String productionId ) {
		return registService.queryRegistByProductionId(productionId);
	}
	
}
