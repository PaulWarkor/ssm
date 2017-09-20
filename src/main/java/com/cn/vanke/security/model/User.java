package com.cn.vanke.security.model;

import java.util.List;

/**
 * 用户信息接口
 */
public interface User {
    String getCode();

    String getId();

    String getUserId();

    String getAccount();

    String getName();

    String getPassword();

    String getMobilePhone();

    List<Role> getRoles();

    Boolean getState();
}
