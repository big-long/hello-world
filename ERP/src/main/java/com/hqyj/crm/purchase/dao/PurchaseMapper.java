package com.hqyj.crm.purchase.dao;

import java.util.List;

import com.hqyj.crm.purchase.entity.Purchase;

public interface PurchaseMapper {
	public List<Purchase> selectAllPurchases();
	
	public Purchase selectByPrimaryKey(String purchaseId);
	
	public int deletePurchaseInfoByPrimaryKey(String purchaseId);
	
	public int insertPurchaseInfoSelective(Purchase purchase);
	
	public int  updatePurchaseInfoByPrimaryKeySelective(Purchase purchase);
	
	public int deletePurchaseByPrimaryKey(String purchaseId);
	
	public int insertPurchaseSelective(Purchase purchase);
	
	public int  updatePurchaseByPrimaryKeySelective(Purchase purchase);

	public int deleteManyPurchase(String[] id_arr);
	
	public int deleteManyPurchaseInfo(String[] id_arr);

	public List<Purchase> selectPageInfoByKeyWord(String keyWord);
	
	
}