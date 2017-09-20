package com.cn.vanke.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.web.util.WebUtils;

import com.cn.vanke.security.model.UserInfo;
import com.cn.vanke.util.CookieUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecurityRememberMeService implements RememberMeServices {

	protected final Logger logger = LoggerFactory.getLogger(SecurityRememberMeService.class);

    private OAuthService oAuthService;

    private AuthenticationManager authenticationManager;

    private String cookieName;

    /**
     * 自动登录
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    public Authentication autoLogin(HttpServletRequest request, HttpServletResponse response) {
        if (logger.isDebugEnabled()) {
            logger.debug("autoLogin(HttpServletRequest,HttpServletResponse) - start");
        }
        Cookie cookieValueInCookie = WebUtils.getCookie(request, getCookieName());
        UserInfo userInfo;
        Authentication authentication = null;
        try {

            if (null == cookieValueInCookie) {
                throw new BadCredentialsException(getCookieName() + " not found");
            }

            String accessToken = cookieValueInCookie.getValue();

            logger.info("catch the {} cookie : {} , load the oauth user", getCookieName(), accessToken);

            userInfo = oAuthService.getUserInfo(accessToken);

            logger.info("the oauth user is not empty ,user mobile : {}, load the security user to spring security context", userInfo.getMobile());

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userInfo.getMobile(), "");

            authentication = usernamePasswordAuthenticationToken;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (logger.isDebugEnabled()) {
            logger.debug("autoLogin(HttpServletRequest,HttpServletResponse) - end");
        }
        return authentication;
    }

    /**
     * 处理登录失败
     *
     * @param request
     * @param response
     */
    @Override
    public void loginFail(HttpServletRequest request, HttpServletResponse response) {
        if (logger.isDebugEnabled()) {
            logger.debug("loginFail(HttpServletRequest,HttpServletResponse) - start");
        }

        CookieUtil.deleteCookie(request, response, getCookieName());

        logger.debug("delete the cookie : {}", getCookieName());

        if (logger.isDebugEnabled()) {
            logger.debug("loginFail(HttpServletRequest,HttpServletResponse) - end");
        }
    }

    /**
     * 处理登录成功
     *
     * @param request
     * @param response
     * @param successfulAuthentication
     */
    @Override
    public void loginSuccess(HttpServletRequest request, HttpServletResponse response, Authentication successfulAuthentication) {
        if (logger.isDebugEnabled()) {
            logger.debug("loginSuccess(HttpServletRequest,HttpServletResponse,Authentication) - start");
        }

        String scheme = request.getScheme();
        String serverName = request.getServerName();
        String domain = serverName.replace(scheme + "//", "");
        StringBuffer url = request.getRequestURL();
        String contextPath = url.delete(url.length() - request.getRequestURI().length(), url.length()).append(request.getContextPath()).toString();

        String accessTokenInSession = WebUtils.getSessionAttribute(request, getCookieName()).toString();

        CookieUtil.deleteCookie(request, response, getCookieName());

        CookieUtil.addCookie(request, response, getCookieName(), accessTokenInSession, -1);

        if (logger.isDebugEnabled()) {
            logger.debug("set cookie : {} {} to scheme : {} serverName : {} contextPath : {} domain : {}", getCookieName(), accessTokenInSession, scheme, serverName, contextPath, domain);
            logger.debug("loginSuccess(HttpServletRequest,HttpServletResponse,Authentication) - end");
        }
    }

    public OAuthService getOAuthService() {
        return oAuthService;
    }

    public void setOAuthService(OAuthService oAuthService) {
        this.oAuthService = oAuthService;
    }

    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public String getCookieName() {
        return cookieName;
    }

    public void setCookieName(String cookieName) {
        this.cookieName = cookieName;
    }
}
