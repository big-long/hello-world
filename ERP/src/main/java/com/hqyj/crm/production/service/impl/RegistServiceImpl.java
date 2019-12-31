package com.hqyj.crm.production.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.crm.common.entity.Result;
import com.hqyj.crm.production.dao.RegistMapper;
import com.hqyj.crm.production.entity.Regist;
import com.hqyj.crm.production.service.RegistService;
@Service
public class RegistServiceImpl implements RegistService {
	@Autowired
	private RegistMapper registMapper;

	@Override
	public Result queryRegistByProductionId(String productionId) {
		 Regist regist = registMapper.selectByPrimaryKey(productionId);
		 return new Result(200, "success",regist);
	}

	@Override
	public Result queryRegists(Regist regist, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Regist> regists = registMapper.selectAllRegists(regist);
		return new Result(200, "success",new PageInfo<Regist>(regists));
	}

	@Override
	public Regist insertRegist(Regist regist) {
		int n=registMapper.insertSelective(regist);
		return regist;
	}

	@Override
	public Result updateRegist(Regist regist) {
		Regist regist_db = registMapper.selectByPrimaryKey(regist.getProductionId());
		if(regist_db!=null) {
			int n=registMapper.updateByPrimaryKeySelective(regist);
			if(n>0) {
				return new Result(200,"success");
			}else {
				return new Result(500,"修改保存失败");
			}
		}else {
			int n=registMapper.insertSelective(regist);
			if(n>0) {
				return new Result(200,"success");
			}else {
				return new Result(500,"新增保存失败");
			}
		}
		
	}

	@Override
	public Result deleteRegist(String productionId) {
		int num = registMapper.deleteByPrimaryKey(productionId);
		if(num>0) {
			return new Result(200, "success");
		}
		return new Result(500, "删除失败");
	}

	@Override
	public Result deleteManyRegist(String[] id_arr) {
		if (id_arr == null || id_arr.length == 0) {
			return new Result(500, "请选择至少一条数据");
		}
		/*
		 * int[] idArr = new int[id_arr.length]; for (int i = 0; i < id_arr.length; i++)
		 * { idArr[i] = Integer.parseInt(id_arr[i]); }
		 */
		int n = registMapper.deleteManyRegist(id_arr);
		if (n == id_arr.length) {
			return new Result(200, "success");
		}
		return new Result(500, "删除失败");
	}

}
