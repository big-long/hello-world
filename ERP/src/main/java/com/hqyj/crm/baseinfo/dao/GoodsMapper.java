package com.hqyj.crm.baseinfo.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.hqyj.crm.baseinfo.entity.Goods;

public interface GoodsMapper {
	
	List<Goods> selectGoods();
	
	Goods selectByGoodsName(Goods goods);
	
	Set<String> queryCategroys();
	
	List<Goods> selectGoodsByKeyWord(@Param("keyWord") String keyWord,@Param("category") String category);
	
	Goods selectGoodsInfoByPrimaryKey(Integer goodsId);
	
	Goods selectGoodsByPrimaryKey(Integer goodsId);
	
	int deleteGoodsByPrimaryKey(Integer goodsId);
	
	
	int deleteGoodsInfoByPrimaryKey(Integer goodsId);
	
	int deleteManyGoodsInfo(int[] id_arr);
	
	int deleteManyGoods(int[] id_arr);
	
	int insertGoodsSelective(Goods goods);
	
	int insertGoodsInfoSelective(Goods goods);
	
	int updateGoodsInfoByPrimaryKeySelective(Goods goods);
	
	int updateGoodsByPrimaryKeySelective(Goods goods);

	int selectStockNumberByGoodsName(String goodsName);

	int updateStockNumber(Map map);

	Integer selectGoodsIdByGoodsName(String goodsName);
}
