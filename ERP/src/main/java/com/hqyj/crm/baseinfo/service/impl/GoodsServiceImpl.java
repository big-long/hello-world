package com.hqyj.crm.baseinfo.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.crm.baseinfo.dao.GoodsMapper;
import com.hqyj.crm.baseinfo.entity.Goods;
import com.hqyj.crm.baseinfo.service.GoodsService;
import com.hqyj.crm.common.entity.Result;

@Service
public class GoodsServiceImpl implements GoodsService {
	@Autowired
	private GoodsMapper goodsMapper;

	@Override
	public Result queryGoodsByGoodsName(Goods goods) {
		goods = goodsMapper.selectByGoodsName(goods);
		return new Result(200, "success", goods);
	}

	@Override
	public Result queryGoods(Goods goods, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Goods> goodss = goodsMapper.selectGoods();
		return new Result(200,"success",new PageInfo<Goods>(goodss));
	}

	@Override
	public Result insertGoods(Goods goods) {
		String goodName = goods.getGoodsName();
		if (goodName == null || "".equals(goodName)) {
			return new Result(500, "商品名称不能为空");
		}
		Goods goods_db = goodsMapper.selectByGoodsName(goods);
		if (goods_db != null) {
			return new Result(500, "商品已存在");
		}
		int n=goodsMapper.insertGoodsSelective(goods);
		int m=goodsMapper.insertGoodsInfoSelective(goods);
		if(m>0&&n==m) {
			return new Result(200, "success");
		}
		return new Result(500, "商品新增失败");
	}

	@Override
	public Result updateGoods(Goods goods) {
		int n=goodsMapper.updateGoodsByPrimaryKeySelective(goods);
		int m=goodsMapper.updateGoodsInfoByPrimaryKeySelective(goods);
		if(m>0&&m==n) {
			return new Result(200, "success");
		}
//		 goodsMapper.selectByPrimaryKey(Goods.getGoodsId());
		 return new Result(500, "修改失败");
	}

	@Override
	public Result deleteGoodsByGoodsId(Integer GoodsId) {
		int n=goodsMapper.deleteGoodsByPrimaryKey(GoodsId);
		int m=goodsMapper.deleteGoodsInfoByPrimaryKey(GoodsId);
		if(n>0&&m==n) {
			return new Result(200,"success");
		}
		return new Result(500, "删除失败");
	}

	@Override
	public Result queryGoodsByGoods(Goods goods) {
		goods = goodsMapper.selectGoodsInfoByPrimaryKey(goods.getGoodsId());
		return new Result(200, "success", goods);
	}

	@Override
	public Result deleteManyGoods(String[] id_arr) {
		if (id_arr == null || id_arr.length == 0) {
			return new Result(500, "请选择至少一条数据");
		}
		int[] idArr = new int[id_arr.length];
		for (int i = 0; i < id_arr.length; i++) {
			idArr[i] = Integer.parseInt(id_arr[i]);
		}
		int n = goodsMapper.deleteManyGoods(idArr);
		int m = goodsMapper.deleteManyGoodsInfo(idArr);
		if (n == id_arr.length && m == id_arr.length) {
			return new Result(200, "success");
		}
		return new Result(500, "删除失败");
	}

	@Override
	public Result queryGoodsByKeyWord(String keyWord, String category, Integer pageNum, Integer pageSize) {
		keyWord = "%" + keyWord + "%";
		PageHelper.startPage(pageNum, pageSize);
		List<Goods> goods = goodsMapper.selectGoodsByKeyWord(keyWord, category);
		return new Result(200, "success", new PageInfo<Goods>(goods));
	}

	@Override
	public Set<String> queryCategroys() {

		return goodsMapper.queryCategroys();
	}

	@Override
	public List<Goods> queryGoods() {
		return 	goodsMapper.selectGoods();
		
	}

}
