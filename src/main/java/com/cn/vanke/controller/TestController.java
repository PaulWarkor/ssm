package com.cn.vanke.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class TestController extends IBaseController{
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	@RequestMapping("/index")
	public ModelAndView showTest(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		return modelAndView;
	}
	
	@RequestMapping("/chater/{code}")
	public String showChater(@PathVariable("code") String code){
		return code;
	}
}
