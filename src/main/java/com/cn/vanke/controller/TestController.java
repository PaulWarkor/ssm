package com.cn.vanke.controller;

import io.swagger.annotations.Api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@Api(value = "测试Controller",description = "测试Controller")
public class TestController extends IBaseController{
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	@RequestMapping(value = "/index",method = RequestMethod.POST)
	public ModelAndView showTest(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		return modelAndView;
	}
	
	@RequestMapping(value = "/chater/{code}",method = RequestMethod.POST)
	public String showChater(@PathVariable("code") String code){
		return code;
	}
}
