package com.hqyj.crm.production.dao;

import java.util.List;

import com.hqyj.crm.production.entity.PickList;

public interface PickListMapper {
    int insertSelective(PickList record);

	List<PickList> selectAllPickList();

	PickList selectPicklistByPlId(String plId);

	int updateSelective(PickList pickList);

	int deleteByPlId(String plId);

	int deleteManyPL(String[] id_arr);

	List<PickList> selectPicklistByKeyWord(String keyWord);
}