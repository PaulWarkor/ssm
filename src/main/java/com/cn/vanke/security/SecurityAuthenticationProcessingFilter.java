package com.cn.vanke.security;


import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

import com.cn.vanke.security.model.AccessToken;
import com.cn.vanke.security.model.UserInfo;
import com.cn.vanke.security.service.OAuthService;

public class SecurityAuthenticationProcessingFilter extends
		AbstractAuthenticationProcessingFilter {

	protected final Logger logger = LoggerFactory.getLogger(SecurityAuthenticationProcessingFilter.class);

    private OAuthService oAuthService;

    private String cookieName;

    public SecurityAuthenticationProcessingFilter(String path) {
        super(new AntPathRequestMatcher(path, RequestMethod.GET.toString()));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        String stateInParam = httpServletRequest.getParameter("state");
        if (httpServletRequest.getSession() == null || httpServletRequest.getSession().getAttribute("state") == null) {
            logger.error("no relevant session for httpServletRequest state:" + stateInParam);
            throw new BadCredentialsException("session timeout");
        }
        String stateInSession = httpServletRequest.getSession().getAttribute("state").toString();
        if (ObjectUtils.notEqual(stateInParam, stateInSession)) {
            logger.error("state not match[{} , {}]", stateInSession, stateInParam);
            throw new BadCredentialsException("state not match");
        }
        String code = httpServletRequest.getParameter("code");
        if (code == null) {
            throw new BadCredentialsException("code not found");
        }
        Cookie jsessionidCookie = WebUtils.getCookie(httpServletRequest, "JSESSIONID");
        logger.info("Code for [session id={}] is:{}", jsessionidCookie != null ? jsessionidCookie.getValue() : null, code);
        AccessToken accessToken;
        UserInfo userInfo;
        try {
            accessToken = oAuthService.getAccessToken(code);
            if (null == accessToken) {
                throw new BadCredentialsException("accessToken not found");
            }
            userInfo = oAuthService.getUserInfo(accessToken);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new BadCredentialsException(ex.getMessage());
        }
        httpServletRequest.getSession().setAttribute(getCookieName(), accessToken.getAccessToken());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userInfo.getMobile(), "");
        return this.getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Long millis = System.currentTimeMillis();
        super.doFilter(servletRequest, servletResponse, filterChain);
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        millis = System.currentTimeMillis() - millis;
        String userName;
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            userName = userDetails.getUsername();
        } catch (Exception ex) {
            userName = "Anonymous";
        }
        userName += " (IP:" + servletRequest.getRemoteAddr() + ")";
        logger.info("request url: " + httpServletRequest.getRequestURI() + ", result: " + httpServletResponse.getStatus() + ", cost: " + (millis / 1000) + " seconds, " + "user agent : " + httpServletRequest.getHeader("USER-AGENT") + " current user : " + userName);
    }

    public OAuthService getOAuthService() {
        return oAuthService;
    }

    public void setOAuthService(OAuthService oAuthService) {
        this.oAuthService = oAuthService;
    }

    public String getCookieName() {
        return cookieName;
    }

    public void setCookieName(String cookieName) {
        this.cookieName = cookieName;
    }
}
