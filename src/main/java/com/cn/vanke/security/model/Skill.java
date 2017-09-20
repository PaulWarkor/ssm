package com.cn.vanke.security.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Skill implements Comparable<Skill> {

    @JsonProperty("skill_code")
    private String code;

    @JsonProperty("skill_name")
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Skill skill) {
        if (this.code.equals(skill.getCode()))
            return 0;
        else
            return 1;
    }

    @Override
    public boolean equals(Object obj) {
        return ((Skill) (obj)).getCode().equals(this.getCode());
    }
}
