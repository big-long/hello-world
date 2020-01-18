package com.hqyj.crm.pStock.dao;

import java.util.List;

import com.hqyj.crm.pStock.entity.Pickout;

public interface PickoutMapper {

    int insertSelective(Pickout pickout);

	List<Pickout> queryAllOutPuts();
	
	List<Pickout> queryAllOutPutsByKeyWord(String keyWord);

	Pickout queryPickoutInfo(String pickoutId);

	int deleteManyPickout(String[] id_arr);

	int deletePickoutById(String pickoutId);

	int updateSelective(Pickout pickout);

	Pickout selectPickoutInfoByPlId(String plId);

	Pickout selectPickoutInfoByPickoutId(String pickoutId);
}