package com.qiankang.session;

import java.io.Serializable;
/**
 * 会话票根
 * @author changliang
 *
 */
public class SessionTicket implements Serializable {
	private String ticket;

	public SessionTicket(String ticket) {
		super();
		this.ticket = ticket;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	@Override
	public String toString() {
		return ticket;
	}
	
}
