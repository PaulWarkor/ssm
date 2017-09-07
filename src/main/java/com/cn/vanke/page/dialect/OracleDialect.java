/**
 * 
 */
package com.cn.vanke.page.dialect;


/**
 * 功能说明：Oracle 分页实现
 * 
 * OracleDialect.java
 * Copyright (C)1984-2017  深圳万科物业发展有限公司  All rights reserved.
 */
public class OracleDialect implements Dialect {


	@Override
	public String buildPageSQL(String sql, int pageIndex, int pageSize) {
		StringBuilder pageSql = new StringBuilder(sql.length() + 100);
		long startIndex = (long)pageIndex*pageSize;
		long endIndex = startIndex+pageSize;
		pageSql.append("select * from ( select temp.*, rownum row_id from ( ");
		pageSql.append(sql);
		pageSql.append(" ) temp where rownum <= ").append(endIndex);
		pageSql.append(") where row_id > ").append(startIndex);
		return pageSql.toString();
	}

}
