package com.cn.vanke.security.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 管理中心
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Unit extends Project {

    @Override
    public boolean equals(Object obj) {
        return ((Unit) (obj)).getCode().equals(this.getCode());
    }

}
