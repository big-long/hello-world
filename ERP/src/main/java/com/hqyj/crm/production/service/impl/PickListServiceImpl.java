package com.hqyj.crm.production.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.crm.common.entity.Result;
import com.hqyj.crm.production.dao.PickListMapper;
import com.hqyj.crm.production.entity.PickList;
import com.hqyj.crm.production.service.PickListService;

@Service
public class PickListServiceImpl implements PickListService {
	@Autowired
	private PickListMapper pickListMapper;

	@Override
	public Result queryPicklistPage(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<PickList> pickLists = pickListMapper.selectAllPickList();

		return new Result(200, "success", new PageInfo<PickList>(pickLists));
	}

	@Override
	public Result updatePicklistByPicklist(PickList pickList) {
		String plId = pickList.getPlId();
		if (plId == null || "".equals(plId)) {
			return new Result(500, "领料单编号为空");
		}
		PickList pickList_db = pickListMapper.selectPicklistByPlId(plId);
		if (pickList_db == null) {
			int n = pickListMapper.insertSelective(pickList);
			if (n == 0) {
				return new Result(500, "新增失败");
			}
		} else {
			int n = pickListMapper.updateSelective(pickList);
			if (n == 0) {
				return new Result(500, "修改失败");
			}
		}
		return new Result(200, "success");
	}

	@Override
	public Result queryPicklistInfo(String plId) {
		if (plId == null || "".equals(plId)) {
			return new Result(500, "领料单编号为空");
		}
		PickList pickList = pickListMapper.selectPicklistByPlId(plId);
		return new Result(200,"success",pickList);
	}

	@Override
	public Result deletePicklistInfo(String plId) {
		if (plId == null || "".equals(plId)) {
			return new Result(500, "领料单编号为空");
		}
		int n = pickListMapper.deleteByPlId(plId);
		if (n == 0) {
			return new Result(500, "删除失败");
		}
		return new Result(200,"success");
	}

	@Override
	public Result deleteManyPicklist(String[] id_arr) {
		if(id_arr==null||id_arr.length==0) {
			return new Result(500,"请至少选择一条数据");
		}
		int n=pickListMapper.deleteManyPL(id_arr);
		if(n==id_arr.length) {
			return new Result(200,"success");
		}
		return new Result(500,"系统异常");
	}

	@Override
	public Result queryPicklistByKeyWord(String keyWord, Integer pageNum, Integer pageSize) {
		keyWord="%"+keyWord+"%";
		List<PickList> pickLists =pickListMapper.selectPicklistByKeyWord(keyWord);
		PageHelper.startPage(pageNum, pageSize);
		return new Result(200,"success",new PageInfo<PickList>(pickLists));
	}

	@Override
	public List<PickList> queryAllPickouts() {
		return  pickListMapper.selectAllPickList();
		
	}
}
