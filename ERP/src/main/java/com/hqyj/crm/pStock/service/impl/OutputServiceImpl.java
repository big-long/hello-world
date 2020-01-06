package com.hqyj.crm.pStock.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.IntNode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.crm.common.entity.Result;
import com.hqyj.crm.pStock.dao.OutputMapper;
import com.hqyj.crm.pStock.entity.Output;
import com.hqyj.crm.pStock.service.OutputService;
@Service
public class OutputServiceImpl implements OutputService{
	
	@Autowired
	private OutputMapper outputMapper;

	@Override
	public Result queryPageInfo(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Output> outputs=outputMapper.queryAllOutPuts();
		return new Result(200,"success",new PageInfo<Output>(outputs));
	}

	@Override
	public Result queryPageInfoByKeyWord(String keyWord, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		keyWord="%"+keyWord+"%";
		List<Output> outputs=outputMapper.queryAllOutPutsByKeyWord(keyWord);
		return new Result(200,"success",new PageInfo<Output>(outputs));
	}

	@Override
	public Result queryOutputInfo(String outputId) {
		Output output=outputMapper.queryOutputInfo(outputId);
		return new Result(200,"success",output);
	}

	@Override
	public Result updateOutput(Output output) {
		String outputId = output.getOutputId();
		if(outputId==null||"".equals(outputId)) {
			return new Result(500, "出库单编号为空");
		}
		Output output_db = outputMapper.queryOutputInfo(outputId);
		if(output_db==null) {
			int n =outputMapper.insertSelective(output);
			if(n==0) {
				return new Result(500,"新增失败");
			}
		}else {
			int n =outputMapper.updateSelective(output);
			if(n==0) {
				return new Result(500,"修改失败");
			}
		}
		return new Result(200,"success");
	}

	@Override
	public Result deleteManyOutput(String[] id_arr) {
		if(id_arr==null||id_arr.length==0) {
			return new Result(500, "请至少选择一条数据");
		}
		int n=outputMapper.deleteManyOutput(id_arr);
		if(n==id_arr.length) {
			return new Result(200,"success");
		}
		return new Result(500,"系统异常");
	}

	@Override
	public Result deleteOutput(String outputId) {
		if(outputId==null||"".equals(outputId)) {
			return new Result(500, "出库单编号为空");
		}
		int n=outputMapper.deleteOutputById(outputId);
		return new Result(200,"success");
	}
}
