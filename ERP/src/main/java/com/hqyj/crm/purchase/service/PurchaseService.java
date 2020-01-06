package com.hqyj.crm.purchase.service;

import java.util.Map;

import com.hqyj.crm.common.entity.Result;
import com.hqyj.crm.purchase.entity.Purchase;

public interface PurchaseService {

	Result queryPageInfo(Integer pageNum, Integer pageSize);

	Result queryPurchaseInfo(String purchaseId);

	Result updatePurchase(Purchase purchase);

	Result deletePurchase(String purchaseId);

	Result deleteManyPurchase(String[] id_arr);

	Map<String, Object> queryPurchaseData();

	Result queryPageInfoByKeyWord(String keyWord, Integer pageNum, Integer pageSize);

}
