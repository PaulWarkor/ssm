package com.cn.vanke.service;

import java.util.List;
import java.util.Map;

import com.cn.vanke.entity.EmployeeTbl;

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
}
