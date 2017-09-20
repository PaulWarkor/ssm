package com.cn.vanke.security.service;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cn.vanke.common.ApplicationConfig;
public class OAuthConfigService {
	public static final String LOCAL_LOGIN = "local";
    public static final String OAUTH_LOGIN = "oauth";
    private static String LOCAL_LOGIN_URL = "/login.jsp";
    private static final String OAUTH_LOGIN_URL = ApplicationConfig.getProperty("Profile.oauth.url", "http://wyapp.vanke.com/api/zhuzher/oauth/authorize");
    public static final String OAUTH_SCOPES = ApplicationConfig.getProperty("Profile.oauth.scopes", "r-user rp-user");
    public static final String OAUTH_REDIRECT_URI = ApplicationConfig.getProperty("Profile.oauth.redirect_uri", "http://tvkcrm.vanke.com/oauth/token");
    public static final String OAUTH_RESPONSE_TYPE = ApplicationConfig.getProperty("Profile.oauth.response_type", "code");
    private static final String OAUTH_CLIENT_ID = ApplicationConfig.getProperty("Profile.oauth.client_id", "xxxxx");
    private static final String OAUTH_CLIENT_SECRET = ApplicationConfig.getProperty("Profile.oauth.client_secret", "xxxxx");
    private final int EXPIRE_TIME = Integer.parseInt(ApplicationConfig.getProperty("Profile.token.cookie_expire", "1800"));
    private final String OAUTH_PORTAL_URL = ApplicationConfig.getProperty("Profile.oauth.portal_url", "");
    private final String OAUTH_CODE_KEY = ApplicationConfig.getProperty("Profile.oauth.code.key", "code");
    private final String AES_KEY = ApplicationConfig.getProperty("Profile.oauth.aes_key", "0102030405060708");
    private final String COOKIE_ACCESS_TOKEN_NAME = ApplicationConfig.getProperty("Profile.token.cookie_key", "access_token");
    private final String ERROR_PAGE_URL = ApplicationConfig.getProperty("Profile.error.page_url", "/error");
    private final String ENCODING_CHARSET = "UTF-8";

    private static Logger logger = LoggerFactory.getLogger(OAuthConfigService.class);


    /**
     * 构建重定向URL
     *
     * @param request
     * @param requestUrl
     * @return
     */
    public String getRedirectUrl(HttpServletRequest request, String requestUrl, String state) throws Exception {
        String redirectUrl;
        redirectUrl = new StringBuffer().append(OAUTH_LOGIN_URL).append("?scopes=")
                .append(URLEncoder.encode(OAUTH_SCOPES, ENCODING_CHARSET))
                //.append("&state=").append(URLEncoder.encode(state, ENCODING_CHARSET))
                .append("&redirect_uri=").append(URLEncoder.encode(OAUTH_REDIRECT_URI, ENCODING_CHARSET))
                .append("&response_type=").append(URLEncoder.encode(OAUTH_RESPONSE_TYPE, ENCODING_CHARSET))
                .append("&client_id=").append(URLEncoder.encode(OAUTH_CLIENT_ID, ENCODING_CHARSET))
                .append("&sign=n").toString();

        return redirectUrl;
    }
}
