package com.hqyj.crm.pStock.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hqyj.crm.pStock.dao.StockMapper;
import com.hqyj.crm.pStock.entity.Stock;
import com.hqyj.crm.pStock.entity.StockInfo;
import com.hqyj.crm.pStock.service.StockService;
@Service
public class StockServiceImpl implements StockService {
	@Autowired
	private StockMapper stockMapper;




	@Override
	public List<StockInfo> findStockListInfoByinbillId(String id){
		List<StockInfo> sis= stockMapper.findStockListInfoByinbillId(id);
		return sis;
	}

	@Override
	public List<Stock> findStockList() {
		List<Stock> sts=stockMapper.findStockList();
		return sts;
	}
	
	@Override
	public int insertStockInfo(StockInfo stockinfo) {
		//stockinfo.setInbillDate(new Date());
		stockinfo.setInbillDate(new Date());
		int num=stockMapper.insertStockInfo(stockinfo);
		return num;
	}
	
	@Override
	public int insertStock(Stock stock) {
		int num=stockMapper.insertStock(stock);
		return num;
	}
	@Override
	public int deleteStockById(String inbillId) {
		int num=stockMapper.deleteStockById(inbillId);
		return num;
	}
	@Override
	public int deleteStockInfoById(String orderId) {
		int num=stockMapper.deleteStockInfoById(orderId);
		return num;
	}
	@Override
	public int updateStock(Stock stock) {
		int num=stockMapper.updateStock(stock);
		return num;
	}
	@Override
	public int updateStockInfo(StockInfo stockInfo) {
		int num=stockMapper.updateStockInfo(stockInfo);
		return num;
	}
	@Override
	public List<Stock> findStockById(String inbillId) {
		List<Stock> st=stockMapper.findStockById(inbillId);
		return st;
	}
	@Override
	public List<Stock> findStockMohu(String name) {
		List<Stock> st=stockMapper.findStockMohu(name);
		return st;
	}
	@Override
	public List<StockInfo> findStockListInfoByStock(Stock stock) {
		List<StockInfo> si=stockMapper.findStockListInfoByStock(stock);
		return si;
	}
	@Override
	public List<StockInfo> findStockInfoByOrderId(String orderId) {
		List<StockInfo> sis=stockMapper.findStockInfoByOrderId(orderId);
		return sis;
	}
	

}
