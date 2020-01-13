package com.plg.shiro.entity;

import java.util.Date;

public class OmExamAnswer {
    private String answerId;

    private String userId;

    private String planId;

    private String paperId;

    private String questionId;

    private String answerResult;

    private Integer markScore;

    private String markText;

    private Date createTime;

    private Date updateTime;

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getAnswerResult() {
        return answerResult;
    }

    public void setAnswerResult(String answerResult) {
        this.answerResult = answerResult;
    }

    public Integer getMarkScore() {
        return markScore;
    }

    public void setMarkScore(Integer markScore) {
        this.markScore = markScore;
    }

    public String getMarkText() {
        return markText;
    }

    public void setMarkText(String markText) {
        this.markText = markText;
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