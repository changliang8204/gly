package com.qiankang.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DemoController {
	private static final Logger _log = LoggerFactory.getLogger(DemoController.class);
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello(){
		_log.info("收到请求");
		return "demo";
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(){
		_log.info("访问首页");
		return "login";
	}
}
