package com.cn.vanke.dao.archibus;

import java.util.List;
import java.util.Map;

import com.cn.vanke.entity.SiteCompany;
import com.cn.vanke.persistence.mybatis.Mapper;

public interface SiteCompanyMapper extends Mapper<SiteCompany> {
	/**
	 * 查询
	 * @param params
	 * @return
	 */
	public List<SiteCompany> query(Map<String,Object> params);
}