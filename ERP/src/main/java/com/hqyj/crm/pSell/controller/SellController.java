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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.crm.pSell.entity.Sell;
import com.hqyj.crm.pSell.entity.SellInfo;
import com.hqyj.crm.pSell.service.SellInfoService;
import com.hqyj.crm.pSell.service.SellService;
import com.hqyj.crm.system.entity.User;



/**
 * @author 赵丹钊
 *
 */
@Controller
@RequestMapping("/PSell")
public class SellController {
	@Autowired
	private SellService sellService;
	@Autowired
	private SellInfoService sellInfoService;
	
	
	@RequestMapping("/sellList")
	public String queryAllSell(HttpServletRequest request, HttpServletResponse response,@RequestParam(required=true,defaultValue="1")Integer page) {
		PageHelper.startPage(page,5);
		List<Sell> sell_dbList = sellService.queryAllSell();
		List<SellInfo> sellInfo_dbList = sellInfoService.queryAllSellInfo();
		request.setAttribute("sell_dbList", sell_dbList);
		request.setAttribute("sellInfo_dbList", sellInfo_dbList);
		PageInfo<Sell> sellPage = new PageInfo<Sell>(sell_dbList);
		request.getSession().setAttribute("sellPage", sellPage);
		return "system/sell/sellList";
	}
	
	@RequestMapping("/sellAdd")
	@ResponseBody
	public List<Sell> addSell(Sell sell,SellInfo sellInfo,HttpServletRequest request, HttpServletResponse response){
		int n = sellService.addSell(sell);
//		request.setAttribute("sell_dbList", sell_dbList);
		int m  =sellInfoService.addSellInfo(sellInfo);
		return sellService.queryAllSell();
	}
	
	@RequestMapping("/sellDelete")
	public String deleteSell(String sellId,HttpServletRequest request, HttpServletResponse response){
		int m = sellService.deleteSell(sellId);
		int n = sellInfoService.deleteSell(sellId);
		List<Sell> sell_dbList = sellService.queryAllSell();
		request.setAttribute("sell_dbList", sell_dbList);
		return "system/sell/sellList";
	}
	
	@RequestMapping("/sellUpdate")
	@ResponseBody
	public List<Sell> updateSell(Sell sell) {
		int m = sellService.updateSell(sell);
		return sellService.queryAllSell();
	}
	
	@RequestMapping("/sellInfoList")
	@ResponseBody
	public List<SellInfo> querySellInfo() {
		return sellInfoService.queryAllSellInfo();
	}
	
	@RequestMapping("/sellInfoDetail")
	@ResponseBody
	public SellInfo querySellInfo(Sell sell) {
		SellInfo sellInfo = sellInfoService.querySellInfo(sell.getSellId());
//		request.setAttribute("sellInfo", sellInfo);
		return sellInfo;
	}
	
	@PostMapping("/sellDeleBatch")
	@ResponseBody
	public int deleSellBatch(String[] sellArray) {
		//System.err.println(userArry[0]+"++++++++++++++++++++++");
		//userArry[0]="111";
		return sellService.sellDeleBatch(sellArray);
	}
	
	@ResponseBody
	public int getPC(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)  {
		String value = request.getParameter("pc");
		if (value == null || value.trim().isEmpty()) {
			return 1;
		}
		return Integer.parseInt(value);
	}
}
