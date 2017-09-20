package com.cn.vanke.security.model;

/**
 * 角色信息接口
 */
public interface Role {
    String getCode();

    String getId();

    String getRoleId();

    String getName();

    String getRoleName();

    Boolean getState();

    Integer getLevel();

    String getGroup();
}
