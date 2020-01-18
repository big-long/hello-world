package com.hqyj.crm.pStock.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.crm.baseinfo.dao.GoodsMapper;
import com.hqyj.crm.common.entity.Result;
import com.hqyj.crm.pStock.dao.GStockMapper;
import com.hqyj.crm.pStock.dao.GoodsStockMapper;
import com.hqyj.crm.pStock.dao.PickoutMapper;
import com.hqyj.crm.pStock.entity.GStock;
import com.hqyj.crm.pStock.entity.GoodsStock;
import com.hqyj.crm.pStock.entity.Pickout;
import com.hqyj.crm.pStock.service.GoodsStockService;

@Service
public class GoodsStockServiceImpl implements GoodsStockService {
	@Autowired
	private GoodsStockMapper goodsStockMapper;
	@Autowired
	private GStockMapper gStockMapper;
	@Autowired
	private PickoutMapper pickoutMapper;
	@Autowired
	private GoodsMapper goodsMapper;

	@Override
	public Result queryPageInfo(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<GoodsStock> list = goodsStockMapper.selectAll();
		return new Result(200, "success", new PageInfo<GoodsStock>(list));
	}

	@Override
	public Result queryGStock(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<GStock> list = gStockMapper.selectAll();
		return new Result(200, "success", new PageInfo<GStock>(list));
	}

	@Override
	public Result updateGStock(GStock gStock) {
		String gStockId = gStock.getgStockId();
		if (gStockId == null || "".equals(gStockId)) {
			return new Result(500, "入库单编号为空");
		}
		GStock gStock_db = gStockMapper.selectGStock(gStockId);
		if (gStock_db == null) {
			int stockNumber = goodsMapper.selectStockNumberByGoodsName(gStock.getGoodsName());

			int goodsId = goodsMapper.selectGoodsIdByGoodsName(gStock.getGoodsName());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("goodsId", goodsId);
			map.put("stockNumber", stockNumber + gStock.getPurchaseNumber());
			int n = goodsMapper.updateStockNumber(map);
			if (n > 0) {
				int m=gStockMapper.insertGStockByGStock(gStock);
				if (m > 0) {
					return new Result(200, "新增成功");
				}
				return new Result(200, "新增成功");
			} else {
				return new Result(500, "库存更新失败");
			}
			
		} else {
			int n = gStockMapper.updateGStockByGStock(gStock);
			if (n > 0) {
				return new Result(200, "修改成功");
			}
		}
		return new Result(500, "系统异常");
	}

	@Override
	public Result queryPurchaseInfo(String purchaseId) {
		GStock gStock = gStockMapper.selectPurchaseInfo(purchaseId);
		return new Result(200, "success", gStock);
	}

	@Override
	public Result queryGStockInfo(String gStockId) {
		GStock gStock = gStockMapper.selectGStockInfo(gStockId);
		return new Result(200, "success", gStock);
	}

	@Override
	public Result deleteGStock(String gStockId) {
		if (gStockId == null || "".equals(gStockId)) {
			return new Result(500, "入库单编号为空");
		}
		int n = gStockMapper.deleteGStockByPrimaryKey(gStockId);
		if (n > 0) {
			return new Result(200, "success");
		}
		return new Result(500, "系统错误");
	}

	@Override
	public Result queryPickoutPageInfo(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Pickout> pickouts = pickoutMapper.queryAllOutPuts();
		return new Result(200, "success", new PageInfo<Pickout>(pickouts));
	}

	@Override
	public Result updatePickout(Pickout pickout) {
		String pickoutId = pickout.getPickoutId();
		if (pickoutId == null || "".equals(pickoutId)) {
			return new Result(500, "出库单编号为空");
		}
		Pickout pickout_db = pickoutMapper.queryPickoutInfo(pickoutId);
		if (pickout_db == null) {
			int num = pickoutMapper.insertSelective(pickout);
			if (num > 0) {
				int stockNumber = goodsMapper.selectStockNumberByGoodsName(pickout.getGoodsName());
				if (stockNumber > pickout.getPickNumber()) {
					int goodsId = goodsMapper.selectGoodsIdByGoodsName(pickout.getGoodsName());
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("goodsId", goodsId);
					map.put("stockNumber", stockNumber - pickout.getPickNumber());
					int n = goodsMapper.updateStockNumber(map);
					if (n > 0) {
						return new Result(200, "新增成功");
					} else {
						return new Result(500, "库存更新失败");
					}
				} else {
					return new Result(500, "库存更新失败");
				}

			}
		} else {
			int num = pickoutMapper.updateSelective(pickout);
			if (num > 0) {
				return new Result(200, "更新成功");
			}
		}
		return new Result(500, "系统异常");
	}

	@Override
	public Result queryPickoutInfoByPlId(String plId) {
		Pickout pickout = pickoutMapper.selectPickoutInfoByPlId(plId);
		return new Result(200, "success", pickout);
	}

	@Override
	public Result queryPickoutInfoByPickoutId(String pickoutId) {
		Pickout pickout = pickoutMapper.selectPickoutInfoByPickoutId(pickoutId);
		return new Result(200, "success", pickout);
	}

	@Override
	public Result deletePickout(String pickoutId) {
		if (pickoutId == null || "".equals(pickoutId)) {
			return new Result(500, "领料单编号为空");
		}
		int n = pickoutMapper.deletePickoutById(pickoutId);
		if (n > 0) {
			return new Result(200, "删除成功");
		}
		return new Result(500, "系统异常");
	}

}
