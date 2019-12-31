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
import com.hqyj.crm.production.entity.Regist;
import com.hqyj.crm.production.service.RegistService;

@Controller
@RequestMapping("/regist")
public class RegistController {
	@Autowired
	private RegistService registService;
	@Autowired
	private ProductService productService;
	
	/**
	 * 
	 * <p>
	 * 	返回产品登记单页面
	 * </p>
	 * @author zdl
	 * @Date 2019年12月31日
	 * @param model 存放产品名称
	 * @return
	 */
	@RequestMapping("/list")
	public String  toPage(Model model) {
		List<Product> products = productService.queryProducts();
		model.addAttribute("products", products);
		return "customer/registList";
	}
	/**
	 * 
	 * <p>
	 * 	生产产品登记单的编号，R开头，后面跟时间戳
	 * </p>
	 * @author zdl
	 * @Date 2019年12月31日
	 * @return
	 */
	@RequestMapping(value = "/generateRegistId")
	@ResponseBody
	public Result generateRegistId() {
		String id="R"+System.currentTimeMillis();
		return new Result(200,"success",id);
	}
	/**
	 * 
	 * <p>
	 * 	根据产品名称，查询该产品相关的信息
	 * </p>
	 * @author zdl
	 * @Date 2019年12月31日
	 * @param product 产品名称
	 * @return
	 */
	@RequestMapping("/getProduct")
	@ResponseBody
	public Result getProductByProductName(Product product) {
		return productService.queryProductByProductName(product);
	}
	/**
	 * 
	 * <p>
	 * 	根据产品登记单编号查询登记单详细信息
	 * </p>
	 * @author zdl
	 * @Date 2019年12月31日
	 * @param productionId 产品登记单编号
	 * @return
	 */
	@RequestMapping("/getRegistInfo")
	@ResponseBody
	public Result queryOne(String productionId) {
		return registService.queryRegistByProductionId(productionId);
	}	
	/**
	 * 
	 * <p>
	 * 	获得分页信息，默认为第一页显示5条数据
	 * </p>
	 * @author zdl
	 * @Date 2019年12月31日
	 * @param regist 
	 * @param pageNum 当前页，默认第一页
	 * @param pageSize 页面大小，默认显示5条数据
	 * @return
	 */
	@RequestMapping("/getPageInfo")
	@ResponseBody
	public Result queryAll(Regist regist,@RequestParam(defaultValue="1") Integer pageNum ,@RequestParam(defaultValue="5") Integer pageSize) {
		return registService.queryRegists(regist,pageNum,pageSize);
	}

	/**
	 * 
	 * <p>
	 *  根据产品登记单编号查询数据库，存在该单号，则修改，否则新增
	 * </p>
	 * @author zdl
	 * @Date 2019年12月31日
	 * @param regist 产品登记单编号
	 * @return
	 */
	@RequestMapping("/updateRegist")
	@ResponseBody
	public Result updateRegist(Regist regist) {
		return registService.updateRegist(regist);
	}
	/**
	 * 
	 * <p>
	 * 	根据产品登记单编号删除对应的产品登记单
	 * </p>
	 * @author zdl
	 * @Date 2019年12月31日
	 * @param regist
	 * @return
	 */
	@RequestMapping("/deleteRegist")
	@ResponseBody
	public Result deleteRegist(Regist regist) {
		return registService.deleteRegist(regist.getProductionId());
	}
	/**
	 * 
	 * <p>
	 * 	根据前台传回来的产品登记单数组，删除对应的产品登记单记录，前台传回的是字符串数组，后台需要进行转换为int数组
	 * </p>
	 * @author zdl
	 * @Date 2019年12月31日
	 * @param id_arr
	 * @return
	 */
	@RequestMapping("/deleteMany")
	@ResponseBody
	public Result deleteManyRegist(String[] id_arr) {
		return registService.deleteManyRegist(id_arr);
	}
	

}
