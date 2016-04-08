package com.qiankang.session;

public interface SessionManage {
	public static final String sessionCacheID = "sessionCache";
	
	public SessionCache getSessionCache(String cacheID);
	
	public void init();
	
	public void close();
}
