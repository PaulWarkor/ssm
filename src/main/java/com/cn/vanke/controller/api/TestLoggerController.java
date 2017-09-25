package com.cn.vanke.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cn.vanke.controller.IBaseController;

@RestController
@RequestMapping("/api")
@Api(value = "日志的分级输出测试",description = "日志的分级输出测试")
public class TestLoggerController extends IBaseController {
	private static final Logger logger = LoggerFactory.getLogger(TestLoggerController.class);
	
	@RequestMapping(value = "/logger/level/{info}/{debug}/{error}", method = RequestMethod.POST)
	@ApiOperation(value = "日志的分级输出测试")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "请求参数未填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对"),
            @ApiResponse(code = 500, message = "服务器响应错误")}
    )
	public String loggerLevel(
			@ApiParam(value = "info级别信息",required = true) @PathVariable("info") String info,
			@ApiParam(value = "debug级别信息",required = true) @PathVariable("debug") String debug,
			@ApiParam(value = "error级别信息",required = true) @PathVariable("error") String error){
		logger.info(info);
		logger.debug(debug);
		logger.error(error);
		return "success";
	}
}
