package com.hqyj.crm.pStock.dao;

import java.util.List;

import com.hqyj.crm.pStock.entity.Stock;

public interface StockMapper {
    int deleteByPrimaryKey(String inbillId);

    int insertSelective(Stock record);

    Stock selectByPrimaryKey(String inbillId);

    int updateByPrimaryKeySelective(Stock record);

	List<Stock> selectAllStock();

}