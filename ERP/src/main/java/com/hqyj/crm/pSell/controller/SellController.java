/**
	 * @Title: 
	 * @Description: TODO 
	 * @param @param 
	 * @param @param 
	 * @param @return 
	 * @return 
	 * @throws
	 */
package com.hqyj.crm.pSell.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hqyj.crm.common.entity.Result;
import com.hqyj.crm.order.entity.Order;
import com.hqyj.crm.order.service.OrderService;
import com.hqyj.crm.pSell.entity.Sell;
import com.hqyj.crm.pSell.service.SellService;



/**
 * @author 赵丹钊
 *
 */
@Controller
@RequestMapping("/pSell")
public class SellController {
	@Autowired
	private SellService sellService;
	@Autowired
	private OrderService orderService;
	
	
	@RequestMapping("/list")
	public String toPage(Model model) {
		List<Order> orders = orderService.queryAllOrders();
		model.addAttribute("orders", orders);
		return "sell/sellList";
	}
	@RequestMapping("/getPageInfo")
	@ResponseBody
	public Result queryAllSell(@RequestParam(required=true,defaultValue="1")Integer pageNum,@RequestParam(required=true,defaultValue="8")Integer pageSize) {
		return sellService.queryAllSell(pageNum, pageSize);
	}
	
	
	@RequestMapping("/getSellId")
	@ResponseBody
	public Result getSellId(){
		String sellId="S"+System.currentTimeMillis();
		return new Result(200,"success",sellId);
	}
	@RequestMapping("/getOrderInfo")
	@ResponseBody
	public Result getOrderInfo(String orderId){
		return orderService.queryOrderByOrderId(orderId);
	}
	@RequestMapping("/getSellInfo")
	@ResponseBody
	public Result getSellInfo(String sellId){
		return sellService.querySellInfoBySellId(sellId);
	}
	@RequestMapping("/sellDelete")
	@ResponseBody
	public Result deleteSell(String sellId){
		return sellService.deleteSell(sellId);
	}
	
	@PostMapping("/sellUpdate")
	@ResponseBody
	public Result updateSell(Sell sell) {
		return sellService.updateSell(sell);
	}
	
	@PostMapping("/sellDeleBatch")
	@ResponseBody
	public Result deleSellBatch(String[] sellArray) {
		
		return sellService.sellDeleBatch(sellArray);
	}
	
}
