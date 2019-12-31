package com.hqyj.crm.production.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.hqyj.crm.common.entity.Result;
import com.hqyj.crm.production.entity.Product;
import com.hqyj.crm.production.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	/**
	 * 
	 * <p>
	 * 	根据关键字和类型查询产品
	 * </p>
	 * @author zdl
	 * @Date 2019年12月24日
	 * @param keyWord 关键字
	 * @param pageNum 当前页
	 * @param pageSize 页面大小
	 * @param category 类型
	 * @return 包含产品集合的分页信息
	 */
	@RequestMapping("/getProductByKeyWord")
	@ResponseBody
	public Result getProductByKeyWord(String keyWord,@RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "5") Integer pageSize,String category) {
		return productService.queryProductByKeyWord(keyWord,category,pageNum,pageSize);
	}
	/**
	 * 
	 * <p>
	 * 	根据产品id查询产品
	 * </p>
	 * @author zdl
	 * @Date 2019年12月24日
	 * @param product 主要包含产品id信息
	 * @return
	 */
	@RequestMapping("/getProductInfo")
	@ResponseBody
	public Result getProductInfo(Product product) {
		return productService.queryProductByProduct(product);
	}
	/**
	 * 
	 * <p>
	 * 	根据产品的名字查询产品信息 暂时没有用到
	 * </p>
	 * @author zdl
	 * @Date 2019年12月24日
	 * @param product 主要包含产品名称信息
	 * @return 返回对应的产品信息
	 */
	@RequestMapping("/searchProduct")
	public Result selectOne(Product product) {
		return productService.queryProductByProductName(product);
	}
	/**
	 * 
	 * <p>
	 * 	返回到产品页面
	 * </p>
	 * @author zdl
	 * @Date 2019年12月24日
	 * @param model 视图模型
	 * @return 返回到产品页面
	 */
	@RequestMapping("/list")
	public String selectAll(Model model) {
		Set<String> categorys= productService.queryCategroys();
		model.addAttribute("categorys", categorys);
		return "customer/productList";
	}
	/**
	 * 
	 * <p>
	 * 	根据前端传回来的页面数据，查询相应的数据
	 * </p>
	 * @author zdl
	 * @Date 2019年12月24日
	 * @param product 包含产品名称和类别信息
	 * @param pageNum 当前页
	 * @param pageSize 页面大小
	 * @return 返回包含产品信息的分页数据
	 */
	@RequestMapping("/getPageInfo")
	@ResponseBody
	public Result getData( Product product, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "5") Integer pageSize) {
		return productService.queryProducts(product, pageNum, pageSize);
	}

	/**
	 * 
	 * <p>
	 * 	更新产品信息或者新增产品信息 根据是否有产品Id来决定是新增还是修改
	 * </p>
	 * @author zdl
	 * @Date 2019年12月24日
	 * @param product 包含产品信息
	 * @return 返回修改或新增后的产品信息
	 */
	@RequestMapping("/updateProduct")
	@ResponseBody
	public Result updateProduct(Product product) {
		if (product.getProductId() != null && !"".equals(product.getProductId())) {
			return productService.updateProduct(product);
		} else {
			return productService.insertProduct(product);
		}
	}
	/**
	 * 
	 * <p>
	 * 	删除产品信息
	 * </p>
	 * @author zdl
	 * @Date 2019年12月24日
	 * @param product 根据产品id删除对应的产品
	 * @return 返回删除影响的行数
	 */
	@RequestMapping("/deleteProduct")
	@ResponseBody
	public Result deleteProduct(Product product) {
		return productService.deleteProductByProductId(product.getProductId());
	}
	/**
	 * 
	 * <p>
	 * 	批量删除产品
	 * </p>
	 * @author zdl
	 * @Date 2019年12月24日
	 * @param id_arr 前端传回来的产品id数组
	 * @return 返回是否删除成功
	 */
	@RequestMapping("/deleteMany")
	@ResponseBody
	public Result deleteManyProduct(String[] id_arr) {
		return productService.deleteManyProducts(id_arr);
	}
}
