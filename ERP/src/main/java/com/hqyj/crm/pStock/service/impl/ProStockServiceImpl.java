package com.hqyj.crm.pStock.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.crm.common.entity.Result;
import com.hqyj.crm.pStock.dao.ProStockMapper;
import com.hqyj.crm.pStock.entity.ProStock;
import com.hqyj.crm.pStock.service.ProStockService;

@Service
public class ProStockServiceImpl implements ProStockService {
	@Autowired
	private ProStockMapper proStockMapper;

	@Override
	public Result queryPageInfo(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<ProStock> proStocks = proStockMapper.selectAll();
		return new Result(200, "success", new PageInfo<ProStock>(proStocks));
	}
}
