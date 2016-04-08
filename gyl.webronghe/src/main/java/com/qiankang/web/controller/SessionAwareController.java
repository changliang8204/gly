package com.qiankang.web.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.qiankang.session.SessionCache;
import com.qiankang.session.SessionManage;

public abstract class SessionAwareController {
	protected String ticket;
	
	@Autowired
	protected SessionManage sessionManage;
	
	public SessionCache getSessionCache(){
		SessionCache cache = sessionManage
				.getSessionCache(SessionManage.sessionCacheID);
		return cache;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	
}
