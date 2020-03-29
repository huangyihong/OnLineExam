package com.plg.shiro.entity.Vo;

import java.util.Date;

public class OmExamSubmitVo {
    private String submitId;

    private String planId;

    private String planName;

    private String paperId;

    private String paperName;

    private String userId;

    private String realName;

    private String status;

    private Date startTime;

    private Date submitTime;

    private Date markTime;

    private Date publishTime;

    private Integer totalScore;

    private String markUserId;

    private String markUser;

    private Date createTime;

    private Date updateTime;
    
    private Integer userAnswerCount;
    
    private Integer paperCount;

    public String getSubmitId() {
        return submitId;
    }

    public void setSubmitId(String submitId) {
        this.submitId = submitId;
    }

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

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Date getMarkTime() {
        return markTime;
    }

    public void setMarkTime(Date markTime) {
        this.markTime = markTime;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public String getMarkUserId() {
        return markUserId;
    }

    public void setMarkUserId(String markUserId) {
        this.markUserId = markUserId;
    }

    public String getMarkUser() {
        return markUser;
    }

    public void setMarkUser(String markUser) {
        this.markUser = markUser;
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

	public Integer getUserAnswerCount() {
		return userAnswerCount;
	}

	public void setUserAnswerCount(Integer userAnswerCount) {
		this.userAnswerCount = userAnswerCount;
	}

	public Integer getPaperCount() {
		return paperCount;
	}

	public void setPaperCount(Integer paperCount) {
		this.paperCount = paperCount;
	}

}