package com.qiankang.session.ehcache;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qiankang.session.SessionCache;
import com.qiankang.session.SessionManage;

public class EhcacheSessionManage implements SessionManage {
	private static final Logger _log = LoggerFactory
			.getLogger(EhcacheSessionManage.class);
	private static EhcacheSessionManage sessionCacheManage = new EhcacheSessionManage();
	private static ScheduledExecutorService scheduledExec = Executors.newSingleThreadScheduledExecutor();
	private CacheManager cacheManager;
	
	private EhcacheSessionManage(){
		cacheManager = CacheManager.create();
	}
	
	public EhcacheSessionManage getInstance(){
		if(sessionCacheManage == null){
			sessionCacheManage = new EhcacheSessionManage();
		}
		return sessionCacheManage;
	}
	
	@Override
	public SessionCache getSessionCache(String cacheID) {
		Cache ehcache = cacheManager.getCache(cacheID);
		EhcacheSessionCache sessionCache = new EhcacheSessionCache();
		sessionCache.setCache(ehcache);
		return sessionCache;
	}

	public synchronized void init() {
		_log.info("会话管理器启动...");
		scheduledExec.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				_log.debug("session缓存维护线程调度");
				EhcacheSessionCache sc = (EhcacheSessionCache)getSessionCache(sessionCacheID);
				try{
					sc.clearTimeoutSessions();
				}catch(Exception e){
					_log.error(ExceptionUtils.getStackTrace(e));
				}
			}
		}, 10, 10, TimeUnit.MINUTES);
	}

	public synchronized void close() {
		cacheManager.shutdown();
	}
}
