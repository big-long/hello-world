package com.hqyj.crm.pStock.service;

import com.hqyj.crm.common.entity.Result;
import com.hqyj.crm.pStock.entity.Stock;

public interface StockService {

	Result queryPageInfo(Integer pageNum, Integer pageSize);

	Result updateStock(Stock stock);

}
