package com.hqyj.crm.baseinfo.service;


import java.util.List;
import java.util.Set;

import com.github.pagehelper.PageInfo;
import com.hqyj.crm.baseinfo.entity.Product;
import com.hqyj.crm.common.entity.Result;

public interface ProductService {
	/**
	 * 
	 * <p>
	 * 	通过产品名称查询产品
	 * </p>
	 * @author zdl
	 * @Date 2019年12月25日
	 * @param product
	 * @return
	 */
	Result queryProductByProductName(Product product);
	/**
	 * 
	 * <p>
	 * 	通过产品信息和页面信息，进行查询
	 * </p>
	 * @author zdl
	 * @Date 2019年12月25日
	 * @param product
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	Result queryProducts(Product product,Integer pageNum ,Integer pageSize);
	/**
	 * 
	 * <p>
	 * 	新增产品
	 * </p>
	 * @author zdl
	 * @Date 2019年12月25日
	 * @param product
	 * @return
	 */
	Result insertProduct(Product product);
	/**
	 * 
	 * <p>
	 * 	通过id修改对应的产品
	 * </p>
	 * @author zdl
	 * @Date 2019年12月25日
	 * @param product
	 * @return
	 */
	Result updateProduct(Product product);
	/**
	 * 
	 * <p>
	 * 	通过指定id删除对应的产品
	 * </p>
	 * @author zdl
	 * @Date 2019年12月25日
	 * @param productId
	 * @return
	 */
	Result deleteProductByProductId(Integer productId);
	/**
	 * 
	 * <p>
	 * 	通过产品信息查询产品
	 * </p>
	 * @author zdl
	 * @Date 2019年12月25日
	 * @param product
	 * @return
	 */
	Result queryProductByProduct(Product product);
	/**
	 * 
	 * <p>
	 * 	批量删除
	 * </p>
	 * @author zdl
	 * @Date 2019年12月25日
	 * @param id_arr
	 * @return
	 */
	Result deleteManyProducts(String[] id_arr);
	/**
	 * 
	 * <p>
	 * 	通过关键字和类别进行查询，返回包含查询结果的页面信息
	 * </p>
	 * @author zdl
	 * @Date 2019年12月25日
	 * @param keyWord 关键字
	 * @param category 类别
	 * @param pageNum 当前页
	 * @param pageSize 页面大小
	 * @return
	 */
	Result queryProductByKeyWord(String keyWord ,String category,Integer pageNum,Integer pageSize);
	/**
	 * 
	 * <p>
	 * 	查询列表中包含的类别
	 * </p>
	 * @author zdl
	 * @Date 2019年12月25日
	 * @return
	 */
	Set<String> queryCategroys();
	/**
	 * 
	 * <p>
	 * 	返回产品名称和产品id
	 * </p>
	 * @author zdl
	 * @Date 2019年12月25日
	 * @return
	 */
	List<Product> queryProducts();

}
