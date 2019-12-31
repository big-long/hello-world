package com.hqyj.crm.production.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hqyj.crm.common.entity.Result;
import com.hqyj.crm.production.entity.Provider;

public interface ProviderService {

	Result queryPageInfo(Integer pageNum, Integer pageSize);

	Result queryProviderInfo(Integer providerId);

	Result updateProvider(Provider provider);

	Result insertProvider(Provider provider);

	Result deleteProvider(Integer providerId);

	Result getProviderByKeyWord(String keyword, Integer pageNum, Integer pageSize);

	Result deleteManyProvider(String[] id_arr);

	List<String> queryAllProviderName();

}
