package com.cn.vanke.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.cn.vanke.util.CookieUtil;

public class Http403ForbiddenEntryPoint implements AuthenticationEntryPoint {

	private String redirectUri;

    private static final Logger logger = LoggerFactory.getLogger(Http403ForbiddenEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        if (logger.isDebugEnabled()) {
            logger.debug("Request URL : {}", request.getRequestURL());
            logger.debug("Pre-authenticated entry point called. Rejecting access");
        }
        CookieUtil.addCookie(request, response, "return_url", request.getRequestURL().toString(), -1);
        StringBuffer url = request.getRequestURL();
        String contextPath = url.delete(url.length() - request.getRequestURI().length(), url.length()).append(request.getContextPath()).toString();
        if (logger.isDebugEnabled()) {
            logger.debug("Request ContextPath : {}", request.getContextPath());
            logger.debug("ContextPath : {}", contextPath);
        }
        response.sendRedirect(contextPath + redirectUri);
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

}
