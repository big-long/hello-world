package com.hqyj.crm.purchase.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.crm.baseinfo.dao.GoodsMapper;
import com.hqyj.crm.baseinfo.dao.ProviderMapper;
import com.hqyj.crm.baseinfo.entity.Goods;
import com.hqyj.crm.baseinfo.entity.Provider;
import com.hqyj.crm.common.entity.Result;
import com.hqyj.crm.order.dao.OrderMapper;
import com.hqyj.crm.purchase.dao.PurchaseMapper;
import com.hqyj.crm.purchase.entity.Purchase;
import com.hqyj.crm.purchase.service.PurchaseService;
@Service
public class PurchaseServiceImpl implements PurchaseService {
	@Autowired
	private PurchaseMapper purchaseMapper;
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private ProviderMapper providerMapper;
	@Autowired
	private OrderMapper orderMapper;

	@Override
	public Result queryPageInfo(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Purchase> purchases = purchaseMapper.selectAllPurchases();
		return new Result(200, "success",new PageInfo<Purchase>(purchases));
	}

	@Override
	public Result queryPurchaseInfo(String purchaseId) {
		Purchase purchase = purchaseMapper.selectByPrimaryKey(purchaseId);
		return new Result(200, "success",purchase);
	}

	@Override
	public Result updatePurchase(Purchase purchase) {
		String purchaseId = purchase.getPurchaseId();
		if(purchaseId==null||"".equals(purchaseId)) {
			return new Result(500, "采购编号为空");
		}
		Purchase purchase_db = purchaseMapper.selectByPrimaryKey(purchaseId);
		if(purchase_db==null) {
			int n=purchaseMapper.insertPurchaseSelective(purchase);
			int m=purchaseMapper.insertPurchaseInfoSelective(purchase);
			if(m==n&&n>0) {
				return new Result(200,"success");
			}
		}else {
			int n=purchaseMapper.updatePurchaseByPrimaryKeySelective(purchase);
			int m=purchaseMapper.updatePurchaseInfoByPrimaryKeySelective(purchase);
			if(m==n&&n>0) {
				return new Result(200,"success");
			}
		}
		return new Result(500,"系统异常");
	}

	@Override
	public Result deletePurchase(String purchaseId) {
		if(purchaseId==null||"".equals(purchaseId)) {
			return new Result(500, "采购编号为空");
		}
		int n=purchaseMapper.deletePurchaseByPrimaryKey(purchaseId);
		int m=purchaseMapper.deletePurchaseInfoByPrimaryKey(purchaseId);
		if(m==n&&n>0) {
			return new Result(200,"success");
		}
		return new Result (500,"系统异常");
	}

	@Override
	public Result deleteManyPurchase(String[] id_arr) {
		if(id_arr==null||id_arr.length==0) {
			return new Result(500,"请至少选择一条数据");
		}
		int n=purchaseMapper.deleteManyPurchase(id_arr);
		int m=purchaseMapper.deleteManyPurchaseInfo(id_arr);
		if(m==n&&n>0) {
			return new Result(200,"success");
		}
		return new Result (500,"系统异常");
	}

	@Override
	public Map<String, Object> queryPurchaseData() {
		Map<String, Object> maps=new HashMap<String, Object>();
		List<Goods> goodss = goodsMapper.selectGoods();
		List<String> provider = providerMapper.selectAllProviderName();
		List<String> settlmentWay = orderMapper.queryDeliveryWay();
		
		maps.put("goodss", goodss);
		maps.put("providers", provider);
		maps.put("settlmentWay", settlmentWay);
		return maps;
	}

	@Override
	public Result queryPageInfoByKeyWord(String keyWord, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		keyWord="%"+keyWord+"%";
		List<Purchase> purchases=purchaseMapper.selectPageInfoByKeyWord(keyWord);
		return new Result(200, "success",new PageInfo<Purchase>(purchases));
	}
}
