package com.plg.shiro.entity;

import java.util.Date;

public class OmExamPlanVo {
    private String planId;

    private String planName;

    private String paperId;

    private String status;

    private String beginTime;

    private Date createTime;

    private Date updateTime;

    private String paperName;

    private Integer paperScore;

    private Integer paperTime;

    private String addMode;

    private Integer singleCount;

    private Integer multiCount;

    private Integer judgeCount;

    private Integer blankCount;

    private Integer answerCount;

    private String courseId;

    private String courseName;

    private Integer passingScore;

    private String passingType;
    
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
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

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public Integer getPaperScore() {
        return paperScore;
    }

    public void setPaperScore(Integer paperScore) {
        this.paperScore = paperScore;
    }

    public Integer getPaperTime() {
        return paperTime;
    }

    public void setPaperTime(Integer paperTime) {
        this.paperTime = paperTime;
    }

    public String getAddMode() {
        return addMode;
    }

    public void setAddMode(String addMode) {
        this.addMode = addMode;
    }

    public Integer getSingleCount() {
        return singleCount;
    }

    public void setSingleCount(Integer singleCount) {
        this.singleCount = singleCount;
    }

    public Integer getMultiCount() {
        return multiCount;
    }

    public void setMultiCount(Integer multiCount) {
        this.multiCount = multiCount;
    }

    public Integer getJudgeCount() {
        return judgeCount;
    }

    public void setJudgeCount(Integer judgeCount) {
        this.judgeCount = judgeCount;
    }

    public Integer getBlankCount() {
        return blankCount;
    }

    public void setBlankCount(Integer blankCount) {
        this.blankCount = blankCount;
    }

    public Integer getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(Integer answerCount) {
        this.answerCount = answerCount;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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

	public String getAutoMarkFlag() {
		return autoMarkFlag;
	}

	public void setAutoMarkFlag(String autoMarkFlag) {
		this.autoMarkFlag = autoMarkFlag;
	}
    
}