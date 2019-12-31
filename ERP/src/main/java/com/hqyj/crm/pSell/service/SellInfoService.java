/**
	 * @Title: 
	 * @Description: TODO 
	 * @param @param 
	 * @param @param 
	 * @param @return 
	 * @return 
	 * @throws
	 */
package com.hqyj.crm.pSell.service;

import java.util.List;

import com.hqyj.crm.pSell.entity.SellInfo;

/**
 * @author ASUS
 *
 */
public interface SellInfoService {

	/**
	 * @return
	 */
	List<SellInfo> queryAllSellInfo();

	/**
	 * @param sellInfo
	 * @return
	 */
	int addSellInfo(SellInfo sellInfo);

	/**
	 * @param sellId
	 * @return
	 */
	int deleteSell(String sellId);

	/**
	 * @param sellId
	 * @return
	 */
	SellInfo querySellInfo(String sellId);

	

}
