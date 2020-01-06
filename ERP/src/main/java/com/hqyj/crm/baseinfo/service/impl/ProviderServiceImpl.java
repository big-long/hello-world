package com.hqyj.crm.baseinfo.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.crm.baseinfo.dao.ProviderMapper;
import com.hqyj.crm.baseinfo.entity.Provider;
import com.hqyj.crm.baseinfo.service.ProviderService;
import com.hqyj.crm.common.entity.Result;
@Service
public class ProviderServiceImpl implements ProviderService {
	@Autowired
	private ProviderMapper providerMapper;

	@Override
	public Result queryPageInfo(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Provider> providers = providerMapper.selectAllProvider();
		return new Result(200, "success",new PageInfo<Provider>(providers));
	}

	@Override
	public Result queryProviderInfo(Integer providerId) {
		 Provider provider = providerMapper.selectAllInfo(providerId);
		return new Result(200, "success",provider);
	}

	@Override
	public Result updateProvider(Provider provider) {
		int n=providerMapper.updateProviderByPrimaryKeySelective(provider);
		int m=providerMapper.updateProviderInfoByPrimaryKeySelective(provider);
		if(n==m&&m>0) {
			return new Result(200,"success");
		}
		return new Result(500,"修改失败");
	}

	@Override
	public Result insertProvider(Provider provider) {
		String providerName = provider.getProviderName();
		if(providerName==null||"".equals(providerName)) {
			return new Result(500,"供应商名称不能为空");
		}
		Provider provider_db=providerMapper.selectProviderByProviderName(providerName);
		if(provider_db!=null) {
			return new Result(500,"该供应商已存在");
		}
		int m=providerMapper.insertProviderInfoSelective(provider);
		int n=providerMapper.insertProviderSelective(provider);
		if(n==m&&m>0) {
			return new Result(200,"success");
		}
		return new Result(500,"新增失败");
	}

	@Override
	public Result deleteProvider(Integer providerId) {
		int n=providerMapper.deleteProviderByPrimaryKey(providerId);
		int m=providerMapper.deleteProviderInfoByPrimaryKey(providerId);
		if(n==m) {
			return new Result(200,"success");
		}
		return new Result(500,"删除失败");
	}

	@Override
	public Result getProviderByKeyWord(String keyword, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		if(keyword==null) {
			keyword="";
		}
		keyword="%"+keyword+"%";
		List<Provider> providers = providerMapper.selectProviderByKeyword(keyword);
		return new Result(200, "success",new PageInfo<Provider>(providers));
	}

	@Override
	public Result deleteManyProvider(String[] id_arr) {
		if(id_arr==null || id_arr.length==0) {
			return new Result(500,"请选择至少一条数据");
		}
		int[] idArr=new int[id_arr.length];
		for (int i = 0; i < id_arr.length; i++) {
			idArr[i]=Integer.parseInt(id_arr[i]);
		}
		int m=providerMapper.deleteManyProvider(idArr);
		int n=providerMapper.deleteManyProviderInfo(idArr);
		
		if(m==id_arr.length&&n==id_arr.length) {
			return new Result(200,"success");
		}
		return new Result(500,"删除失败");
	}

	@Override
	public List<String> queryAllProviderName() {
		return providerMapper.selectAllProviderName();
	}
	

}
