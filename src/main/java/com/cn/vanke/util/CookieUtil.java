package com.cn.vanke.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Cookie操作工具类
 */
public class CookieUtil {

    /**
     * 添加Cookie
     *
     * @param httpServletResponse
     * @param httpServletRequest
     * @param name
     * @param value
     * @param age
     */
    public static void addCookie(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String name, String value, Integer age) {
        String domain = getDomain(httpServletRequest);
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(age);
        cookie.setDomain(domain);
        httpServletResponse.addCookie(cookie);
    }

    public static String getCookieValue(HttpServletRequest httpServletRequest, String cookieName) {
        if (StringUtils.isBlank(cookieName)) {
            return null;
        }
        Cookie cookie = getCookie(httpServletRequest, cookieName);
        return null == cookie ? null : cookie.getValue();
    }

    /**
     * 获取Cookie
     *
     * @param httpServletRequest
     * @param cookieName
     * @return
     */
    public static Cookie getCookie(HttpServletRequest httpServletRequest, String cookieName) {
        if (StringUtils.isBlank(cookieName)) {
            return null;
        }
        if (null == httpServletRequest.getCookies()) {
            return null;
        }
        for (Cookie cookie : httpServletRequest.getCookies()) {
            if (cookieName.equals(cookie.getName())) {
                return cookie;
            }
        }
        return null;
    }

    /**
     * 删除Cookie
     *
     * @param httpServletResponse
     * @param httpServletRequest
     * @param cookieName
     * @return
     */
    public static boolean deleteCookie(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String cookieName) {
        if (StringUtils.isBlank(cookieName)) {
            return false;
        }
        String domain = getDomain(httpServletRequest);
        Cookie cookie = getCookie(httpServletRequest, cookieName);
        if (cookie != null) {
            cookie.setMaxAge(0);//如果0，就说明立即删除
            cookie.setPath("/");//不要漏掉
            cookie.setDomain("." + domain);
            httpServletResponse.addCookie(cookie);
            return true;
        }
        return false;
    }


    /**
     * 清除所有cookie
     *
     * @param httpServletRequest
     * @param httpServletResponse
     */
    public static void clear(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        Cookie[] cookies = httpServletRequest.getCookies();
        for (int i = 0, len = cookies.length; i < len; i++) {
            Cookie localCookie = new Cookie(cookies[i].getName(), null);
            localCookie.setMaxAge(0);
            localCookie.setPath("/");
            localCookie.setDomain(getDomain(httpServletRequest));
            Cookie domainCookie = new Cookie(cookies[i].getName(), null);
            domainCookie.setMaxAge(0);
            domainCookie.setPath("/");
            domainCookie.setDomain("." + getDomain(httpServletRequest));
            httpServletResponse.addCookie(localCookie);
            httpServletResponse.addCookie(domainCookie);
        }
    }

    /**
     * 获取域名
     *
     * @param httpServletRequest
     * @return
     */
    public static String getDomain(HttpServletRequest httpServletRequest) {
        String domainPattern = "(\\w*\\.?){2}\\.(com.cn|net.cn|gov.cn|org\\.nz|org.cn|com|net|org|gov|cc|biz|info|cn|co)$";
        Pattern pattern = Pattern.compile(domainPattern, Pattern.CASE_INSENSITIVE);
        String scheme = httpServletRequest.getScheme();
        String serverName = httpServletRequest.getServerName();
        Matcher matcher = pattern.matcher(serverName.replace(scheme + "//", "").split("/")[0]);
        matcher.find();
        return matcher.group();
    }

}
