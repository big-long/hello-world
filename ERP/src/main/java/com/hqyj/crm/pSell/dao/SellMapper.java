package com.hqyj.crm.pSell.dao;

import java.util.List;

import com.hqyj.crm.pSell.entity.Sell;

public interface SellMapper {
    int deleteByPrimaryKey(String sellId);

    int insert(Sell record);

    int insertSelective(Sell record);

    Sell selectByPrimaryKey(String sellId);

    int updateByPrimaryKeySelective(Sell record);

    int updateByPrimaryKey(Sell record);

	/**
	 * @return
	 */
	List<Sell> queryAllSell();

	/**
	 * @param idArray
	 * @return
	 */
	int deleSellBatch(int[] idArray);
}