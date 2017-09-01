package com.cn.vanke.common;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 全局异常处理器
 */
public class GlobalExceptionResolver extends AbstractHandlerExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionResolver.class);

    private static final String ERROR_VIEW = ApplicationConfig.getProperty("config.error.page", "error");

    private static final String CHARACTER_ENCODING = "UTF-8";

    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception exception) {
        return doResolveException(httpServletRequest, httpServletResponse, handler, exception);

    }

    @Override
    protected ModelAndView doResolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception exception) {

        logger.error("catch exception : {}", exception.getMessage());
        logger.error("exception stacktrace : {}", ExceptionUtils.getStackTrace(exception));

        if (null == handler) {
            return null;
        }

        //httpServletResponse.reset();
        httpServletResponse.setCharacterEncoding(CHARACTER_ENCODING);
        httpServletResponse.setContentType(CONTENT_TYPE);

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        //尝试从方法中查找注解
        Boolean hasResponseBody = null == AnnotationUtils.findAnnotation(handlerMethod.getMethod(), ResponseBody.class) ? false : true;
        if (!hasResponseBody) {
            //方法中找不到，则从类中查找
            hasResponseBody = null == AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), RestController.class) ? false : true;
        }

        if (!hasResponseBody) {

            httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

            ModelAndView modelAndView;
            modelAndView = new ModelAndView(ERROR_VIEW);
            modelAndView.addObject("message", exception.getMessage());
            if (logger.isDebugEnabled()) {
                modelAndView.addObject("stackTrace", ExceptionUtils.getStackTrace(exception));
            }
            return modelAndView;

        } else {
            MappingJackson2JsonView mappingJackson2JsonView = new MappingJackson2JsonView();
            mappingJackson2JsonView.setExtractValueFromSingleKeyModel(true);
            if (exception instanceof ParameterErrorException) {
                httpServletResponse.setStatus(HttpStatus.BAD_REQUEST.value());
                List<ParameterError> parameterErrors = new ArrayList<>();
                if (null == ((ParameterErrorException) exception).getErrors() || ((ParameterErrorException) exception).getErrors().size() == 0) {
                    return new ModelAndView(mappingJackson2JsonView).addObject(Result.errorClient(HttpStatus.BAD_REQUEST.toString(), null, exception.getMessage()));
                }
                for (FieldError fieldError : ((ParameterErrorException) exception).getErrors()) {
                    parameterErrors.add(new ParameterError(fieldError));
                }
                return new ModelAndView(mappingJackson2JsonView).addObject(Result.errorClient(HttpStatus.BAD_REQUEST.toString(), parameterErrors, exception.getMessage()));
            }

            if (exception instanceof AccessDeniedException) {
                httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
                return new ModelAndView(mappingJackson2JsonView).addObject(Result.errorClient(HttpStatus.FORBIDDEN.toString(), null, "权限不足"));
            }

            if (exception instanceof ResourceNotFoundException) {
                httpServletResponse.setStatus(HttpStatus.NOT_FOUND.value());
                return new ModelAndView(mappingJackson2JsonView).addObject(Result.errorClient(HttpStatus.NOT_FOUND.toString(), null, exception.getMessage()));
            }

            httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            if (logger.isDebugEnabled()) {
                return new ModelAndView(mappingJackson2JsonView).addObject(Result.errorServer(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "服务器异常", ExceptionUtils.getStackTrace(exception)));
            }
            return new ModelAndView(mappingJackson2JsonView).addObject(Result.errorServer(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "服务器异常", null));
        }
    }
}
