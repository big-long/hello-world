package com.hqyj.crm.pStock.dao;

import java.util.List;

import com.hqyj.crm.pStock.entity.GStock;

public interface GStockMapper {
	List<GStock> selectAll();

	GStock selectGStock(String gStockId);

	int insertGStockByGStock(GStock gStock);

	int updateGStockByGStock(GStock gStock);

	GStock selectPurchaseInfo(String purchaseId);

	GStock selectGStockInfo(String gStockId);

	int deleteGStockByPrimaryKey(String gStockId);
}
