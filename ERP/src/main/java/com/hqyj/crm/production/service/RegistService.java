package com.hqyj.crm.production.service;

import com.hqyj.crm.common.entity.Result;
import com.hqyj.crm.production.entity.Regist;

public interface RegistService {

	Result queryRegistByProductionId(String productionId);

	Result queryRegists(Regist regist, Integer pageNum, Integer pageSize);

	Regist insertRegist(Regist regist);

	Result updateRegist(Regist regist);

	Result deleteRegist(String productionId);

	Result deleteManyRegist(String[] id_arr);

}
