package com.qiankang.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.qiankang.session.SessionCache;
import com.qiankang.session.SessionManage;
import com.qiankang.util.JsonUtil;
import com.qiankang.web.data.GeneralRespData;
import com.qiankang.web.data.RespCode;
/**
 * 登录检查拦截器
 * @author changliang
 *
 */
public class LoginCheckInterceptor extends HandlerInterceptorAdapter {
	private static final Logger _log = LoggerFactory.getLogger(LoginCheckInterceptor.class);
	private static final String cacheID = "sessionCache";
	@Autowired
	private SessionManage sessionManage ;
	private SessionCache cache;
	
	public void init(){	
		this.cache = sessionManage.getSessionCache(cacheID);
	}
	
	private void notloginResp(HttpServletResponse response) throws Exception{
		GeneralRespData respData = new GeneralRespData();
		respData.setCode(RespCode.NO_LOGIN);
		respData.setMessage("用户未登录");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().write(JsonUtil.toJson(respData));
	}
	
	 /**  
     * 在业务处理器处理请求之前被调用  
     * 如果返回false  
     *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链 
     * 如果返回true  
     *    执行下一个拦截器,直到所有的拦截器都执行完毕  
     *    再执行被拦截的Controller  
     *    然后进入拦截器链,  
     *    从最后一个拦截器往回执行所有的postHandle()  
     *    接着再从最后一个拦截器往回执行所有的afterCompletion()  
     */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		init();
		String ticket = (String)request.getAttribute("ticket");
			
		try{
			Object obj = cache.get(ticket);
			
			if(obj == null){
				notloginResp(response);
				return false;
			}
		}catch(NullPointerException e){
			notloginResp(response);
			return false;
		}
		return true;
	}

	/** 
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作    
     * 可在modelAndView中加入数据，比如当前时间 
     */  
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	/**  
     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等 
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()  
     */ 
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

	
}
