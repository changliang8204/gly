package com.qiankang.session;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UserSessionContext implements Serializable {
	private long lastTimestamp = System.currentTimeMillis(); //最新时间戳 毫秒
	private long avaliableTime = 10 * 6000; //可存续时间  毫秒
	private String userID;
	private String userName;
	private String organizationID;
	/**
	 * 用户授权的操作集合
	 */
	private Set<String> operationCodeSet = new HashSet<String>();
	/**
	 * 用户授权的功能集合
	 */
	private Set<String> functionSet = new HashSet<String>();
	
	private Set<String> authCodeSet = new HashSet<String>();
	
	public long getLastTimestamp() {
		return lastTimestamp;
	}
	
	public long getAvaliableTime() {
		return avaliableTime;
	}
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getOrganizationID() {
		return organizationID;
	}
	public void setOrganizationID(String organizationID) {
		this.organizationID = organizationID;
	}
	public Set<String> getOperationCodeSet() {
		return operationCodeSet;
	}
	public void setOperationCodeSet(Set<String> operationCodeSet) {
		this.operationCodeSet = operationCodeSet;
	}
	public Set<String> getFunctionSet() {
		return functionSet;
	}
	public void setFunctionSet(Set<String> functionSet) {
		this.functionSet = functionSet;
	}
	public Set<String> getAuthCodeSet() {
		return authCodeSet;
	}
	public void setAuthCodeSet(Set<String> authCodeSet) {
		this.authCodeSet = authCodeSet;
	}
	/**
	 * 
	 * @Title: session续期      
	 * @param         
	 * @return void
	 * @throws 异常对象
	 */
	public void renewal(){
		lastTimestamp = System.currentTimeMillis();
	}
}
