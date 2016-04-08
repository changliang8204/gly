package com.qiankang.service;

public interface SecurityService extends Service{
	
	/**
	 * 验证手机
	 * @param phone  目标手机号
	 * @param verifyCode 手机验证码
	 */
	public void verifyPhone(String phone, String verifyCode);
}
