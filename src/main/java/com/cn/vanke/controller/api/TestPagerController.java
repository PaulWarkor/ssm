package com.cn.vanke.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cn.vanke.controller.IBaseController;
import com.cn.vanke.entity.SiteCompany;
import com.cn.vanke.page.domain.Pager;
import com.cn.vanke.service.EmployeeTblService;
import com.cn.vanke.service.SiteCompanyService;

@RestController(value = "/pagerModel")
@Api(value = "分页测试模版",description = "分页测试模版")
public class TestPagerController extends IBaseController{

	@Autowired
	private EmployeeTblService employeeTblService;
	@Autowired
	private SiteCompanyService siteCompanyService;
	
	private static final String DATASOURCE_ARCHIBUS = "archibus";
	private static final String DATASOURCE_FMOBILE = "fmobile";
	
	@RequestMapping(value = "/{dataType}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "mysql分页查询测试",produces="application/json")
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "请求参数未填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对"),
            @ApiResponse(code = 500, message = "服务器响应错误")}
    )
	public Pager<Map<String,Object>> pagerModelFunction(
			@ApiParam(value = "分页信息模板",required = true) @RequestBody Pager<Map<String,Object>> pager,
			@ApiParam(value = "数据库类型[archibus、fmobile]",required = true) @PathVariable("dataType") String dataType){
		Map<String,Object> params = new HashMap<String,Object>();
		pager.setPageSize(5);
		pager.setCurrentPage(pager.getPageIndex());
		pager.setQueryParams(params);
		if(dataType.equalsIgnoreCase(DATASOURCE_ARCHIBUS)){
			params.put("value","11000001");
			pager.setData(this.siteCompanyService.queryDataByPage(pager));
		}else if(dataType.equalsIgnoreCase(DATASOURCE_FMOBILE)){
			params.put("value","小王");
			pager.setData(this.employeeTblService.queryDataByPage(pager));
		}else{
			Map<String,Object> msg = new HashMap<String,Object>();
			msg.put("msg", "数据库类型信息错误！！！");
			pager.setData(Arrays.asList(msg));	
		}
		return pager;
	}
	
	@RequestMapping(value = "/sqlserver", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "自动封装Pager分页查询测试[sqlserver]",produces="application/json")
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "请求参数未填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对"),
            @ApiResponse(code = 500, message = "服务器响应错误")}
    )
	public Pager<SiteCompany> pagerForSqlServer(
			@ApiParam(value = "通过组装ModelAttribute分页,同时返回Bean分页信息[Sqlserver]",required = true) @ModelAttribute  Pager<SiteCompany> pager){
		//由于Pager中的queryParams是通过form表单提交 queryParams[key]=value的方式,此处无法模拟
		if(pager.getQueryParams().isEmpty()){
			pager.getQueryParams().put("value", "11000001");
		} 
		pager.setData(this.siteCompanyService.queryBeanDataByPage(pager));
		return pager;
	}
}
