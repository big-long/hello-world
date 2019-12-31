package com.hqyj.crm.pStock.dao;

import java.util.List;

import com.hqyj.crm.pStock.entity.Stock;
import com.hqyj.crm.pStock.entity.StockInfo;

public interface StockMapper {
    
   
	List<Stock> findStockList();
	List<StockInfo> findStockListInfoByinbillId(String id);
	int insertStockInfo(StockInfo stockinfo);
	int insertStock(Stock stock);
	int deleteStockById(String inbillId);
	int deleteStockInfoById(String orderId);
	int updateStock(Stock stock);
	int updateStockInfo(StockInfo stockInfo);
	List<Stock> findStockById(String inbillId);
	List<Stock> findStockMohu(String name);
	List<StockInfo> findStockListInfoByStock(Stock stock);
	List<StockInfo> findStockInfoByOrderId(String orderId);
}