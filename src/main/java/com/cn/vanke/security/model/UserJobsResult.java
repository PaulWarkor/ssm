package com.cn.vanke.security.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class UserJobsResult {

    /**
     * code
     */
    @JsonProperty(value = "code")
    private String code;

    /**
     * result
     */
    @JsonProperty(value = "result")
    private List<UserJob> userJobs;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<UserJob> getUserJobs() {
        return userJobs;
    }

    public void setUserJobs(List<UserJob> userJobs) {
        this.userJobs = userJobs;
    }
}
