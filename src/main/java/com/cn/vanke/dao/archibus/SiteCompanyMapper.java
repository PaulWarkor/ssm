package com.cn.vanke.dao.archibus;

import java.util.List;
import java.util.Map;

import com.cn.vanke.entity.SiteCompany;
import com.cn.vanke.page.domain.Pager;
import com.cn.vanke.persistence.mybatis.Mapper;

public interface SiteCompanyMapper extends Mapper<SiteCompany> {
	/**
	 * 查询
	 * @param params
	 * @return
	 */
	public List<SiteCompany> query(Map<String,Object> params);
	
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