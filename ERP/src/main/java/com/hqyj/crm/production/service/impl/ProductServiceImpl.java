package com.hqyj.crm.production.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.crm.common.entity.Result;
import com.hqyj.crm.production.dao.ProductMapper;
import com.hqyj.crm.production.entity.Product;
import com.hqyj.crm.production.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductMapper productMapper;

	@Override
	public Result queryProductByProductName(Product product) {
		if(product.getProductName()==null||"".equals(product.getProductName())) {
			return new Result(500,"产品名字为空");
		}
		 product =productMapper.selectByProductName(product);
		return new Result(200,"success",product);
	}

	@Override
	public Result queryProducts(Product product, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Product> products = productMapper.selectProducts();
		return new Result(200,"success",new PageInfo<Product>(products));
	}

	@Override
	public Result insertProduct(Product product) {
		String productName = product.getProductName();
		if(productName==null||"".equals(productName)) {
			return new Result(500, "产品名称不能为空");
		}
		Product product_db=productMapper.selectProductByProductName(productName);
		if(product_db!=null) {
			return new Result(500, "产品已存在");
		}
		int m=productMapper.insertProductSelective(product);
		int n=productMapper.insertProductInfoSelective(product);
		if(m>0&&n==m) {
		return new Result(200, "success");
		}
		return new Result(500, "新增失败");
	}

	@Override
	public Result updateProduct(Product product) {
		int m=productMapper.updateProductByPrimaryKeySelective(product);
		int n=productMapper.updateProductInfoByPrimaryKeySelective(product);
		if(m>0&&n==m) {
			return new Result(200,"success");
		}
		return new Result(500,"修改失败");
	}

	@Override
	public Result deleteProductByProductId(Integer productId) {
		int m =productMapper.deleteProductByPrimaryKey(productId);
		int n=productMapper.deleteProductInfoByPrimaryKey(productId);
		if(m>0&&n==m) {
			return new Result(200,"success");
		}
		return new Result(500,"删除失败");
	}

	@Override
	public Result queryProductByProduct(Product product) {
		product = productMapper.selectProductInfoByPrimaryKey(product.getProductId());
		return new Result(200, "success",product);
	}

	@Override
	public Result deleteManyProducts(String[] id_arr) {
		if (id_arr == null || id_arr.length == 0) {
			return new Result(500, "请选择至少一条数据");
		}
		int[] idArr = new int[id_arr.length];
		for (int i = 0; i < id_arr.length; i++) {
			idArr[i] = Integer.parseInt(id_arr[i]);
		}
		int n = productMapper.deleteManyProduct(idArr);
		int m = productMapper.deleteManyProductInfo(idArr);
		if (n == id_arr.length && m == id_arr.length) {
			return new Result(200, "success");
		}
		return new Result(500, "系统异常");
	}

	@Override
	public Result queryProductByKeyWord(String keyWord,String category,Integer pageNum,Integer pageSize) {
		keyWord="%"+keyWord+"%";
		PageHelper.startPage(pageNum, pageSize);
		List<Product> products=productMapper.selectProductByKeyWord(keyWord,category);
		return new Result(200,"success",new PageInfo<Product>(products));
	}

	@Override
	public Set<String> queryCategroys() {
		
		return productMapper.queryCategroys();
	}

	@Override
	public List<Product> queryProducts() {
		return productMapper.selectProducts();
	}

}
