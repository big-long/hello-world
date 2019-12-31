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

import com.hqyj.crm.pSell.entity.Sell;

/**
 * @author ASUS
 *
 */
public interface SellService {

	/**
	 * @return
	 */
	List<Sell> queryAllSell();

	/**
	 * @param sell
	 * @return
	 */
	int addSell(Sell sell);

	/**
	 * @param sellId
	 * @return
	 */
	int deleteSell(String sellId);

	/**
	 * @param sell
	 * @return
	 */
	int updateSell(Sell sell);

	/**
	 * @param sellArray
	 * @return
	 */
	int sellDeleBatch(String[] sellArray);

}
