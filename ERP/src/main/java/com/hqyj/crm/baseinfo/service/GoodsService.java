package com.hqyj.crm.baseinfo.service;


import java.util.List;
import java.util.Set;

import com.hqyj.crm.baseinfo.entity.Goods;
import com.hqyj.crm.common.entity.Result;

public interface GoodsService {

	Result queryGoodsByGoodsName(Goods goods);

	Result queryGoods(Goods goods,Integer pageNum ,Integer pageSize);

	Result insertGoods(Goods goods);

	Result updateGoods(Goods goods);

	Result deleteGoodsByGoodsId(Integer GoodsId);

	Result queryGoodsByGoods(Goods goods);

	Result deleteManyGoods(String[] id_arr);

	Result queryGoodsByKeyWord(String keyWord ,String category,Integer pageNum,Integer pageSize);

	Set<String> queryCategroys();

	List<Goods> queryGoods();

}
