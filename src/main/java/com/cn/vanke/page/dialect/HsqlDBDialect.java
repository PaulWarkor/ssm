package com.cn.vanke.page.dialect;

/**
 * 
 * 功能说明：分页支持HsqlDB内存数据库
 * 
 * HsqDbDialect.java
 * Copyright (C)1984-2017  深圳万科物业发展有限公司  All rights reserved.
 */
public class HsqlDBDialect implements Dialect {

	@Override
	public String buildPageSQL(String sql, int pageIndex, int pageSize) {
		StringBuilder pageSql = new StringBuilder(sql.length() + 30);
		long startIndex = (long)pageIndex*pageSize;
		pageSql.append(sql).append(" limit ").append(pageSize).append(" offset ").append(startIndex);
		return pageSql.toString();
	}

}
