/**
	 * @Title: 
	 * @Description: TODO 
	 * @param @param 
	 * @param @param 
	 * @param @return 
	 * @return 
	 * @throws
	 */
package com.hqyj.crm.pSell.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.crm.common.entity.Result;
import com.hqyj.crm.pSell.dao.SellMapper;
import com.hqyj.crm.pSell.entity.Sell;
import com.hqyj.crm.pSell.service.SellService;

@Service
public class SellServiceImpl implements SellService {
	@Autowired
	private SellMapper sellMapper;

	@Override
	public Result queryAllSell(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Sell> sells = sellMapper.queryAllSell();
		return new Result(200, "success", new PageInfo<Sell>(sells));
	}

	public Result deleteSell(String sellId) {
		int n = sellMapper.deleteSellByPrimaryKey(sellId);
		int m = sellMapper.deleteSellInfoByPrimaryKey(sellId);
		if (m == n && n > 0) {
			return new Result(200, "success");
		}
		return new Result(500, "系统异常");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hqyj.crm.pSell.service.SellService#updateSell(com.hqyj.crm.pSell.entity.
	 * Sell)
	 */
	@Override
	public Result updateSell(Sell sell) {
		String sellId = sell.getSellId();
		if (sellId == null || "".equals(sellId)) {
			return new Result(500, "销售编号为空");
		}
		Sell sell_db = sellMapper.selectByPrimaryKey(sellId);
		if (sell_db == null) {
			int n = sellMapper.insertSellInfoSelective(sell);
			int m = sellMapper.insertSellSelective(sell);
			if (m == n && n > 0) {
				return new Result(200, "success");
			}
		} else {
			int n = sellMapper.updateSellInfoSelective(sell);
			int m = sellMapper.updateSellSelective(sell);
			if (m == n && n > 0) {
				return new Result(200, "success");
			}

		}
		return new Result(500, "系统异常");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hqyj.crm.pSell.service.SellService#userDeleBatch(java.lang.String[])
	 */
	@Override
	public Result sellDeleBatch(String[] sellArray) {
		if(sellArray==null||sellArray.length==0) {
			return new Result(500, "请至少选择一条数据");
		}
		
		int n= sellMapper.deleSellBatch(sellArray);
		int m= sellMapper.deleSellInfoBatch(sellArray);
		if (m == n && n > 0) {
			return new Result(200, "success");
		}
		return new Result(500, "系统异常");
	}

	@Override
	public Result querySellInfoBySellId(String sellId) {
		Sell sell = sellMapper.selectByPrimaryKey(sellId);
		return new Result(200, "success",sell);
	}

	@Override
	public List<Sell> queryAllSell() {
		// TODO Auto-generated method stub
		return sellMapper.queryAllSell();
	}
}
