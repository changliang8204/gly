package com.qiankang.session.ehcache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qiankang.session.SessionCache;
import com.qiankang.session.UserSessionContext;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

public class EhcacheSessionCache implements SessionCache {
	private static final Logger _log = LoggerFactory.getLogger(EhcacheSessionCache.class);
	
	private Cache cache;
	
	public void setCache(Cache cache) {
		this.cache = cache;
	}
	/**
	 * 
	 * @Title: 清理过期的session     
	 * @param         
	 * @return void
	 * @throws 异常对象
	 */
	void clearTimeoutSessions(){
		for(Object key : cache.getKeys()){
			Element element = cache.get(key);
			if(element != null 
					&& (element.getObjectValue() instanceof UserSessionContext)){
				UserSessionContext usc = (UserSessionContext)element.getObjectValue();
				long currentTimestamp = System.currentTimeMillis();
				if((usc.getLastTimestamp()+usc.getAvaliableTime()) > currentTimestamp){
					//session 已超时
					cache.remove(key);
					_log.debug("清理过期session[{}]",usc.getUserID());
				}
			}
		}
	}

	@Override
	public void put(String key, Object obj) {
		Element e = new Element(key, obj);  
		cache.put(e);
	}

	@Override
	public Object get(String key) {
		Element e = cache.get(key);
		 if(e == null){ 
			 return null;
		 }
		return e.getObjectValue();
	}

	@Override
	public void delete(String key) {
		cache.remove(key);
	}

}
