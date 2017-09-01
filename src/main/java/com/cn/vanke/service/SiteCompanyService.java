package com.cn.vanke.service;

import java.util.List;
import java.util.Map;

import com.cn.vanke.entity.SiteCompany;

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
}
