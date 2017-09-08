package com.cn.vanke.junit;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cn.vanke.entity.SiteCompany;
import com.cn.vanke.page.domain.Pager;
import com.cn.vanke.service.EmployeeTblService;
import com.cn.vanke.service.SiteCompanyService;
import com.cn.vanke.util.JsonUtils;

public class SpringMvcTest extends BaseJunitTest{

	@Autowired
	private SiteCompanyService siteCompanyService;
	@Autowired
	private EmployeeTblService employeeTblService;
	
	@Test
	public void testMysql(){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("queryName", "name");
		params.put("queryValue", "小王");
		System.err.println(JsonUtils.object2Json(employeeTblService.query(params)));
	}
	
	@Test
	public void testMysqlForPager(){
		Pager<Map<String,Object>> pager = new Pager<Map<String,Object>>();
		pager.setPageIndex(2);
		pager.setPageSize(8);
		pager.getQueryParams().put("value", "小王");
		pager.setData(this.employeeTblService.queryDataByPage(pager));
		System.err.println(JsonUtils.object2Json(pager));
	}
	
	@Test
	public void testSqlserverForPager(){
		Pager<SiteCompany> pager = new Pager<SiteCompany>();
		pager.setPageIndex(3);
		pager.setPageSize(6);
		pager.getQueryParams().put("value", "11000001");
		pager.setData(this.siteCompanyService.queryBeanDataByPage(pager));
		System.err.println(JsonUtils.object2Json(pager));
	}
	
	@Test
	public void testSqlMapperForPagerBean(){
		Pager<SiteCompany> pager = new Pager<SiteCompany>();
		pager.setPageIndex(3);
		pager.setPageSize(6);
		String sql = "select * from site_vn where site_id = '11000001'";
		System.err.println(JsonUtils.object2Json(this.siteCompanyService.queryPagerBeanBySqlMaper(pager,sql)));
	}
	
	@Test
	public void testSqlMapperForPagerMap(){
		Pager<Map<String,Object>> pager = new Pager<Map<String,Object>>();
		pager.setPageIndex(2);
		pager.setPageSize(8);
		pager.getQueryParams().put("value", "小王");
		String sql = "select * from employee_tbl where name like '小王%'";
		System.err.println(JsonUtils.object2Json(this.employeeTblService.queryPagerMapSqlMapper(pager, sql)));
	}
	
}
