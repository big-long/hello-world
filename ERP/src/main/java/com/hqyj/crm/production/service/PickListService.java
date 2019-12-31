package com.hqyj.crm.production.service;

import com.hqyj.crm.common.entity.Result;
import com.hqyj.crm.production.entity.PickList;

public interface PickListService {

	Result queryPicklistPage(Integer pageNum, Integer pageSize);

	Result updatePicklistByPicklist(PickList pickList);

	Result queryPicklistInfo(String plId);

	Result deletePicklistInfo(String plId);

	Result deleteManyPicklist(String[] id_arr);

	Result queryPicklistByKeyWord(String keyWord, Integer pageNum, Integer pageSize);

}
