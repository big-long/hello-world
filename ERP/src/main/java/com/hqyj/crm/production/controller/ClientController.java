package com.hqyj.crm.production.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hqyj.crm.common.entity.Result;
import com.hqyj.crm.production.entity.Client;
import com.hqyj.crm.production.service.ClientService;

@Controller
@RequestMapping("/client")
public class ClientController {
	@Autowired
	private ClientService clientService;
	/**
	 * 
	 * <p>
	 * 	返回顾客的页面
	 * </p>
	 * @author zdl
	 * @Date 2019年12月24日
	 * @return 返回到顾客的页面
	 */
	@RequestMapping("list")
	public String  toPage() {
		return "customer/clientList";
	}
	/**
	 * 
	 * <p>
	 * 	批量删除
	 * </p>
	 * @author zdl
	 * @Date 2019年12月24日
	 * @param id_arr 前端传回来的id数组
	 * @return 返回是否删除成功
	 */
	@RequestMapping("/deleteMany")
	@ResponseBody
	public Result deleteMany(String[] id_arr) {
		return clientService.deleteManyClients(id_arr);
	}
	/**
	 * 
	 * <p>
	 * 	根据id查询具体的客户
	 * </p>
	 * @author zdl
	 * @Date 2019年12月24日
	 * @param clientId 顾客id
	 * @return 返回查询到的顾客信息
	 */
	@RequestMapping("/getClientInfo")
	@ResponseBody
	public Result getClientInfo(Integer clientId) {
		return  clientService.queryClientInfo(clientId);
	}
	/**
	 * 
	 * <p>
	 * 	根据关键字查询客户简称，获得查询客户的分页信息
	 * </p>
	 * @author zdl
	 * @Date 2019年12月24日
	 * @param keyWord	关键字
	 * @param pageNum	当前页
	 * @param pageSize	页面大小
	 * @return
	 */
	@RequestMapping("/getClientByKeyWord")
	@ResponseBody
	public Result getClientByKeyWord(String keyWord,@RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "8") Integer pageSize) {
		return clientService.queryClientByKeyWord(keyWord,pageNum,pageSize);
	}
	/**
	 * 
	 * <p>
	 * 	查询页面信息
	 * </p>
	 * @author zdl
	 * @Date 2019年12月24日
	 * @param pageNum 当前页
	 * @param pageSize 页面大小
	 * @return
	 */
	@RequestMapping("/getPageInfo")
	@ResponseBody
	public Result getPageInfo(@RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "8") Integer pageSize) {
		return clientService.queryPageInfo(pageNum,pageSize);
	}
	/**
	 * 
	 * <p>
	 * 	通过id有无进行修改或新增删除
	 * </p>
	 * @author zdl
	 * @Date 2019年12月24日
	 * @param client 客户信息
	 * @return
	 */
	@PostMapping("/update")
	@ResponseBody
	public Result updateClient(Client client) {
		if(client.getClientId()!=null) {
			return clientService.updateClient(client);
		}
		return clientService.insertClient(client);
	}
	/**
	 * 
	 * <p>	
	 * 	根据id删除客户信息
	 * </p>
	 * @author zdl
	 * @Date 2019年12月24日
	 * @param clientId 客户id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Result deleteClient(Integer clientId) {
		return clientService.deleteClient(clientId);
	}
}
