package com.cn.vanke.service;

import java.util.List;
import java.util.Map;

import com.cn.vanke.entity.SiteCompany;
import com.cn.vanke.page.domain.Pager;

public interface SiteCompanyService {

	/**
	 * 查询
	 * @param params
	 * @return
	 */
	public List<SiteCompany> query(Map<String,Object> params);
	
	/**
	 * 插入数据到archibus
	 * @param siteCompany
	 * @return
	 */
	public int insertIntoObject(SiteCompany siteCompany);
	
	/***
	 * 分页查询
	 * @param pager
	 * @return
	 */
	public List<Map<String,Object>> queryDataByPage(Pager<Map<String,Object>> pager);
	
	/***
	 * 通过封装JavaBean分页查询
	 * @param pager
	 * @return
	 */
	public List<SiteCompany> queryBeanDataByPage(Pager<SiteCompany> pager);
}
