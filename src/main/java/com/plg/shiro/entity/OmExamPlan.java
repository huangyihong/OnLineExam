package com.plg.shiro.entity;

import java.util.Date;

public class OmExamPlan {
    private String planId;

    private String planName;

    private String paperId;

    private Integer passingScore;

    private String passingType;

    private Date createTime;

    private Date updateTime;

    private String deleted;

    private String autoMarkFlag;

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public Integer getPassingScore() {
        return passingScore;
    }

    public void setPassingScore(Integer passingScore) {
        this.passingScore = passingScore;
    }

    public String getPassingType() {
        return passingType;
    }

    public void setPassingType(String passingType) {
        this.passingType = passingType;
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

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getAutoMarkFlag() {
        return autoMarkFlag;
    }

    public void setAutoMarkFlag(String autoMarkFlag) {
        this.autoMarkFlag = autoMarkFlag;
    }
}