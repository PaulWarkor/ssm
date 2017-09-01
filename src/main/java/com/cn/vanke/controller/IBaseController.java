package com.cn.vanke.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/***
 *Controller基础类 
 *（org.springframework.web.context.request.RequestContextListener） 
 *该作用域仅适用于WebApplicationContext环境 
 */
public class IBaseController {
	/***
	 * @Title : getProjectRealPath
	 * @Description : 获取项目路径
	 * @param @return
	 * @return String
	 * @throws
	 */
	public String getProjectRealPath(String fileName){
		if("".equals(fileName)){
			return this.getServletContext().getRealPath("/");
		}else{
			return this.getServletContext().getRealPath("/") + fileName;
		}
	}
	
	/***
	 * @Title : getServletContext
	 * @Description : 获取ServletContext
	 * @param @return
	 * @return ServletContext
	 * @throws
	 */
	public ServletContext getServletContext(){
		return this.getSession().getServletContext();
	}
	
	/**
	 * @Title : getRequest
	 * @Description : 获取request
	 * @param @return
	 * @return HttpServletRequest
	 * @throws
	 */
	public HttpServletRequest getRequest(){
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	}
	
	/***
	 * @Title : getResponse
	 * @Description : 获取response
	 * @param @return
	 * @return HttpServletResponse
	 * @throws
	 */
	public HttpServletResponse getResponse(){
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
	}
	
	/***
	 * @Title : getSession
	 * @Description : 获取Session
	 * @param @return
	 * @return HttpSession
	 * @throws
	 */
	public HttpSession getSession(){
		return this.getRequest().getSession();
	}
	
	/**
	 * @Title : getServerPath
	 * @Description : 获取ServerPath
	 * @param @return
	 * @return String
	 * @throws
	 */
	public String getServerPath(){
		return this.getRequest().getServletPath();
	}
}
