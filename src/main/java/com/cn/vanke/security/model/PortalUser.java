package com.cn.vanke.security.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class PortalUser implements User {

    private String name;

    private String mobilePhone;

    private List<UserJob> jobs;

    private String jobNames;


    @Override
    public String getCode() {
        return null;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getUserId() {
        return null;
    }

    @Override
    public String getAccount() {
        return null;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getMobilePhone() {
        return this.mobilePhone;
    }

    @Override
    public List<Role> getRoles() {
        return null;
    }

    @Override
    public Boolean getState() {
        return null;
    }

    public List<UserJob> getJobs() {
        return jobs;
    }

    public void setJobs(List<UserJob> jobs) {
        this.jobs = jobs;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getJobNames() {
        return jobNames;
    }

    public void setJobNames(String jobNames) {
        this.jobNames = jobNames;
    }

    public PortalUser(String name, String mobilePhone, List<UserJob> jobs) {
        this.name = name;
        this.mobilePhone = mobilePhone;
        this.jobs = jobs;
        String jobName = "";
        for (Iterator<UserJob> iterator = jobs.iterator(); iterator.hasNext();) {
        	jobName += "，" + iterator.next().getRoleName();
		}
        this.jobNames = jobName.substring(1);
    }
}
