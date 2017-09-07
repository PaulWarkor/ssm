package com.cn.vanke.page.dialect;

/**
 * 
 * 功能说明：MySQL实现分页
 * 
 * MySqlDialect.java
 * Copyright (C)1984-2017  深圳万科物业发展有限公司  All rights reserved.
 */
public class MySqlDialect implements Dialect{

	@Override
	public String buildPageSQL(String sql, int pageIndex, int pageSize) {
		StringBuilder pageSql = new StringBuilder(sql.length() + 20);
		long startIndex = (long)pageIndex*pageSize;
		pageSql.append(sql).append(" limit ").append(startIndex).append(",").append(pageSize);
		return pageSql.toString();
	}

	
}
