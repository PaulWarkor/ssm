package com.cn.vanke.security;

import java.util.Collection;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * Spring security 安全信息投票器（判断当前用户角色是否具备当前Url的访问权限）
 */
public class SecurityAccessDecisionManager implements AccessDecisionManager {

	private static final Logger logger = LoggerFactory.getLogger(SecurityAccessDecisionManager.class);

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if (logger.isDebugEnabled()) {
            logger.debug("decide(Authentication, Object, Collection<ConfigAttribute>) - start");
        }
        if (null == configAttributes) {
            if (logger.isDebugEnabled()) {
                logger.debug("decide(Authentication, Object, Collection<ConfigAttribute>) - end");
            }
            return;
        }
        if (logger.isDebugEnabled()) {
            logger.debug("request url : {}：", object.toString());
        }
        Iterator<ConfigAttribute> iterator = configAttributes.iterator();
        while (iterator.hasNext()) {
            ConfigAttribute configAttribute = iterator.next();
            if (logger.isDebugEnabled()) {
                logger.debug("need role : {}", configAttribute.getAttribute());
            }
            String needRole = ((SecurityConfig) configAttribute).getAttribute();
            for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
                if (logger.isDebugEnabled()) {
                    logger.debug("user role : {}", grantedAuthority.getAuthority());
                }
                if (needRole.equals(grantedAuthority.getAuthority())) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("need role : {} user role : {} matched ", needRole, grantedAuthority.getAuthority());
                        logger.debug("decide(Authentication, Object, Collection<ConfigAttribute>) - end");
                    }
                    return;
                }
            }
        }
        throw new AccessDeniedException("access denied");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

}
