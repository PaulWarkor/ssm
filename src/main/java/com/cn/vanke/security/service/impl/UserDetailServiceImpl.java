package com.cn.vanke.security.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.util.WebUtils;

import com.cn.vanke.security.model.PortalUser;
import com.cn.vanke.security.model.UserInfo;
import com.cn.vanke.security.model.UserJob;
import com.cn.vanke.security.service.OAuthService;

public class UserDetailServiceImpl implements UserDetailsService {


    private static Logger logger = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    @Autowired
    private OAuthService oAuthService;

    @Autowired(required = false)
    private HttpServletRequest httpServletRequest;

    private String cookieName;

    @Override
    public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {

        if (logger.isDebugEnabled()) {
            logger.debug("loadUserByUsername(String) - start");
        }

        if (StringUtils.isBlank(mobile)) {
            throw new UsernameNotFoundException("mobile can not be null");
        }

        List<UserJob> jobs;
        UserInfo userInfo;
        PortalUser portalUser;

        try {
            String accessToken = WebUtils.getSessionAttribute(httpServletRequest, getCookieName()).toString();
            userInfo = oAuthService.getUserInfo(accessToken);
            jobs = distinctJobs(oAuthService.getUserJobs(accessToken));
            portalUser = new PortalUser(userInfo.getFullName(), userInfo.getMobile(), jobs);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UsernameNotFoundException("load the user job fail");
        }

        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        for (Iterator<UserJob> iterator = jobs.iterator(); iterator.hasNext();) {
        	grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + iterator.next()));
		}

        org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(mobile, "", grantedAuthorities);
        WebUtils.setSessionAttribute(httpServletRequest, "portalUser", portalUser);

        if (logger.isDebugEnabled()) {
            logger.debug("loadUserByUsername(String) - end");
        }
        return user;
    }

    /**
     * 去除重复岗位信息
     *
     * @param userJobs
     * @return
     */
    private List<UserJob> distinctJobs(List<UserJob> userJobs) {
        List<UserJob> jobs = new ArrayList<>();
        for (UserJob userJob : userJobs) {
            UserJob job = new UserJob();
            job.setRoleCode(userJob.getRoleCode());
            job.setRoleName(userJob.getRoleName());
            if (Collections.frequency(jobs, job) < 1) {
                jobs.add(job);
            }
        }
        return jobs;
    }

    public String getCookieName() {
        return cookieName;
    }

    public void setCookieName(String cookieName) {
        this.cookieName = cookieName;
    }

}
