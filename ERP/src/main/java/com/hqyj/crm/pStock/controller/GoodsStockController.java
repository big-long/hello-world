package com.hqyj.crm.pStock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hqyj.crm.common.entity.Result;
import com.hqyj.crm.pStock.entity.GStock;
import com.hqyj.crm.pStock.entity.Pickout;
import com.hqyj.crm.pStock.service.GoodsStockService;
import com.hqyj.crm.production.entity.PickList;
import com.hqyj.crm.production.service.PickListService;
import com.hqyj.crm.purchase.entity.Purchase;
import com.hqyj.crm.purchase.service.PurchaseService;

@Controller
@RequestMapping("/goodsStock")
public class GoodsStockController {
	@Autowired
	private GoodsStockService goodsStockService;
	@Autowired
	private PurchaseService purchaseService;
	@Autowired 
	private PickListService pickListService;
	/**
	 * 
	 * <p>
	 * 	商品库存管理之页面
	 * </p>
	 * @author zdl
	 * @Date 2020年1月15日
	 * @return
	 */
	@RequestMapping("/list")
	public String toPage() {
		return "pStock/goodsStockList";
	}
	/**
	 * 
	 * <p>
	 * 商品库存管理之列表展示
	 * </p>
	 * @author zdl
	 * @Date 2020年1月15日
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/getPageInfo")
	@ResponseBody
	public Result  getPageInfo(@RequestParam(defaultValue="1") Integer pageNum,@RequestParam(defaultValue="8") Integer pageSize) {
		return goodsStockService.queryPageInfo(pageNum,pageSize);
	}
	/**
	 * 
	 * <p>
	 * 	商品入库管理之入库单页面
	 * </p>
	 * @author zdl
	 * @Date 2020年1月15日
	 * @return
	 */
	@RequestMapping("/gStockList")
	public String toStockPage(Model model) {
		List<Purchase> purchases=purchaseService.queryAllPurchase();
		model.addAttribute("purchases", purchases);
		return "pStock/gStockList";
	}
	/**
	 * 
	 * <p>
	 * 	商品入库管理之查询所有的入库单信息
	 * </p>
	 * @author zdl
	 * @Date 2020年1月15日
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/getGStockPageInfo")
	@ResponseBody
	public Result  getGStock(@RequestParam(defaultValue="1") Integer pageNum,@RequestParam(defaultValue="8") Integer pageSize) {
		return goodsStockService.queryGStock(pageNum,pageSize);
	}
	/**
	 * 
	 * <p>
	 * 商品入库管理之新增或修改入库单信息
	 * </p>
	 * @author zdl
	 * @Date 2020年1月15日
	 * @param gStock
	 * @return
	 */
	@RequestMapping("/updateGStock")
	@ResponseBody
	public Result update(GStock gStock) {
		return goodsStockService.updateGStock(gStock);
	}
	/**
	 * 
	 * <p>
	 * 	商品入库管理之生产入库单编号
	 * </p>
	 * @author zdl
	 * @Date 2020年1月15日
	 * @return
	 */
	@RequestMapping("/getGStockId")
	@ResponseBody
	public Result getGStockId() {
		String gStockId="GS"+System.currentTimeMillis();
		return new Result(200, "success",gStockId);
	}
	/**
	 * 
	 * <p>
	 * 	商品入库管理之根据采购单编号查询采购单信息
	 * </p>
	 * @author zdl
	 * @Date 2020年1月18日
	 * @param purchaseId
	 * @return
	 */
	@RequestMapping("/getPurchaseInfo")
	@ResponseBody
	public Result getPurchaseInfo(String purchaseId) {
		return goodsStockService.queryPurchaseInfo(purchaseId);
	}
	/**
	 * 
	 * <p>
	 * 	商品入库管理之根据商品入库编号查询入库商品的信息
	 * </p>
	 * @author zdl
	 * @Date 2020年1月18日
	 * @param gStockId
	 * @return
	 */
	@RequestMapping("/getStockInfo")
	@ResponseBody
	public Result getStockInfo(String gStockId) {
		return goodsStockService.queryGStockInfo(gStockId);
	}
	/**
	 * 
	 * <p>
	 * 	商品入库管理之根据商品入库编号删除对应入库单
	 * </p>
	 * @author zdl
	 * @Date 2020年1月18日
	 * @param gStockId
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Result deleteStock(String gStockId) {
		return goodsStockService.deleteGStock(gStockId);
	}
	@RequestMapping("/pickoutList")
	public String toPickoutPage(Model model) {
		List<PickList> pickLists=pickListService.queryAllPickouts();
		model.addAttribute("pickLists", pickLists);
		return "pStock/pickoutList";
	}
	/**
	 * 
	 * <p>
	 * 	领料出库管理之查询列表信息
	 * </p>
	 * @author zdl
	 * @Date 2020年1月18日
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/getPickoutPageInfo")
	@ResponseBody
	public Result  getPickout(@RequestParam(defaultValue="1") Integer pageNum,@RequestParam(defaultValue="8") Integer pageSize) {
		return goodsStockService.queryPickoutPageInfo(pageNum,pageSize);
	}
	@RequestMapping("/generatePickoutId")
	@ResponseBody
	public Result generatePickoutId() {
		String pickoutId="PT"+System.currentTimeMillis();
		return  new Result(200, "success",pickoutId);
	}
	@RequestMapping("/updatePickout")
	@ResponseBody
	public Result updatePickout(Pickout pickout) {
		return  goodsStockService.updatePickout(pickout);
	}
	@RequestMapping("/getPickListInfo")
	@ResponseBody
	public Result getPickListInfo(String plId) {
		return  goodsStockService.queryPickoutInfoByPlId(plId);
	}
	@RequestMapping("/getPickoutInfo")
	@ResponseBody
	public Result getPickoutInfo(String pickoutId) {
		return  goodsStockService.queryPickoutInfoByPickoutId(pickoutId);
	}
	@RequestMapping("/deletePickout")
	@ResponseBody
	public Result deletePickout(String pickoutId) {
		return goodsStockService.deletePickout(pickoutId);
	}
	
}
