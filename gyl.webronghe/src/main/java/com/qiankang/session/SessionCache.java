package com.qiankang.session;

public interface SessionCache {
	public void put(String key,Object obj);
	
	public Object get(String key);
	
	public void delete(String key);
}
