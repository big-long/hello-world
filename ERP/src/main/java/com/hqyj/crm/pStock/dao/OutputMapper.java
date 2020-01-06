package com.hqyj.crm.pStock.dao;

import java.util.List;

import com.hqyj.crm.pStock.entity.Output;

public interface OutputMapper {

    int insertSelective(Output record);

	List<Output> queryAllOutPuts();

	List<Output> queryAllOutPutsByKeyWord(String keyWord);

	Output queryOutputInfo(String outputId);

	int deleteManyOutput(String[] id_arr);

	int deleteOutputById(String outputId);

	int updateSelective(Output output);
}