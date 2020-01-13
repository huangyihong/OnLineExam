package com.plg.shiro.entity;

public class OmPaperQuestion {
    private String paperQuestionId;

    private String paperId;

    private String questionId;

    private Integer questionOrder;

    private String userId;

    private Integer questionScore;

    public String getPaperQuestionId() {
        return paperQuestionId;
    }

    public void setPaperQuestionId(String paperQuestionId) {
        this.paperQuestionId = paperQuestionId;
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

    public Integer getQuestionOrder() {
        return questionOrder;
    }

    public void setQuestionOrder(Integer questionOrder) {
        this.questionOrder = questionOrder;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getQuestionScore() {
        return questionScore;
    }

    public void setQuestionScore(Integer questionScore) {
        this.questionScore = questionScore;
    }
}