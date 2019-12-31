package com.hqyj.crm.baseinfo.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.crm.baseinfo.dao.ClientMapper;
import com.hqyj.crm.baseinfo.entity.Client;
import com.hqyj.crm.baseinfo.service.ClientService;
import com.hqyj.crm.common.entity.Result;

@Service
public class ClientServiceImpl implements ClientService {
	@Autowired
	private ClientMapper clientMapper;

	@Override
	public Result queryPageInfo(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Client> clients = clientMapper.selectAllClients();
		return new Result(200, "success",new PageInfo<Client>(clients));
	}

	@Override
	public Result insertClient(Client client) {
		String name = client.getClientName();
		if(name==null || "".equals(name)) {
			return new Result(500, "客户名不能为空");
		}
		Client client_db=clientMapper.selectClientByName(name);
		if(client_db!=null) {
			return new Result(500, "客户已存在");
		}
		clientMapper.insertClientSelective(client);
		clientMapper.insertClientInfoSelective(client);
		return new Result(200, "success");
	}

	@Override
	public Result updateClient(Client client) {
		
		int n=clientMapper.updateClientByPrimaryKeySelective(client);
		int m=clientMapper.updateClientInfoByPrimaryKeySelective(client);
		if(m==n&n!=0) {
			return new Result(200, "success");
		}
		return new Result(500,"更新失败");
	}

	@Override
	public Result deleteClient(Integer clientId) {
		int n=clientMapper.deleteClientByPrimaryKey(clientId);
		if(n>0) {
			return new Result(200, "success");
		}
		return new Result(500, "删除失败");
	}

	@Override
	public Result queryClientInfo(Integer clientId) {
		 Client client = clientMapper.queryClientInfo(clientId);
		return new Result(200,"success",client);
	}

	@Override
	public Result deleteManyClients(String[] id_arr) {
		if (id_arr != null && id_arr.length > 0) {
			int[] idArr = new int[id_arr.length];
			for (int i = 0; i < id_arr.length; i++) {
				idArr[i] = Integer.parseInt(id_arr[i]);
			}
			int n = clientMapper.deleteManyClients(idArr);
			int m = clientMapper.deleteManyClientInfos(idArr);
			if (n == id_arr.length&&m==id_arr.length) {
				return new Result(200,"success");
			} else {
				return new Result(500, "删除不同步");
			}
		}
		return new Result(500,"没有选择数据");
	}

	@Override
	public Result queryClientByKeyWord(String keyWord, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		keyWord="%"+keyWord+"%";
		List<Client> clients = clientMapper.selectClientByKeyWord(keyWord);
		return new Result(200,"success",new PageInfo<Client>(clients)); 
	}

	@Override
	public List<Client> queryClients() {
		return clientMapper.queryAllClients();
	}

}
