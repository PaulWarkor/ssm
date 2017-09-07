package com.cn.vanke.service;

import java.util.List;
import java.util.Map;

import com.cn.vanke.entity.EmployeeTbl;
import com.cn.vanke.page.domain.Pager;

public interface EmployeeTblService {

	/**
	 * 查询
	 * @param params
	 * @return
	 */
	public List<EmployeeTbl> query(Map<String,Object> params);
	
	/***
	 * 插入员工信息数据到mysql
	 * @param emloyeeTbl
	 * @return
	 */
	public int insertIntoObject(EmployeeTbl emloyeeTbl);
	
	/***
	 * 分页查询
	 * @param pageParams
	 * @return
	 */
	public List<Map<String,Object>> queryDataByPage(Pager<Map<String,Object>> pager);
}
