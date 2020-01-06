package com.hqyj.crm.pSell.dao;

import java.util.List;

import com.hqyj.crm.pSell.entity.Sell;

public interface SellMapper {
    int deleteSellByPrimaryKey(String sellId);
    
    int deleteSellInfoByPrimaryKey(String sellId);

    int insertSellInfoSelective(Sell sell);

    int insertSellSelective(Sell sell);

    Sell selectByPrimaryKey(String sellId);

    int updateSellSelective(Sell sell);

    int updateSellInfoSelective(Sell sell);

	List<Sell> queryAllSell();

	int deleSellBatch(String[] idArray);
	
	int deleSellInfoBatch(String[] idArray);
}