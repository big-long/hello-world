
package com.hqyj.crm.pSell.service;

import java.util.List;

import com.hqyj.crm.common.entity.Result;
import com.hqyj.crm.pSell.entity.Sell;

public interface SellService {

	Result queryAllSell(Integer pageNum, Integer pageSize);

	Result deleteSell(String sellId);
	
	Result updateSell(Sell sell);

	Result sellDeleBatch(String[] sellArray);

	Result querySellInfoBySellId(String sellId);

	List<Sell> queryAllSell();

}
