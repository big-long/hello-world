package com.hqyj.crm.pStock.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.crm.common.entity.Result;
import com.hqyj.crm.pStock.dao.StockMapper;
import com.hqyj.crm.pStock.entity.Stock;
import com.hqyj.crm.pStock.service.StockService;

@Service
public class StockServiceImpl implements StockService {
	@Autowired
	private StockMapper stockMapper;

	@Override
	public Result queryPageInfo(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Stock> stocks=stockMapper.selectAllStock();
		return new Result(200, "success", new PageInfo<Stock>(stocks));
	}

	@Override
	public Result updateStock(Stock stock) {
		String inbillId = stock.getInbillId();
		if(inbillId==null||"".equals(inbillId)) {
			return new Result(500, "入库单编号为空");
		}
		Stock stock_db = stockMapper.selectByPrimaryKey(inbillId);
		if(stock_db==null) {
			int n=stockMapper.insertSelective(stock);
			if(n>0) {
				return new Result(200, "success");
			}
		}else {
			int n=stockMapper.updateByPrimaryKeySelective(stock);
			if(n>0) {
				return new Result(200, "success");
			}
		}
		return new Result(500, "系统异常");	}
}
