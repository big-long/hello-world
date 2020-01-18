package com.hqyj.crm.pStock.service;

import com.hqyj.crm.common.entity.Result;
import com.hqyj.crm.pStock.entity.GStock;
import com.hqyj.crm.pStock.entity.Pickout;

public interface GoodsStockService {

	Result queryPageInfo(Integer pageNum, Integer pageSize);

	Result queryGStock(Integer pageNum, Integer pageSize);

	Result updateGStock(GStock gStock);

	Result queryPurchaseInfo(String purchaseId);

	Result queryGStockInfo(String gStockId);

	Result deleteGStock(String gStockId);

	Result queryPickoutPageInfo(Integer pageNum, Integer pageSize);

	Result updatePickout(Pickout pickout);

	Result queryPickoutInfoByPlId(String plId);

	Result queryPickoutInfoByPickoutId(String pickoutId);

	Result deletePickout(String pickoutId);

}
