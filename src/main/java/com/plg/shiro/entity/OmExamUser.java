package com.plg.shiro.entity;

import java.util.Date;

public class OmExamUser {
    private String examUserId;

    private String planId;

    private String paperId;

    private String userId;

    private String groupId;

    private String examUserType;

    private Date createTime;

    private Date updateTime;

    public String getExamUserId() {
        return examUserId;
    }

    public void setExamUserId(String examUserId) {
        this.examUserId = examUserId;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getExamUserType() {
        return examUserType;
    }

    public void setExamUserType(String examUserType) {
        this.examUserType = examUserType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}