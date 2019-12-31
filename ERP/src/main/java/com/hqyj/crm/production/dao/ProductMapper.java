package com.hqyj.crm.production.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.hqyj.crm.production.entity.Product;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer productId);

    int insertProductSelective(Product record);
    
    int insertProductInfoSelective(Product record);

    Product selectProductInfoByPrimaryKey(Integer productId);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

	Product selectByProductName(Product product);

	List<Product> selectProducts();

	int updateProductByPrimaryKeySelective(Product product);
	
	int updateProductInfoByPrimaryKeySelective(Product product);

	Integer deleteProductByPrimaryKey(Integer productId);

	Integer deleteProductInfoByPrimaryKey(Integer productId);

	int deleteManyProductInfo(int[] idArr);
	
	int deleteManyProduct(int[] idArr);

	List<Product> selectProductByKeyWord(@Param("keyWord") String keyWord,@Param("category") String category);

	Set<String> queryCategroys();

	Product selectProductByProductName(String productName);



}