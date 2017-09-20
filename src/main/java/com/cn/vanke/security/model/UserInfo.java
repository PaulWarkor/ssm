package com.cn.vanke.security.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;

/**
 * UserInfo
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfo {
    /**
     * nick_name
     */
    @JsonProperty(value = "nickname")
    private String nickName;

    /**
     * created
     */
    @JsonProperty(value = "created")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date created;

    /**
     * updated
     */
    @JsonProperty(value = "updated")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updated;

    /**
     * identity_id
     */
    @JsonProperty(value = "identity_id")
    private String identityId;

    /**
     * is_keeper
     */
    @JsonProperty(value = "is_keeper")
    private String isKeeper;

    /**
     * avatar_url
     */
    @JsonProperty(value = "avatar_url")
    private String avatarUrl;

    /**
     * contact_phones
     */
    @JsonProperty(value = "contact_phones")
    private String[] contactPhones;

    /**
     * state
     */
    @JsonProperty(value = "state")
    private String state;

    /**
     * id
     */
    @JsonProperty(value = "id")
    private String id;

    /**
     * full_name
     */
    @JsonProperty(value = "fullname")
    private String fullName;

    /**
     * sex
     */
    @JsonProperty(value = "sex")
    private String sex;

    /**
     * mobile
     */
    @JsonProperty(value = "mobile")
    private String mobile;

    /**
     * jobCanEdit
     */
    @JsonProperty(value = "job_can_edit")
    private String jobCanEdit;

    /**
     * unitCodes
     */
    @JsonProperty(value = "unit_codes")
    private List<String> unitCodes;

    /**
     * accessToken
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty(value = "access_token")
    private String accessToken;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonIgnore
    private List<UserJob> userJobs;

    @JsonIgnoreProperties(ignoreUnknown = true)
    private List<Project> projects;

    @JsonIgnoreProperties(ignoreUnknown = true)
    private List<Unit> units;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty(value = "role_groups")
    private List<RoleGroup> roleGroups;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty(value = "job_codes")
    private List<String> jobCodes;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty(value = "job_names")
    private List<String> jobNames;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getIdentityId() {
        return identityId;
    }

    public void setIdentityId(String identityId) {
        this.identityId = identityId;
    }

    public String getIsKeeper() {
        return isKeeper;
    }

    public void setIsKeeper(String isKeeper) {
        this.isKeeper = isKeeper;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String[] getContactPhones() {
        return contactPhones;
    }

    public void setContactPhones(String[] contactPhones) {
        this.contactPhones = contactPhones;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getJobCanEdit() {
        return jobCanEdit;
    }

    public void setJobCanEdit(String jobCanEdit) {
        this.jobCanEdit = jobCanEdit;
    }

    public List<String> getUnitCodes() {
        return unitCodes;
    }

    public void setUnitCodes(List<String> unitCodes) {
        this.unitCodes = unitCodes;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public List<UserJob> getUserJobs() {
        return userJobs;
    }

    public void setUserJobs(List<UserJob> userJobs) {
        this.userJobs = userJobs;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<Unit> getUnits() {
        return units;
    }

    public void setUnits(List<Unit> units) {
        this.units = units;
    }

    public List<RoleGroup> getRoleGroups() {
        return roleGroups;
    }

    public void setRoleGroups(List<RoleGroup> roleGroups) {
        this.roleGroups = roleGroups;
    }

    public List<String> getJobCodes() {
        return jobCodes;
    }

    public void setJobCodes(List<String> jobCodes) {
        this.jobCodes = jobCodes;
    }

    public List<String> getJobNames() {
        return jobNames;
    }

    public void setJobNames(List<String> jobNames) {
        this.jobNames = jobNames;
    }
}
