package com.cn.vanke.security;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.cn.vanke.security.model.Project;
import com.cn.vanke.security.model.UserInfo;
/**
 * Spring安全上下文
 */
public class SimpleSecurityContext implements SecurityContext {

	public static String getAccessToken() {
        return null == SecurityContextHolder.getContext().getAuthentication() ? "" : ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

    public static String getUserId() {
        return null == SecurityContextHolder.getContext().getAuthentication() ? "" : ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

    public static List<Project> getProjects() {
        return null;
    }

    private final static ThreadLocal<UserInfo> threadLocal = new ThreadLocal<UserInfo>();

    public static UserInfo get() {
        return threadLocal.get();
    }

    public static void set(UserInfo userInfo) {
        threadLocal.set(userInfo);
    }

    public static void clear() {
        threadLocal.remove();
    }
}
