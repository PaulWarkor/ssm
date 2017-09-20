package com.cn.vanke.security.model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class RoleGroup {

    @JsonProperty("group")
    private String group;

    @JsonProperty("level")
    private Integer level;

    public RoleGroup(String group, Integer level) {
        this.group = group;
        this.level = level;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
