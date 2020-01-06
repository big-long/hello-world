package com.hqyj.crm.purchase.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hqyj.crm.baseinfo.entity.Goods;
import com.hqyj.crm.baseinfo.service.GoodsService;
import com.hqyj.crm.baseinfo.service.ProviderService;
import com.hqyj.crm.common.entity.Result;
import com.hqyj.crm.purchase.entity.Purchase;
import com.hqyj.crm.purchase.service.PurchaseService;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {
	@Autowired
	private PurchaseService purchaseService;
	@Autowired
	private GoodsService goodsService;
	@Autowired 
	private ProviderService providerService;

	@RequestMapping("/list")
	public String toPage(Model model) {
		Map<String, Object > data=purchaseService.queryPurchaseData();
		model.addAttribute("data", data);
		return "purchase/purchaseList";
	}
	@RequestMapping("/generatePurchaseId")
	@ResponseBody
	public Result generatePurchaseId() {
		String purchaseId="pe"+System.currentTimeMillis();
		return new Result(200,"success",purchaseId);
	}
	
	@RequestMapping("/getGoodsInfo")
	@ResponseBody
	public Result getGoodsInfo(Goods goods) {
		return goodsService.queryGoodsByGoodsName(goods);
	}
	@RequestMapping("/getPageInfo")
	@ResponseBody
	public Result getPageInfo(@RequestParam(defaultValue="1") Integer pageNum,@RequestParam(defaultValue="8") Integer pageSize) {
		return purchaseService.queryPageInfo(pageNum,pageSize);
	}
	@RequestMapping("/getPurchaseByKeyWord")
	@ResponseBody
	public Result getPurchaseByKeyWord(String keyWord, @RequestParam(defaultValue="1") Integer pageNum,@RequestParam(defaultValue="8") Integer pageSize) {
		return purchaseService.queryPageInfoByKeyWord(keyWord,pageNum,pageSize);
	}
	
	@RequestMapping("/getPurchaseInfo")
	@ResponseBody
	public Result getPurchaseInfo(String purchaseId) {
		return purchaseService.queryPurchaseInfo(purchaseId);
	}
	@RequestMapping("/update")
	@ResponseBody
	public Result updatePurchase(Purchase purchase) {
		return purchaseService.updatePurchase(purchase);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Result deletePurchase(String purchaseId) {
		return purchaseService.deletePurchase(purchaseId);
	}
	
	@RequestMapping("/deleteMany")
	@ResponseBody
	public Result deleteManyPurchase(String[] id_arr) {
		return purchaseService.deleteManyPurchase(id_arr);
	}
	
}
