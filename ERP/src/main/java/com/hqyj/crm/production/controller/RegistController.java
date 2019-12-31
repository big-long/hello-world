package com.hqyj.crm.production.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hqyj.crm.common.entity.Result;
import com.hqyj.crm.production.entity.Product;
import com.hqyj.crm.production.entity.Regist;
import com.hqyj.crm.production.service.ProductService;
import com.hqyj.crm.production.service.RegistService;

@Controller
@RequestMapping("/regist")
public class RegistController {
	@Autowired
	private RegistService registService;
	@Autowired
	private ProductService productService;
	@RequestMapping("/list")
	public String  toPage(Model model) {
		List<Product> products = productService.queryProducts();
		model.addAttribute("products", products);
		return "customer/registList";
	}
	@RequestMapping(value = "/generateRegistId")
	@ResponseBody
	public Result generateRegistId() {
		String id="R"+System.currentTimeMillis();
		return new Result(200,"success",id);
	}
	@RequestMapping("/getProduct")
	@ResponseBody
	public Result getProductByProductName(Product product) {
		return productService.queryProductByProductName(product);
	}
	@RequestMapping("/getRegistInfo")
	@ResponseBody
	public Result queryOne(String productionId) {
		return registService.queryRegistByProductionId(productionId);
	}
	@RequestMapping("/getPageInfo")
	@ResponseBody
	public Result queryAll(Regist regist,@RequestParam(defaultValue="1") Integer pageNum ,@RequestParam(defaultValue="5") Integer pageSize) {
		return registService.queryRegists(regist,pageNum,pageSize);
	}

	/*
	 * @RequestMapping("/insertRegist")
	 * 
	 * @ResponseBody public Regist insertRegist(Regist regist) { return
	 * registService.insertRegist(regist); }
	 */
	@RequestMapping("/updateRegist")
	@ResponseBody
	public Result updateRegist(Regist regist) {
		return registService.updateRegist(regist);
	}
	@RequestMapping("/deleteRegist")
	@ResponseBody
	public Result deleteRegist(Regist regist) {
		return registService.deleteRegist(regist.getProductionId());
	}
	@RequestMapping("/deleteMany")
	@ResponseBody
	public Result deleteManyRegist(String[] id_arr) {
		return registService.deleteManyRegist(id_arr);
	}
}
