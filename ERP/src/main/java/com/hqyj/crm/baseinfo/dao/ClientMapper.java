package com.hqyj.crm.baseinfo.dao;

import java.util.List;

import com.hqyj.crm.baseinfo.entity.Client;

public interface ClientMapper {
    int deleteClientByPrimaryKey(Integer clientId);

    int deleteClientInfoByPrimaryKey(Integer clientId);

    Client selectByPrimaryKey(Integer clientId);

    int updateClientInfoByPrimaryKeySelective(Client client);

    int updateClientByPrimaryKeySelective(Client client);
    
    List<Client> selectAllClients();

	int insertClientInfoSelective(Client client);
	
	int insertClientSelective(Client client);

	Client queryClientInfo(Integer clientId);

	int deleteManyClients(int[] idArr);

	int deleteManyClientInfos(int[] idArr);

	List<Client> selectClientByKeyWord(String keyWord);

	List<Client> queryAllClients();

	Client selectClientByName(String name);

	
}