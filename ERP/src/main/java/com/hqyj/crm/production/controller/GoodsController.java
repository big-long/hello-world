package com.hqyj.crm.production.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hqyj.crm.common.entity.Result;
import com.hqyj.crm.production.entity.Goods;
import com.hqyj.crm.production.service.GoodsService;
import com.hqyj.crm.production.service.ProviderService;

@Controller
@RequestMapping("/goods")
public class GoodsController {
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private ProviderService providerService;
	/**
	 * 
	 * <p>
	 * 	通过关键字和类型查询商品
	 * </p>
	 * @author zdl
	 * @Date 2019年12月24日
	 * @param keyWord 关键字
	 * @param pageNum 当前页
	 * @param pageSize 页面大小
	 * @param category 类别
	 * @return 包含商品的分页信息
	 */
	@RequestMapping("/getGoodsByKeyWord")
	@ResponseBody
	public Result getGoodsByKeyWord(String keyWord,@RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "5") Integer pageSize,String category) {
		return goodsService.queryGoodsByKeyWord(keyWord,category,pageNum,pageSize);
	}
	/**
	 * 
	 * <p>
	 * 	通过商品的Id查询商品
	 * </p>
	 * @author zdl
	 * @Date 2019年12月24日
	 * @param goods 主要使用goodsId
	 * @return 返回具体的某个商品
	 */
	@RequestMapping("/getGoodsInfo")
	@ResponseBody
	public Result getGoodsInfo(Goods goods) {
		return goodsService.queryGoodsByGoods(goods);
	}
	/**
	 * 
	 * <p>
	 * 	查询商品通过商品名称  暂时没有使用
	 * </p>
	 * @author zdl
	 * @Date 2019年12月24日
	 * @param goods 商品名称
	 * @return 返回具体的商品
	 */
	@RequestMapping("/searchGoods")
	public Result selectOne(Goods goods) {
		return goodsService.queryGoodsByGoodsName(goods);
	}
	/**
	 * 	
	 * <p>
	 * 	跳转到商品页面
	 * </p>
	 * @author zdl
	 * @Date 2019年12月24日
	 * @param model 返回类型集合
	 * @return 返回商品页面
	 */
	@RequestMapping("/list")
	public String selectAll(Model model) {
//		PageInfo<Goods> Goodss = goodsService.queryGoodss(Goods, pageNum, pageSize);
		Set<String> categorys= goodsService.queryCategroys();
		List<String> providerNameStrings= providerService.queryAllProviderName();
		model.addAttribute("categorys", categorys);
		model.addAttribute("providers", providerNameStrings);
		return "customer/goodsList";
	}
	/**
	 * 
	 * <p>
	 * 	通过ajax的gotoPage函数调用改方法，返回查询的商品分页信息
	 * </p>
	 * @author zdl
	 * @Date 2019年12月24日
	 * @param goods 根据查询出来的信息进行页面跳转
	 * @param pageNum 当前页
	 * @param pageSize	页面大小
	 * @return
	 */
	@RequestMapping("/getPageInfo")
	@ResponseBody
	public Result getData( Goods goods, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "5") Integer pageSize) {
		return goodsService.queryGoods(goods, pageNum, pageSize);
	}

	/**
	 * 
	 * <p>
	 * 	更新商品或新增商品，商品id为空，就新增，不为空就修改
	 * </p>
	 * @author zdl
	 * @Date 2019年12月24日
	 * @param goods 商品的信息
	 * @return 新增或修改后的商品
	 */
	@RequestMapping("/updateGoods")
	@ResponseBody
	public Result updateGoods(Goods goods) {
		if (goods.getGoodsId() != null && !"".equals(goods.getGoodsId())) {
			return goodsService.updateGoods(goods);
		} else {
			return goodsService.insertGoods(goods);
		}
	}
	/**
	 * 
	 * <p>
	 * 	删除商品
	 * </p>
	 * @author zdl
	 * @Date 2019年12月24日
	 * @param goods 主要包含商品的Id
	 * @return
	 */
	@RequestMapping("/deleteGoods")
	@ResponseBody
	public Result deleteGoods(Goods goods) {
		return goodsService.deleteGoodsByGoodsId(goods.getGoodsId());
	}
	/**
	 * 
	 * <p>
	 * 	批量删除商品
	 * </p>
	 * @author zdl
	 * @Date 2019年12月24日
	 * @param id_arr 前端传回来的id数组
	 * @return
	 */
	@RequestMapping("/deleteMany")
	@ResponseBody
	public Result deleteManyGoods(String[] id_arr) {
		return goodsService.deleteManyGoods(id_arr);
	}
}
