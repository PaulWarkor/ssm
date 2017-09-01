package com.cn.vanke.dao.fmobile;

import java.util.List;
import java.util.Map;

import com.cn.vanke.entity.EmployeeTbl;
import com.cn.vanke.persistence.mybatis.Mapper;

public interface EmployeeTblMapper extends Mapper<EmployeeTbl> {
	/**
	 * 查询
	 * @param params
	 * @return
	 */
	public List<EmployeeTbl> query(Map<String,Object> params);
}