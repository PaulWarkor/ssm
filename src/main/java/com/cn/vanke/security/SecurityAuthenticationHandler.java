package com.cn.vanke.security;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.cn.vanke.util.CookieUtil;

public class SecurityAuthenticationHandler implements
		AuthenticationSuccessHandler, AuthenticationFailureHandler {

	private static Logger logger = LoggerFactory.getLogger(SecurityAuthenticationHandler.class);

    private final String CHARSET = "UTF-8";

    private String failureRedirectUri;

    private String successRedirectUri;

    private String cookieName;

    /**
     * 认证失败
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param e
     * @throws IOException
     * @throws ServletException
     */
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        //认证失败,跳转至401页面
        String requestServletPath = httpServletRequest.getServletPath();
        String requestContextPath = httpServletRequest.getContextPath();
        CookieUtil.deleteCookie(httpServletRequest, httpServletResponse, getCookieName());
        httpServletResponse.sendRedirect(requestContextPath + failureRedirectUri + "?errorMessage=" + URLEncoder.encode(e.getMessage(), CHARSET));
    }

    /**
     * 认证成功
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //认证成功,跳转至欢迎界面
        String requestServletPath = httpServletRequest.getServletPath();
        String requestContextPath = httpServletRequest.getContextPath();
        String scheme = httpServletRequest.getScheme();

//        String returnUrl = CookieUtil.getCookieValue(httpServletRequest, "return_url");
//
//        CookieUtil.deleteCookie(httpServletRequest, httpServletResponse, "return_url");
//
//        httpServletResponse.sendRedirect(StringUtils.isBlank(returnUrl) ? requestContextPath + successRedirectUri : returnUrl);

        if (logger.isDebugEnabled()) {
            logger.debug("requestServletPath : " + requestServletPath);
        }
        logger.info("login success redirect to the" + " scheme : " + scheme + ", requestContextPath :" + requestContextPath + (StringUtils.isBlank(successRedirectUri) ? "" : " , successRedirectUri : " + successRedirectUri));
        httpServletResponse.sendRedirect(StringUtils.isBlank(successRedirectUri) ? requestContextPath : requestContextPath + successRedirectUri);
    }

    public String getFailureRedirectUri() {
        return failureRedirectUri;
    }

    public void setFailureRedirectUri(String failureRedirectUri) {
        this.failureRedirectUri = failureRedirectUri;
    }

    public String getSuccessRedirectUri() {
        return successRedirectUri;
    }

    public void setSuccessRedirectUri(String successRedirectUri) {
        this.successRedirectUri = successRedirectUri;
    }

    public String getCookieName() {
        return cookieName;
    }

    public void setCookieName(String cookieName) {
        this.cookieName = cookieName;
    }
}
