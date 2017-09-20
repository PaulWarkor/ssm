package com.cn.vanke.security.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 岗位信息
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserJob {

    @JsonProperty("project")
    private String projectName;

    @JsonProperty("can_edit")
    private Boolean canEdit;

    @JsonProperty("manager")
    private Boolean isManager;

    @JsonProperty("role")
    private String roleName;

    @JsonProperty("job_id")
    private String jobId;

    @JsonProperty("system_head")
    private Boolean systemHead;

    @JsonProperty("keeper")
    private Boolean isKeeper;

    @JsonProperty("qr_code")
    private String qrCode;

    @JsonProperty("project_code")
    private String projectCode;

    @JsonProperty("role_code")
    private String roleCode;

    @JsonProperty(value = "battle_map_code")
    private String battleMapCode;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Boolean getCanEdit() {
        return canEdit;
    }

    public void setCanEdit(Boolean canEdit) {
        this.canEdit = canEdit;
    }

    public Boolean getManager() {
        return isManager;
    }

    public void setManager(Boolean manager) {
        isManager = manager;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public Boolean getSystemHead() {
        return systemHead;
    }

    public void setSystemHead(Boolean systemHead) {
        this.systemHead = systemHead;
    }

    public Boolean getKeeper() {
        return isKeeper;
    }

    public void setKeeper(Boolean keeper) {
        isKeeper = keeper;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getBattleMapCode() {
        return battleMapCode;
    }

    public void setBattleMapCode(String battleMapCode) {
        this.battleMapCode = battleMapCode;
    }

    @Override
    public boolean equals(Object obj) {
        return ((UserJob) (obj)).getRoleCode().equals(this.getRoleCode());
    }

}

