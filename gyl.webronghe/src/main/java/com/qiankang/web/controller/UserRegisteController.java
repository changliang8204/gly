package com.qiankang.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qiankang.web.data.GeneralRespData;
import com.qiankang.web.data.GsonBody;
import com.qiankang.web.data.RespCode;
import com.qiankang.web.data.UserRegisteRequest;
/**
 * 用户注册控制器
 * @author changliang
 *
 */
@Controller
public class UserRegisteController {
	private static final Logger _log = LoggerFactory.getLogger(UserRegisteController.class);
	
	@RequestMapping(value = "userRegister", method = RequestMethod.POST)
	@ResponseBody
	public GeneralRespData registe(@GsonBody UserRegisteRequest userRegReq){
		_log.debug("收到用户注册请求");
		GeneralRespData resp = new GeneralRespData();
		resp.setCode(RespCode.SUCCESS);
		resp.setMessage("注册成功!");
		return resp;
	}
	
}
