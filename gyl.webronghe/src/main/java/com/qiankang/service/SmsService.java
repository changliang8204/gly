package com.qiankang.service;

public interface SmsService extends Service {

	public void send(String phone, String msg);
	
}
