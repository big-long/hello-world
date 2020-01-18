package com.hqyj.crm.pStock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hqyj.crm.common.entity.Result;
import com.hqyj.crm.pSell.entity.Sell;
import com.hqyj.crm.pSell.service.SellService;
import com.hqyj.crm.pStock.entity.Output;
import com.hqyj.crm.pStock.service.OutputService;

@Controller
@RequestMapping("/output")
public class OutputController {

	@Autowired
	private OutputService outputService;
	@Autowired
	private SellService sellService;
	
	@RequestMapping("/list")
	public String  toPage(Model model) {
		List<Sell> sells = sellService.queryAllSell();
		model.addAttribute("sells", sells);
		return "pStock/outputList";
	}
	@RequestMapping("/getPageInfo")
	@ResponseBody
	public Result  getPageInfo(@RequestParam(defaultValue="1") Integer pageNum,@RequestParam(defaultValue="8") Integer pageSize) {
		return outputService.queryPageInfo(pageNum,pageSize);
	}
	@RequestMapping("/getOutputByKeyWord")
	@ResponseBody
	public Result getOutputByKeyWord(String keyWord, @RequestParam(defaultValue="1") Integer pageNum,@RequestParam(defaultValue="8") Integer pageSize) {
		return outputService.queryPageInfoByKeyWord(keyWord,pageNum,pageSize);
	}
	
	@RequestMapping("/getOutputInfo")
	@ResponseBody
	public Result getOutputInfo(String outputId) {
		return outputService.queryOutputInfo(outputId);
	}
	@RequestMapping("/update")
	@ResponseBody
	public Result updateOutput(Output Output) {
		return outputService.updateOutput(Output);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Result deleteOutput(String outputId) {
		return outputService.deleteOutput(outputId);
	}
	
	@RequestMapping("/deleteMany")
	@ResponseBody
	public Result deleteManyOutput(String[] id_arr) {
		return outputService.deleteManyOutput(id_arr);
	}
	@RequestMapping("/generateOutputId")
	@ResponseBody
	public Result getOutputId() {
		String outputId="OP"+System.currentTimeMillis();
		return new Result(200, "success",outputId);
	}
	@RequestMapping("/getSellInfo")
	@ResponseBody
	public Result getSellInfo(String sellId) {
		return sellService.querySellInfoBySellId(sellId);
	}
}
