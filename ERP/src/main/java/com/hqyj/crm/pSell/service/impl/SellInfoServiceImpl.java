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

import com.hqyj.crm.pSell.dao.SellInfoMapper;
import com.hqyj.crm.pSell.entity.SellInfo;
import com.hqyj.crm.pSell.service.SellInfoService;

/**
 * @author ASUS
 *
 */
@Service
public class SellInfoServiceImpl implements SellInfoService {
	@Autowired
	private SellInfoMapper sellInfoMapper;

	/* (non-Javadoc)
	 * @see com.hqyj.crm.pSell.service.SellInfoService#queryAllSellInfo()
	 */
	@Override
	public List<SellInfo> queryAllSellInfo() {
		return sellInfoMapper.queryAllSellInfo() ;
	}

	/* (non-Javadoc)
	 * @see com.hqyj.crm.pSell.service.SellInfoService#addSellInfo(com.hqyj.crm.pSell.entity.SellInfo)
	 */
	@Override
	public int addSellInfo(SellInfo sellInfo) {
		return sellInfoMapper.insertSelective(sellInfo);
	}

	/* (non-Javadoc)
	 * @see com.hqyj.crm.pSell.service.SellInfoService#deleteSell(java.lang.String)
	 */
	@Override
	public int deleteSell(String sellId) {
		return sellInfoMapper.deleteByPrimaryKey(sellId);
	}

	/* (non-Javadoc)
	 * @see com.hqyj.crm.pSell.service.SellInfoService#querySellInfo(java.lang.String)
	 */
	@Override
	public SellInfo querySellInfo(String sellId) {
		SellInfo sell = sellInfoMapper.selectByPrimaryKey(sellId);
		return sell;
	}

	
	
	
	

}
