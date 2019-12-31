package com.hqyj.crm.production.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.hqyj.crm.common.entity.Result;
import com.hqyj.crm.production.entity.Provider;
import com.hqyj.crm.production.service.ProviderService;

import net.sf.jsqlparser.statement.select.First.Keyword;

@Controller
@RequestMapping("/provider")
public class ProviderController {
	@Autowired
	private ProviderService providerService;

	@RequestMapping("/list")
	public String toPage() {
		return "customer/providerList";
	}

	@RequestMapping("/getPageInfo")
	@ResponseBody
	public Result getPageInfo(@RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "8") Integer pageSize) {
		return providerService.queryPageInfo(pageNum, pageSize);
	}

	@RequestMapping("/getProviderByKeyWord")
	@ResponseBody
	public Result getProviderByKeyWord(String keyword, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "8") Integer pageSize) {
		return providerService.getProviderByKeyWord(keyword, pageNum, pageSize);
	}

	@RequestMapping("/getProviderInfo")
	@ResponseBody
	public Result getProviderInfo(Integer providerId) {
		return providerService.queryProviderInfo(providerId);
	}

	@RequestMapping("/update")
	@ResponseBody
	public Result updateProvider(Provider provider) {
		if (provider.getProviderId() != null) {
			return providerService.updateProvider(provider);
		}
		return providerService.insertProvider(provider);

	}

	@RequestMapping("/delete")
	@ResponseBody
	public Result deleteProvider(Integer providerId) {
		return providerService.deleteProvider(providerId);
	}

	@RequestMapping("/deleteMany")
	@ResponseBody
	public Result deleteManyProvider(String[] id_arr) {
		return providerService.deleteManyProvider(id_arr);
	}
}
