package com.qiankang.spring;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.qiankang.web.controller.SessionAwareController;

public class SessionTicketInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String ticket = null;
		Cookie[] cookies = request.getCookies();

		for(Cookie ck : cookies){
			if(ck.getName().equals("ticket")){
				ticket = ck.getValue();
				request.setAttribute("ticket", ticket);
			}
		}
		if(ticket == null)
			return false;
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		Object obj = handlerMethod.getBean();
		if(obj instanceof SessionAwareController){
			((SessionAwareController)obj).setTicket(ticket);
		}
		return true;
	}
	
}
