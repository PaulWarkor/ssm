package com.cn.vanke.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cn.vanke.controller.IBaseController;
import com.cn.vanke.entity.EntityTemplate;
import com.cn.vanke.util.DateUtils;

@RestController
@RequestMapping("/api")
@Api(value = "Api测试模版",description = "Api测试模版")
public class TestSwaggeController extends IBaseController{

	@RequestMapping(value = "/{p_Code}/{format_Date}/{p_Name}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "参数[字符和时间]测试",produces="application/json")
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "请求参数未填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对"),
            @ApiResponse(code = 500, message = "服务器响应错误")}
    )
	public String testApiForString(
			@ApiParam(value = "信息模板",required = true) @RequestBody EntityTemplate entityTemplate,
			@ApiParam(value = "项目编码",required = true) @PathVariable("p_Code") String projectCode,
			@ApiParam(value = "项目名称",required = true) @PathVariable("p_Name") String projectName,
			@ApiParam(value = "日期(yyyy-MM-dd)",required = true) @PathVariable("format_Date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date formatDate) {
		String model = "编码格式 : " + super.getRequest().getCharacterEncoding().toString() + "\n";
		model += "信息模版  : " + entityTemplate.toString() + "\n";
		model += "项目编码  : " + projectCode;
		model += "\n";
		model += "项目名称  : " + projectName;
		model += "\n";
		model += "时间日期  : " + DateUtils.formatToString(DateUtils.TO_SECOND, formatDate);
		return model;
	}
}
