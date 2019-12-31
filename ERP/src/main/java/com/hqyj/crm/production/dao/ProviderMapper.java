package com.hqyj.crm.production.dao;

import java.util.List;

import com.hqyj.crm.production.entity.Provider;

public interface ProviderMapper {
	List<Provider> selectAllProvider();
	
	Provider selectAllInfo(Integer providerId);
	
    int deleteProviderInfoByPrimaryKey(Integer providerId);

    int deleteProviderByPrimaryKey(Integer providerId);
    
    int insertProviderSelective(Provider record);

    int insertProviderInfoSelective(Provider record);

	Provider selectByPrimaryKey(Integer providerId);

    int updateProviderByPrimaryKeySelective(Provider record);

    int updateProviderInfoByPrimaryKeySelective(Provider record);

	List<Provider> selectProviderByKeyword(String keyword);

	int deleteManyProvider(int[] idArr);
	
	int deleteManyProviderInfo(int[] idArr);

	List<String> selectAllProviderName();

	Provider selectProviderByProviderName(String providerName);
}