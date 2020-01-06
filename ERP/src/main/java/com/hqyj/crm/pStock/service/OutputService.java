package com.hqyj.crm.pStock.service;

import com.hqyj.crm.common.entity.Result;
import com.hqyj.crm.pStock.entity.Output;

public interface OutputService {

	Result queryPageInfo(Integer pageNum, Integer pageSize);

	Result queryPageInfoByKeyWord(String keyWord, Integer pageNum, Integer pageSize);

	Result queryOutputInfo(String outputId);

	Result updateOutput(Output output);

	Result deleteManyOutput(String[] id_arr);

	Result deleteOutput(String outputId);


}
