package com.hqyj.crm.production.service;

import java.util.List;

import com.hqyj.crm.common.entity.Result;
import com.hqyj.crm.production.entity.Client;

public interface ClientService {
	/**
	 * 
	 * <p>
	 * 	查询客户的列表信息
	 * </p>
	 * @author zdl
	 * @Date 2019年12月25日
	 * @return
	 */
	List<Client> queryClients();
	/**
	 * 
	 * <p>
	 * 	获得客户的分页信息
	 * </p>
	 * @author zdl
	 * @Date 2019年12月25日
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	Result queryPageInfo(Integer pageNum, Integer pageSize);
	/**
	 * 
	 * <p>
	 * 	新增客户
	 * </p>	
	 * @author zdl
	 * @Date 2019年12月25日
	 * @param client
	 * @return
	 */
	Result insertClient(Client client);
	/**
	 * 
	 * <p>
	 * 	修改客户
	 * </p>
	 * @author zdl
	 * @Date 2019年12月25日
	 * @param client
	 * @return
	 */
	Result updateClient(Client client);
	/**
	 * 
	 * <p>
	 * 	通过客户id删除对应的客户
	 * </p>
	 * @author zdl
	 * @Date 2019年12月25日
	 * @param clientId
	 * @return
	 */
	Result deleteClient(Integer clientId);
	/**
	 * 
	 * <p>
	 * 	通过客户id查询其对应的详细信息
	 * </p>
	 * @author zdl
	 * @Date 2019年12月25日
	 * @param clientId
	 * @return
	 */
	Result queryClientInfo(Integer clientId);
	/**
	 * 
	 * <p>
	 * 	批量删除
	 * </p>
	 * @author zdl
	 * @Date 2019年12月25日
	 * @param id_arr
	 * @return
	 */
	Result deleteManyClients(String[] id_arr);
	/**
	 * 
	 * <p>
	 * 	通过关键字查询客户
	 * </p>
	 * @author zdl
	 * @Date 2019年12月25日
	 * @param keyWord 关键字
	 * @param pageNum 当前页
	 * @param pageSize 页面大小
	 * @return
	 */
	Result queryClientByKeyWord(String keyWord, Integer pageNum, Integer pageSize);
}
