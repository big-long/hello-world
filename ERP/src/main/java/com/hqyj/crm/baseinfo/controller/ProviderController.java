package com.hqyj.crm.baseinfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hqyj.crm.baseinfo.entity.Provider;
import com.hqyj.crm.baseinfo.service.ProviderService;
import com.hqyj.crm.common.entity.Result;


@Controller
@RequestMapping("/provider")
public class ProviderController {
	@Autowired
	private ProviderService providerService;
	/**
	 * 
	 * <p>
	 * 	返回到供应商页面
	 * </p>
	 * @author zdl
	 * @Date 2019年12月31日
	 * @return
	 */
	@RequestMapping("/list")
	public String toPage() {
		return "customer/providerList";
	}
	/**
	 * 
	 * <p>
	 * 	获得分页信息，默认第一页显示8条数据
	 * </p>
	 * @author zdl
	 * @Date 2019年12月31日
	 * @param pageNum 当前页 默认第一页
	 * @param pageSize 页面大小，默认显示8条数据
	 * @return
	 */
	@RequestMapping("/getPageInfo")
	@ResponseBody
	public Result getPageInfo(@RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "8") Integer pageSize) {
		return providerService.queryPageInfo(pageNum, pageSize);
	}
	/**
	 * 
	 * <p>
	 * 	根据关键字查询供应商，返回分页信息
	 * </p>
	 * @author zdl
	 * @Date 2019年12月31日
	 * @param keyword 供应商简称的关键字
	 * @param pageNum 当前页 默认第一页
	 * @param pageSize 页面大小，默认显示8条数据
	 * @return
	 */
	@RequestMapping("/getProviderByKeyWord")
	@ResponseBody
	public Result getProviderByKeyWord(String keyword, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "8") Integer pageSize) {
		return providerService.getProviderByKeyWord(keyword, pageNum, pageSize);
	}
	/**
	 * 
	 * <p>
	 * 	根据供应商编号获得对应供应商的详细信息
	 * </p>
	 * @author zdl
	 * @Date 2019年12月31日
	 * @param providerId 供应商编号
	 * @return
	 */
	@RequestMapping("/getProviderInfo")
	@ResponseBody
	public Result getProviderInfo(Integer providerId) {
		return providerService.queryProviderInfo(providerId);
	}
	/**
	 * 
	 * <p>
	 * 	根据供应商信息新增或修改对应的供应商，根据是否有供应商编号判断是新增还是修改
	 * </p>
	 * @author zdl
	 * @Date 2019年12月31日
	 * @param provider 供应商信息
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Result updateProvider(Provider provider) {
		if (provider.getProviderId() != null) {
			return providerService.updateProvider(provider);
		}
		return providerService.insertProvider(provider);

	}
	/**
	 * 
	 * <p>
	 * 	根据供应商的编号删除对应的供应商
	 * </p>
	 * @author zdl
	 * @Date 2019年12月31日
	 * @param providerId 供应商编号
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Result deleteProvider(Integer providerId) {
		return providerService.deleteProvider(providerId);
	}
	/**
	 * 
	 * <p>
	 * 	根据前台传回来的多个供应商编号，进行批量删除，前台传回的数组类型是字符串数组，后台需要转换成int类型的数组
	 * </p>
	 * @author zdl
	 * @Date 2019年12月31日
	 * @param id_arr 供应商编号数组
	 * @return
	 */
	@RequestMapping("/deleteMany")
	@ResponseBody
	public Result deleteManyProvider(String[] id_arr) {
		return providerService.deleteManyProvider(id_arr);
	}
}
