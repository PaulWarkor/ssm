package com.cn.vanke.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.vanke.dao.fmobile.EmployeeTblMapper;
import com.cn.vanke.entity.EmployeeTbl;
import com.cn.vanke.page.domain.Pager;
import com.cn.vanke.persistence.sql.SqlMapper;
import com.cn.vanke.service.EmployeeTblService;

@Service
public class EmployeeTblServiceImpl  implements EmployeeTblService {
	
	@Autowired
	private EmployeeTblMapper employeeTblMapper;
	
	@Autowired
	private SqlMapper fMobileSqlMapper;
	
	@Override
	public List<EmployeeTbl> query(Map<String, Object> params) {
		return employeeTblMapper.query(params);
	}

	@Override
	public int insertIntoObject(EmployeeTbl emloyeeTbl) {
		return fMobileSqlMapper.insert("insert into employee_tbl(name,date,singin) values('小王9',SYSDATE(),'9')");
	}

	@Override
	public List<Map<String, Object>> queryDataByPage(Pager<Map<String,Object>> pager) {
		return this.employeeTblMapper.queryDataByPage(pager);
	}
}
