package com.cn.vanke.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cn.vanke.controller.IBaseController;
import com.cn.vanke.entity.EmployeeTbl;
import com.cn.vanke.entity.SiteCompany;
import com.cn.vanke.service.EmployeeTblService;
import com.cn.vanke.service.SiteCompanyService;
import com.cn.vanke.util.JsonUtils;

@RestController
@RequestMapping("/api")
@Api(value = "多数据源查询、更新测试模板",description = "多数据源查询、更新测试模板")
public class TestDataSourceController extends IBaseController {
	private static final Logger logger = LoggerFactory.getLogger(TestDataSourceController.class);
	private static final String DATASOURCE_ARCHIBUS = "archibus";
	private static final String DATASOURCE_FMOBILE = "fmobile";
	
	@Autowired
	private SiteCompanyService siteCompanyService;
	@Autowired
	private EmployeeTblService employeeTblService;
	
	@RequestMapping(value = "/datasource/{type}/{paramsName}/{paramsValue}", method = RequestMethod.POST)
	@ApiOperation(value = "多数据源测试")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "请求参数未填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对"),
            @ApiResponse(code = 500, message = "服务器响应错误")}
    )
	public String query(
			@ApiParam(value = "数据源类型[archibus|fmobile]",required = true) @PathVariable("type") String type,
			@ApiParam(value = "参数名称(用于查询,相当于字段)",required = true) @PathVariable("paramsName") String name,
			@ApiParam(value = "参数值",required = true) @PathVariable("paramsValue") String value) {
		logger.info(">>>>>测试多数据源,数据源为:" + type);
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("queryName", name);
		params.put("queryValue", value);
		if(type.equalsIgnoreCase(DATASOURCE_ARCHIBUS)){
			return JsonUtils.object2Json(siteCompanyService.query(params));
		}else if(type.equalsIgnoreCase(DATASOURCE_FMOBILE)){
			return JsonUtils.object2Json(employeeTblService.query(params));
		}else{
			return "参数有误!!!请重新填写!!!";
		}
	}
	
	@RequestMapping(value = "/datasource/fmobile/insert/object", method = RequestMethod.POST)
	@ApiOperation(value = "插入数据对象到mysql")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "请求参数未填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对"),
            @ApiResponse(code = 500, message = "服务器响应错误")}
    )
	public String insertIntoEmployeeTbl(@ApiParam(value = "员工信息",required = true) @RequestBody EmployeeTbl emloyeeTbl){
		if(emloyeeTbl != null){
			emloyeeTbl.setId(null);
			int result = this.employeeTblService.insertIntoObject(emloyeeTbl);
			if(result == 0){
				return "插入数据成功!!!";
			}
		}
		return "插入数据失败!!!";
	}
	
	@RequestMapping(value = "/datasource/archibus/insert/object", method = RequestMethod.POST)
	@ApiOperation(value = "插入数据对象到archibus")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "请求参数未填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对"),
            @ApiResponse(code = 500, message = "服务器响应错误")}
    )
	public String insertIntoSiteCompany(@ApiParam(value = "项目供应商信息",required = true) @RequestBody SiteCompany siteCompany){
		if(siteCompany != null){
			siteCompany.setId(null);
			int result = this.siteCompanyService.insertIntoObject(siteCompany);
			if(result == 1){
				return "插入数据成功!!!";
			}
		}
		return "插入数据失败!!!";
	}
}
