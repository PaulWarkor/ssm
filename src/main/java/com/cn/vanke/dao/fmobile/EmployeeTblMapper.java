package com.cn.vanke.dao.fmobile;

import java.util.List;
import java.util.Map;

import com.cn.vanke.entity.EmployeeTbl;
import com.cn.vanke.page.domain.Pager;
import com.cn.vanke.persistence.mybatis.Mapper;

public interface EmployeeTblMapper extends Mapper<EmployeeTbl> {
	/**
	 * 查询
	 * @param params
	 * @return
	 */
	public List<EmployeeTbl> query(Map<String,Object> params);
	
	/***
	 * 分页查询
	 * @param pageParams
	 * @return
	 */
	public List<Map<String,Object>> queryDataByPage(Pager pager);
}