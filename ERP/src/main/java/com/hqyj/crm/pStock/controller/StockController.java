package com.hqyj.crm.pStock.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.crm.pStock.entity.Stock;
import com.hqyj.crm.pStock.entity.StockInfo;
import com.hqyj.crm.pStock.service.impl.StockServiceImpl;

@Controller
@RequestMapping("/stock")
public class StockController {
	@Autowired
	private StockServiceImpl stockServiceImpl;
	/**
	 * 产品入库表
	 */
	@RequestMapping("/stockList")
	public String findStockList(String name,@RequestParam(required=true,defaultValue="1")Integer page,HttpServletRequest request){
		List<Stock> stlist=stockServiceImpl.findStockList();
		request.getSession().setAttribute("stlist", stlist);
		request.getSession().setAttribute("name", name);
		PageHelper.startPage(page, 5);
		List<Stock> stpage=new ArrayList<Stock>();
		if(name!=null){
			name="%"+name+"%";
			stpage=stockServiceImpl.findStockMohu(name);
		}else{
			stpage=stockServiceImpl.findStockList();
		}
		request.getSession().setAttribute("stpage", stpage);
		
		PageInfo<Stock> p=new PageInfo<Stock>(stpage);
		request.getSession().setAttribute("page", p);
		return "customer/stockList";
	}
	/**
	 * 产品入库表模糊查询
	 * 产品名或者生产商模糊查询
	 */
	@RequestMapping("/stockList2")
	@ResponseBody
	public List<Stock> findStock(String name){
		name="%"+name+"%";
		List<Stock> sts=stockServiceImpl.findStockMohu(name);
		return sts;
	}
	/**
	 * 根据订单号查询订单
	 */
	@RequestMapping("/findStockInfoByOrderId")
	@ResponseBody
	public List<StockInfo> findStockInfoByOrderId(String orderId){
		List<StockInfo> sis=stockServiceImpl.findStockInfoByOrderId(orderId);
		return sis;
	}
	
	/**
	 * 根据inbillId查询对应的产品入库数据
	 * 主要实现库存数目更改
	 */
	@RequestMapping("/findStockById")
	@ResponseBody
	public List<Stock> findStockById(String inbillId){
		List<Stock> st=stockServiceImpl.findStockById(inbillId);
		return st;
	}
	
	/**
	 * 产品入库订单表1
	 * 根据产品编号查看对应所有订单
	 */
	@RequestMapping("/stockInfoList1")
	public String findStockInfoById(String inbillId,
			@RequestParam(required=true,defaultValue="1")Integer page,
			HttpServletRequest request){
		List<Stock> stlist=stockServiceImpl.findStockList();
		request.getSession().setAttribute("stlist", stlist);
		request.getSession().setAttribute("num", 1);
		PageHelper.startPage(page, 5);
		List<StockInfo> sispage=stockServiceImpl.findStockListInfoByinbillId(inbillId);
		request.getSession().setAttribute("inbillId", inbillId);
		request.getSession().setAttribute("sispage", sispage);
		PageInfo<StockInfo> p2=new PageInfo<StockInfo>(sispage);
		request.getSession().setAttribute("page", p2);
		return "customer/stockInfoList";
     }
	/**
	 * 产品入库订单表2
	 */
	@RequestMapping("/stockInfoList2")
	public String findStockInfoByOrderId(String orderId,
			@RequestParam(required=true,defaultValue="1")Integer page,
			HttpServletRequest request){
		List<Stock> stlist=stockServiceImpl.findStockList();
		request.getSession().setAttribute("stlist", stlist);
		request.getSession().setAttribute("num", 2);
		PageHelper.startPage(page, 5);
		List<StockInfo>  sispage=stockServiceImpl.findStockInfoByOrderId(orderId); 
		request.getSession().setAttribute("orderId", orderId);
		request.getSession().setAttribute("sispage", sispage);
		PageInfo<StockInfo> p2=new PageInfo<StockInfo>(sispage);
		request.getSession().setAttribute("page", p2);
		return "customer/stockInfoList";
     }
	/**
	 * 产品入库订单表2.0升级版有bug废弃
	 */
	/**@RequestMapping("/stockInfoList3")
	public String findStockInfoById(Stock stock,
			@RequestParam(required=true,defaultValue="1")Integer page,
			HttpServletRequest request){
		List<Stock> stlist=stockServiceImpl.findStockList();
		request.getSession().setAttribute("stlist", stlist);
		request.getSession().setAttribute("stock", stock);
		PageHelper.startPage(page, 5);
		List<StockInfo> sispage=stockServiceImpl.findStockListInfoByStock(stock);
		request.getSession().setAttribute("sispage", sispage);
		PageInfo<StockInfo> p2=new PageInfo<StockInfo>(sispage);
		request.getSession().setAttribute("page", p2);
		return "customer/stockInfoList2";
     }*/
	
	
	/**
	 * 产品入库表增
	 */
	@RequestMapping(value="insertStock",method=RequestMethod.POST)
	@ResponseBody
	public int insertStock(Stock stock){
		int num=stockServiceImpl.insertStock(stock);
		return num;
	}
	
	/**
	 * 产品入库订单表增
	 */
	@RequestMapping("/insertStockInfo")
	@ResponseBody
	public int insertStockInfo(StockInfo stockinfo){
		int num=stockServiceImpl.insertStockInfo(stockinfo);
		/**
		 * 增加订单时同时增加库存数量
		 */
		if(num>0){
			String inbillId=stockinfo.getInbillId();
			int num1=stockinfo.getInbillNumber();
			int num2=0;
			List<Stock> sto=stockServiceImpl.findStockById(inbillId);
			for (Stock stock : sto) {
				num2=stock.getRepertoryNumber();	
			}
			int repertoryNumber=num1+num2;
			Stock so=new Stock();
			so.setRepertoryNumber(repertoryNumber);
			so.setInbillId(inbillId);
			stockServiceImpl.updateStock(so);
		}
		return num;
	}
	
	/**
	 * 产品入库表删
	 */
	@RequestMapping("/deleteStockById")
	@ResponseBody
	public int deleteStockById(String inbillId){
		 int num = stockServiceImpl.deleteStockById(inbillId);	
		return num;	
	}
	/**
	 * 产品入库表批量删除
	 */
	@RequestMapping("/deleteSome")
	@ResponseBody
	public int deleteSome(@RequestParam(value="inbillId[]")List inbillId){
		try {
			for (Object id : inbillId) {
				if(id!=""){
					stockServiceImpl.deleteStockById(id.toString());
				}
			}
			return 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
	}
	/**
	 * 产品入库订单表删
	 */
	@RequestMapping("/deleteStockInfoById")
	@ResponseBody
	public int deleteStockInfoById(String orderId){
		return  stockServiceImpl.deleteStockInfoById(orderId);
		
	}
	/**
	 * 产品入库订单表批量删除
	 */
	@RequestMapping("/deleteSomeStockInfo")
	@ResponseBody
	public int deleteSomeStockInfo(@RequestParam(value="orderId[]")List orderId){
		try {
			for (Object id : orderId) {
				if(id!=""){
					stockServiceImpl.deleteStockInfoById(id.toString());
				}
			}
			return 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
	}
	/**
	 * 产品入库表改
	 */
	@RequestMapping(value="updateStock",method=RequestMethod.POST)
	@ResponseBody
	public int updateStock(Stock stock){
		int num=stockServiceImpl.updateStock(stock);
		return num;
	}
	/**
	 * 产品入库订单表改
	 */
	@RequestMapping("/updateStockInfo")
	@ResponseBody
	public int updateStockInfo(StockInfo stockInfo){
		int num=stockServiceImpl.updateStockInfo(stockInfo);
		return num;
	}
}
