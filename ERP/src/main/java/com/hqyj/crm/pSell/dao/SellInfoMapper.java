package com.hqyj.crm.pSell.dao;

import java.util.List;

import com.hqyj.crm.pSell.entity.SellInfo;

public interface SellInfoMapper {
    int deleteByPrimaryKey(String sellId);

    int insert(SellInfo record);

    int insertSelective(SellInfo record);

    SellInfo selectByPrimaryKey(String sellId);

    int updateByPrimaryKeySelective(SellInfo record);

    int updateByPrimaryKey(SellInfo record);

	/**
	 * @return
	 */
	List<SellInfo> queryAllSellInfo();

	/**
	 * @param sellId
	 * @return
	 */
//	SellInfo selectBySellId(String sellId);
}