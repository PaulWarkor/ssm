package com.cn.vanke.security.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Job {

    @JsonProperty("job_code")
    private String code;

    @JsonProperty("job_name")
    private String name;

    private List<Skill> skills;

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

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}
