package com.cn.vanke.security.service;

import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.cn.vanke.common.ApplicationConfig;
import com.cn.vanke.security.model.AccessToken;
import com.cn.vanke.security.model.UserInfo;
import com.cn.vanke.security.model.UserInfoResult;
import com.cn.vanke.security.model.UserJob;
import com.cn.vanke.security.model.UserJobsResult;
import com.cn.vanke.util.JsonMapperUtil;

/**
 * OAuth服务
 */
@Service
public class OAuthService {
	
	private static final String OAUTH_ACCESS_TOKEN_URL = ApplicationConfig.getProperty("Profile.oauth.access_token_url", "http://wyapp.vanke.com/api/zhuzher/oauth/access_token");
    private static final String OAUTH_USER_INFO_URL = ApplicationConfig.getProperty("Profile.oauth.user_info_url", "http://wyapp.vanke.com/api/zhuzher/users/me?access_token=");
    private static final String OAUTH_USER_JOBS_URL = ApplicationConfig.getProperty("Profile.oauth.user_jobs_url", "http://wyapp.vanke.com/api/zhuzher/users/me/jobs?access_token=");
    private static final String OAUTH_CLIENT_ID = ApplicationConfig.getProperty("Profile.oauth.client_id", "xxxxx");
    private static final String OAUTH_CLIENT_SECRET = ApplicationConfig.getProperty("Profile.oauth.client_secret", "xxxxx");
    private static final String OAUTH_ACCESS_TOKEN_GRANT_TYPE = ApplicationConfig.getProperty("Profile.oauth.access_token_grant_type", "authorization_code");
    private static final String OAUTH_REFRESH_TOKEN_GRANT_TYPE = ApplicationConfig.getProperty("Profile.oauth.refresh_token_grant_type", "refresh_token");
    private static final String OAUTH_REDIRECT_URI = ApplicationConfig.getProperty("Profile.oauth.redirect_uri", "http://tvkcrm.vanke.com/oauth/token");
    private static final String OAUTH_REVOKE_URL = ApplicationConfig.getProperty("Profile.oauth.revoke_url", "http://wyapp.vanke.com/api/lebang/oauth/revoke");

    protected final Logger logger = LoggerFactory.getLogger(OAuthService.class);

  /*  private SecurityUserDao dao;

    public SecurityUserDao getDao() {
        return dao;
    }

    public void setDao(SecurityUserDao dao) {
        this.dao = dao;
    }*/

    @Autowired(required = false)
    private HttpServletRequest httpServletRequest;

    /**
     * 根据code获取AccessToken
     *
     * @param code
     * @return
     */
    public AccessToken getAccessToken(String code) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        Date startDate = new Date();
        String exceptionMessage = null;
        AccessToken accessToken = null;
        try {
            accessToken = restTemplate.postForObject(OAUTH_ACCESS_TOKEN_URL, getAccessTokenVariables(code), AccessToken.class);
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            exceptionMessage = StringEscapeUtils.unescapeJava(e.getResponseBodyAsString());
            throw new RuntimeException("OAuth认证服务器认证过程出错,返回异常信息:" + exceptionMessage);
        } catch (HttpServerErrorException e) {
            e.printStackTrace();
            exceptionMessage = StringEscapeUtils.unescapeJava(e.getResponseBodyAsString());
            throw new RuntimeException("OAuth认证服务器认证过程出错,返回异常信息:" + exceptionMessage);
        } finally {
            Date endDate = new Date();
            String userAgent = httpServletRequest.getHeader("User-Agent");
            log2RedisMQ(startDate, endDate, OAUTH_ACCESS_TOKEN_URL, JsonMapperUtil.toJson(getAccessTokenVariables(code)), JsonMapperUtil.toJson(accessToken), "根据code获取AccessToken", userAgent, HttpMethod.POST.toString(), exceptionMessage);
        }
        return accessToken;
    }

    /**
     * 根据RefreshToken刷新AccessToken
     *
     * @param refreshToken
     * @return
     */
    public AccessToken refreshAccessToken(String refreshToken) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        Date startDate = new Date();
        String exceptionMessage = null;
        AccessToken accessToken = null;
        try {
            accessToken = restTemplate.postForObject(OAUTH_ACCESS_TOKEN_URL, getRefreshTokenVariables(refreshToken), AccessToken.class);
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            exceptionMessage = StringEscapeUtils.unescapeJava(e.getResponseBodyAsString());
            throw new RuntimeException("OAuth认证服务器认证过程出错,返回异常信息:" + exceptionMessage);
        } catch (HttpServerErrorException e) {
            e.printStackTrace();
            exceptionMessage = StringEscapeUtils.unescapeJava(e.getResponseBodyAsString());
            throw new RuntimeException("OAuth认证服务器认证过程出错,返回异常信息:" + exceptionMessage);
        } finally {
            Date endDate = new Date();
            String userAgent = httpServletRequest.getHeader("User-Agent");
            log2RedisMQ(startDate, endDate, OAUTH_ACCESS_TOKEN_URL, JsonMapperUtil.toJson(getRefreshTokenVariables(refreshToken)), JsonMapperUtil.toJson(accessToken), "根据RefreshToken刷新AccessToken", userAgent, HttpMethod.POST.toString(), exceptionMessage);
        }
        return accessToken;
    }

    /**
     * 根据AccessToken获取用户信息
     *
     * @param accessToken
     * @return
     */
    public UserInfo getUserInfo(AccessToken accessToken) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        Date startDate = new Date();
        String exceptionMessage = null;
        UserInfoResult result = null;
        try {
            result = restTemplate.getForObject(getUserInfoUrl(accessToken.getAccessToken()), UserInfoResult.class);
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            exceptionMessage = StringEscapeUtils.unescapeJava(e.getResponseBodyAsString());
            throw new RuntimeException("OAuth认证服务器认证过程出错,返回异常信息:" + exceptionMessage);
        } catch (HttpServerErrorException e) {
            e.printStackTrace();
            exceptionMessage = StringEscapeUtils.unescapeJava(e.getResponseBodyAsString());
            throw new RuntimeException("OAuth认证服务器认证过程出错,返回异常信息:" + exceptionMessage);
        } finally {
            Date endDate = new Date();
            String userAgent = httpServletRequest.getHeader("User-Agent");
            log2RedisMQ(startDate, endDate, getUserInfoUrl(accessToken.getAccessToken()), "", JsonMapperUtil.toJson(result), "根据AccessToken获取用户信息", userAgent, HttpMethod.GET.toString(), exceptionMessage);
        }
        return null == result ? null : result.getUserInfo();
    }

    /**
     * 根据AccessToken获取用户信息
     *
     * @param accessToken
     * @return
     */
    public UserInfo getUserInfo(String accessToken) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        Date startDate = new Date();
        String exceptionMessage = null;
        UserInfoResult result = null;
        try {
            result = restTemplate.getForObject(getUserInfoUrl(accessToken), UserInfoResult.class);
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            exceptionMessage = StringEscapeUtils.unescapeJava(e.getResponseBodyAsString());
            throw new RuntimeException("OAuth认证服务器认证过程出错,返回异常信息:" + exceptionMessage);
        } catch (HttpServerErrorException e) {
            e.printStackTrace();
            exceptionMessage = StringEscapeUtils.unescapeJava(e.getResponseBodyAsString());
            throw new RuntimeException("OAuth认证服务器认证过程出错,返回异常信息:" + exceptionMessage);
        } finally {
            Date endDate = new Date();
            String userAgent = httpServletRequest.getHeader("User-Agent");
            log2RedisMQ(startDate, endDate, getUserInfoUrl(accessToken), "", JsonMapperUtil.toJson(result), "根据AccessToken获取用户信息", userAgent, HttpMethod.GET.toString(), exceptionMessage);
        }
        return null == result ? null : result.getUserInfo();
    }

    /**
     * 处理客户端异常
     *
     * @param accessToken
     * @param errorMessage
     * @return
     */
    @SuppressWarnings({ "unchecked", "unused" })
    private UserInfo handleError(final String accessToken, final String errorMessage) {
        try {
			Map<String, String> errorMap = JsonMapperUtil.fromJson(errorMessage, Map.class);
            if ("101".equals(errorMap.get("code"))) {
                String referToken = refreshAccessToken(accessToken).getAccessToken();
                UserInfo userInfo = getUserInfo(referToken);
                return userInfo;
            }
            if ("123".equals(errorMap.get("code"))) {
                throw new RuntimeException("OAuth认证服务器认证过程出错,返回异常信息:" + errorMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        throw new RuntimeException(errorMessage);
    }

    /**
     * 根据AccessToken获取用户岗位
     *
     * @param accessToken
     * @return
     */
    public List<UserJob> getUserJobs(String accessToken) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        Date startDate = new Date();
        String exceptionMessage = null;
        UserJobsResult result = null;
        try {
            result = restTemplate.getForObject(getUserJobsUrl(accessToken), UserJobsResult.class);
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            exceptionMessage = StringEscapeUtils.unescapeJava(e.getResponseBodyAsString());
            throw new RuntimeException("OAuth认证服务器认证过程出错,返回异常信息:" + exceptionMessage);
        } catch (HttpServerErrorException e) {
            e.printStackTrace();
            exceptionMessage = StringEscapeUtils.unescapeJava(e.getResponseBodyAsString());
            throw new RuntimeException("OAuth认证服务器认证过程出错,返回异常信息:" + exceptionMessage);
        } finally {
            Date endDate = new Date();
            String userAgent = httpServletRequest.getHeader("User-Agent");
            log2RedisMQ(startDate, endDate, getUserInfoUrl(accessToken), "", JsonMapperUtil.toJson(result), "根据AccessToken获取岗位信息", userAgent, HttpMethod.GET.toString(), exceptionMessage);
        }
        return null == result ? null : result.getUserJobs();
    }

    /**
     * 根据AccessToken注销系统
     *
     * @param accessToken
     * @return
     * @throws Exception
     */
    public String logout(String accessToken) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        Date startDate = new Date();
        String exceptionMessage = null;
        ResponseEntity<String> result = null;
        logger.debug("accessToken：" + accessToken);
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("token", accessToken);
        try {
            result = restTemplate.postForEntity(OAUTH_REVOKE_URL, parameters, String.class);
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            exceptionMessage = StringEscapeUtils.unescapeJava(e.getResponseBodyAsString());
            throw new RuntimeException("OAuth认证服务器认证过程出错,返回异常信息:" + exceptionMessage);
        } catch (HttpServerErrorException e) {
            e.printStackTrace();
            exceptionMessage = StringEscapeUtils.unescapeJava(e.getResponseBodyAsString());
            throw new RuntimeException("OAuth认证服务器认证过程出错,返回异常信息:" + exceptionMessage);
        } finally {
            Date endDate = new Date();
            String userAgent = httpServletRequest.getHeader("User-Agent");
            log2RedisMQ(startDate, endDate, OAUTH_REVOKE_URL, JsonMapperUtil.toJson(parameters), JsonMapperUtil.toJson(result), "根据AccessToken注销系统", userAgent, HttpMethod.POST.toString(), exceptionMessage);
        }
        return null == result ? "" : null == result.getBody() ? "" : result.getBody();
    }

    /**
     * 构建获取AccessToken的参数列表
     *
     * @param code
     * @return
     */
    private Map<String, String> getAccessTokenVariables(String code) {
        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("client_id", OAUTH_CLIENT_ID);
        paramsMap.put("client_secret", OAUTH_CLIENT_SECRET);
        paramsMap.put("code", code);
        paramsMap.put("grant_type", OAUTH_ACCESS_TOKEN_GRANT_TYPE);
        paramsMap.put("redirect_uri", OAUTH_REDIRECT_URI);
        return paramsMap;
    }

    /**
     * 构建刷新AccessToken的参数列表
     *
     * @param refreshToken
     * @return
     */
    private Map<String, String> getRefreshTokenVariables(String refreshToken) {
        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("client_id", OAUTH_CLIENT_ID);
        paramsMap.put("client_secret", OAUTH_CLIENT_SECRET);
        paramsMap.put("grant_type", OAUTH_REFRESH_TOKEN_GRANT_TYPE);
        paramsMap.put("refresh_token", refreshToken);
        paramsMap.put("redirect_uri", OAUTH_REDIRECT_URI);
        return paramsMap;
    }

    /**
     * 构建获取用户信息的URL
     *
     * @param accessToken
     * @return
     */
    private String getUserInfoUrl(String accessToken) {
        StringBuffer stringBuffer = new StringBuffer().append(OAUTH_USER_INFO_URL).append(URLEncoder.encode(accessToken));
        return stringBuffer.toString();
    }

    /**
     * 构建获取用户岗位的URL
     *
     * @param accessToken
     * @return
     */
    private String getUserJobsUrl(String accessToken) {
        StringBuffer stringBuffer = new StringBuffer().append(OAUTH_USER_JOBS_URL).append(URLEncoder.encode(accessToken));
        return stringBuffer.toString();
    }

    /**
     * 存入Redis消息队列
     *
     * @param requestDate      请求时间
     * @param responseDate     响应时间
     * @param remoteUrl        访问地址
     * @param parameters       参数
     * @param result           结果
     * @param actionName       动作
     * @param requestMethod    请求类型
     * @param exceptionMessage 异常信息
     */
    private void log2RedisMQ(Date requestDate, Date responseDate, String remoteUrl, String parameters, String result, String actionName, String userAgent, String requestMethod, String exceptionMessage) {

    }	
}
