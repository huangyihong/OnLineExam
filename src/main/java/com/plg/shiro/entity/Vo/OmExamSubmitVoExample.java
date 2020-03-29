package com.plg.shiro.entity.Vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.plg.shiro.entity.PageExample;

public class OmExamSubmitVoExample extends PageExample{
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OmExamSubmitVoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andSubmitIdIsNull() {
            addCriterion("submit_id is null");
            return (Criteria) this;
        }

        public Criteria andSubmitIdIsNotNull() {
            addCriterion("submit_id is not null");
            return (Criteria) this;
        }

        public Criteria andSubmitIdEqualTo(String value) {
            addCriterion("submit_id =", value, "submitId");
            return (Criteria) this;
        }

        public Criteria andSubmitIdNotEqualTo(String value) {
            addCriterion("submit_id <>", value, "submitId");
            return (Criteria) this;
        }

        public Criteria andSubmitIdGreaterThan(String value) {
            addCriterion("submit_id >", value, "submitId");
            return (Criteria) this;
        }

        public Criteria andSubmitIdGreaterThanOrEqualTo(String value) {
            addCriterion("submit_id >=", value, "submitId");
            return (Criteria) this;
        }

        public Criteria andSubmitIdLessThan(String value) {
            addCriterion("submit_id <", value, "submitId");
            return (Criteria) this;
        }

        public Criteria andSubmitIdLessThanOrEqualTo(String value) {
            addCriterion("submit_id <=", value, "submitId");
            return (Criteria) this;
        }

        public Criteria andSubmitIdLike(String value) {
            addCriterion("submit_id like", value, "submitId");
            return (Criteria) this;
        }

        public Criteria andSubmitIdNotLike(String value) {
            addCriterion("submit_id not like", value, "submitId");
            return (Criteria) this;
        }

        public Criteria andSubmitIdIn(List<String> values) {
            addCriterion("submit_id in", values, "submitId");
            return (Criteria) this;
        }

        public Criteria andSubmitIdNotIn(List<String> values) {
            addCriterion("submit_id not in", values, "submitId");
            return (Criteria) this;
        }

        public Criteria andSubmitIdBetween(String value1, String value2) {
            addCriterion("submit_id between", value1, value2, "submitId");
            return (Criteria) this;
        }

        public Criteria andSubmitIdNotBetween(String value1, String value2) {
            addCriterion("submit_id not between", value1, value2, "submitId");
            return (Criteria) this;
        }

        public Criteria andPlanIdIsNull() {
            addCriterion("plan_id is null");
            return (Criteria) this;
        }

        public Criteria andPlanIdIsNotNull() {
            addCriterion("plan_id is not null");
            return (Criteria) this;
        }

        public Criteria andPlanIdEqualTo(String value) {
            addCriterion("plan_id =", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdNotEqualTo(String value) {
            addCriterion("plan_id <>", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdGreaterThan(String value) {
            addCriterion("plan_id >", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdGreaterThanOrEqualTo(String value) {
            addCriterion("plan_id >=", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdLessThan(String value) {
            addCriterion("plan_id <", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdLessThanOrEqualTo(String value) {
            addCriterion("plan_id <=", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdLike(String value) {
            addCriterion("plan_id like", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdNotLike(String value) {
            addCriterion("plan_id not like", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdIn(List<String> values) {
            addCriterion("plan_id in", values, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdNotIn(List<String> values) {
            addCriterion("plan_id not in", values, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdBetween(String value1, String value2) {
            addCriterion("plan_id between", value1, value2, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdNotBetween(String value1, String value2) {
            addCriterion("plan_id not between", value1, value2, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanNameIsNull() {
            addCriterion("plan_name is null");
            return (Criteria) this;
        }

        public Criteria andPlanNameIsNotNull() {
            addCriterion("plan_name is not null");
            return (Criteria) this;
        }

        public Criteria andPlanNameEqualTo(String value) {
            addCriterion("plan_name =", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameNotEqualTo(String value) {
            addCriterion("plan_name <>", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameGreaterThan(String value) {
            addCriterion("plan_name >", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameGreaterThanOrEqualTo(String value) {
            addCriterion("plan_name >=", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameLessThan(String value) {
            addCriterion("plan_name <", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameLessThanOrEqualTo(String value) {
            addCriterion("plan_name <=", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameLike(String value) {
            addCriterion("plan_name like", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameNotLike(String value) {
            addCriterion("plan_name not like", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameIn(List<String> values) {
            addCriterion("plan_name in", values, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameNotIn(List<String> values) {
            addCriterion("plan_name not in", values, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameBetween(String value1, String value2) {
            addCriterion("plan_name between", value1, value2, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameNotBetween(String value1, String value2) {
            addCriterion("plan_name not between", value1, value2, "planName");
            return (Criteria) this;
        }

        public Criteria andPaperIdIsNull() {
            addCriterion("paper_id is null");
            return (Criteria) this;
        }

        public Criteria andPaperIdIsNotNull() {
            addCriterion("paper_id is not null");
            return (Criteria) this;
        }

        public Criteria andPaperIdEqualTo(String value) {
            addCriterion("paper_id =", value, "paperId");
            return (Criteria) this;
        }

        public Criteria andPaperIdNotEqualTo(String value) {
            addCriterion("paper_id <>", value, "paperId");
            return (Criteria) this;
        }

        public Criteria andPaperIdGreaterThan(String value) {
            addCriterion("paper_id >", value, "paperId");
            return (Criteria) this;
        }

        public Criteria andPaperIdGreaterThanOrEqualTo(String value) {
            addCriterion("paper_id >=", value, "paperId");
            return (Criteria) this;
        }

        public Criteria andPaperIdLessThan(String value) {
            addCriterion("paper_id <", value, "paperId");
            return (Criteria) this;
        }

        public Criteria andPaperIdLessThanOrEqualTo(String value) {
            addCriterion("paper_id <=", value, "paperId");
            return (Criteria) this;
        }

        public Criteria andPaperIdLike(String value) {
            addCriterion("paper_id like", value, "paperId");
            return (Criteria) this;
        }

        public Criteria andPaperIdNotLike(String value) {
            addCriterion("paper_id not like", value, "paperId");
            return (Criteria) this;
        }

        public Criteria andPaperIdIn(List<String> values) {
            addCriterion("paper_id in", values, "paperId");
            return (Criteria) this;
        }

        public Criteria andPaperIdNotIn(List<String> values) {
            addCriterion("paper_id not in", values, "paperId");
            return (Criteria) this;
        }

        public Criteria andPaperIdBetween(String value1, String value2) {
            addCriterion("paper_id between", value1, value2, "paperId");
            return (Criteria) this;
        }

        public Criteria andPaperIdNotBetween(String value1, String value2) {
            addCriterion("paper_id not between", value1, value2, "paperId");
            return (Criteria) this;
        }

        public Criteria andPaperNameIsNull() {
            addCriterion("paper_name is null");
            return (Criteria) this;
        }

        public Criteria andPaperNameIsNotNull() {
            addCriterion("paper_name is not null");
            return (Criteria) this;
        }

        public Criteria andPaperNameEqualTo(String value) {
            addCriterion("paper_name =", value, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameNotEqualTo(String value) {
            addCriterion("paper_name <>", value, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameGreaterThan(String value) {
            addCriterion("paper_name >", value, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameGreaterThanOrEqualTo(String value) {
            addCriterion("paper_name >=", value, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameLessThan(String value) {
            addCriterion("paper_name <", value, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameLessThanOrEqualTo(String value) {
            addCriterion("paper_name <=", value, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameLike(String value) {
            addCriterion("paper_name like", value, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameNotLike(String value) {
            addCriterion("paper_name not like", value, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameIn(List<String> values) {
            addCriterion("paper_name in", values, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameNotIn(List<String> values) {
            addCriterion("paper_name not in", values, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameBetween(String value1, String value2) {
            addCriterion("paper_name between", value1, value2, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameNotBetween(String value1, String value2) {
            addCriterion("paper_name not between", value1, value2, "paperName");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andRealNameIsNull() {
            addCriterion("real_name is null");
            return (Criteria) this;
        }

        public Criteria andRealNameIsNotNull() {
            addCriterion("real_name is not null");
            return (Criteria) this;
        }

        public Criteria andRealNameEqualTo(String value) {
            addCriterion("real_name =", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotEqualTo(String value) {
            addCriterion("real_name <>", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameGreaterThan(String value) {
            addCriterion("real_name >", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameGreaterThanOrEqualTo(String value) {
            addCriterion("real_name >=", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameLessThan(String value) {
            addCriterion("real_name <", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameLessThanOrEqualTo(String value) {
            addCriterion("real_name <=", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameLike(String value) {
            addCriterion("real_name like", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotLike(String value) {
            addCriterion("real_name not like", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameIn(List<String> values) {
            addCriterion("real_name in", values, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotIn(List<String> values) {
            addCriterion("real_name not in", values, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameBetween(String value1, String value2) {
            addCriterion("real_name between", value1, value2, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotBetween(String value1, String value2) {
            addCriterion("real_name not between", value1, value2, "realName");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Date value) {
            addCriterion("start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Date value) {
            addCriterion("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Date value) {
            addCriterion("start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Date value) {
            addCriterion("start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Date> values) {
            addCriterion("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Date> values) {
            addCriterion("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Date value1, Date value2) {
            addCriterion("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("start_time not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeIsNull() {
            addCriterion("submit_time is null");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeIsNotNull() {
            addCriterion("submit_time is not null");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeEqualTo(Date value) {
            addCriterion("submit_time =", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeNotEqualTo(Date value) {
            addCriterion("submit_time <>", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeGreaterThan(Date value) {
            addCriterion("submit_time >", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("submit_time >=", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeLessThan(Date value) {
            addCriterion("submit_time <", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeLessThanOrEqualTo(Date value) {
            addCriterion("submit_time <=", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeIn(List<Date> values) {
            addCriterion("submit_time in", values, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeNotIn(List<Date> values) {
            addCriterion("submit_time not in", values, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeBetween(Date value1, Date value2) {
            addCriterion("submit_time between", value1, value2, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeNotBetween(Date value1, Date value2) {
            addCriterion("submit_time not between", value1, value2, "submitTime");
            return (Criteria) this;
        }

        public Criteria andMarkTimeIsNull() {
            addCriterion("mark_time is null");
            return (Criteria) this;
        }

        public Criteria andMarkTimeIsNotNull() {
            addCriterion("mark_time is not null");
            return (Criteria) this;
        }

        public Criteria andMarkTimeEqualTo(Date value) {
            addCriterion("mark_time =", value, "markTime");
            return (Criteria) this;
        }

        public Criteria andMarkTimeNotEqualTo(Date value) {
            addCriterion("mark_time <>", value, "markTime");
            return (Criteria) this;
        }

        public Criteria andMarkTimeGreaterThan(Date value) {
            addCriterion("mark_time >", value, "markTime");
            return (Criteria) this;
        }

        public Criteria andMarkTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("mark_time >=", value, "markTime");
            return (Criteria) this;
        }

        public Criteria andMarkTimeLessThan(Date value) {
            addCriterion("mark_time <", value, "markTime");
            return (Criteria) this;
        }

        public Criteria andMarkTimeLessThanOrEqualTo(Date value) {
            addCriterion("mark_time <=", value, "markTime");
            return (Criteria) this;
        }

        public Criteria andMarkTimeIn(List<Date> values) {
            addCriterion("mark_time in", values, "markTime");
            return (Criteria) this;
        }

        public Criteria andMarkTimeNotIn(List<Date> values) {
            addCriterion("mark_time not in", values, "markTime");
            return (Criteria) this;
        }

        public Criteria andMarkTimeBetween(Date value1, Date value2) {
            addCriterion("mark_time between", value1, value2, "markTime");
            return (Criteria) this;
        }

        public Criteria andMarkTimeNotBetween(Date value1, Date value2) {
            addCriterion("mark_time not between", value1, value2, "markTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeIsNull() {
            addCriterion("publish_time is null");
            return (Criteria) this;
        }

        public Criteria andPublishTimeIsNotNull() {
            addCriterion("publish_time is not null");
            return (Criteria) this;
        }

        public Criteria andPublishTimeEqualTo(Date value) {
            addCriterion("publish_time =", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeNotEqualTo(Date value) {
            addCriterion("publish_time <>", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeGreaterThan(Date value) {
            addCriterion("publish_time >", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("publish_time >=", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeLessThan(Date value) {
            addCriterion("publish_time <", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeLessThanOrEqualTo(Date value) {
            addCriterion("publish_time <=", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeIn(List<Date> values) {
            addCriterion("publish_time in", values, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeNotIn(List<Date> values) {
            addCriterion("publish_time not in", values, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeBetween(Date value1, Date value2) {
            addCriterion("publish_time between", value1, value2, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeNotBetween(Date value1, Date value2) {
            addCriterion("publish_time not between", value1, value2, "publishTime");
            return (Criteria) this;
        }

        public Criteria andTotalScoreIsNull() {
            addCriterion("total_score is null");
            return (Criteria) this;
        }

        public Criteria andTotalScoreIsNotNull() {
            addCriterion("total_score is not null");
            return (Criteria) this;
        }

        public Criteria andTotalScoreEqualTo(Integer value) {
            addCriterion("total_score =", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreNotEqualTo(Integer value) {
            addCriterion("total_score <>", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreGreaterThan(Integer value) {
            addCriterion("total_score >", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_score >=", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreLessThan(Integer value) {
            addCriterion("total_score <", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreLessThanOrEqualTo(Integer value) {
            addCriterion("total_score <=", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreIn(List<Integer> values) {
            addCriterion("total_score in", values, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreNotIn(List<Integer> values) {
            addCriterion("total_score not in", values, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreBetween(Integer value1, Integer value2) {
            addCriterion("total_score between", value1, value2, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("total_score not between", value1, value2, "totalScore");
            return (Criteria) this;
        }

        public Criteria andMarkUserIdIsNull() {
            addCriterion("mark_user_id is null");
            return (Criteria) this;
        }

        public Criteria andMarkUserIdIsNotNull() {
            addCriterion("mark_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andMarkUserIdEqualTo(String value) {
            addCriterion("mark_user_id =", value, "markUserId");
            return (Criteria) this;
        }

        public Criteria andMarkUserIdNotEqualTo(String value) {
            addCriterion("mark_user_id <>", value, "markUserId");
            return (Criteria) this;
        }

        public Criteria andMarkUserIdGreaterThan(String value) {
            addCriterion("mark_user_id >", value, "markUserId");
            return (Criteria) this;
        }

        public Criteria andMarkUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("mark_user_id >=", value, "markUserId");
            return (Criteria) this;
        }

        public Criteria andMarkUserIdLessThan(String value) {
            addCriterion("mark_user_id <", value, "markUserId");
            return (Criteria) this;
        }

        public Criteria andMarkUserIdLessThanOrEqualTo(String value) {
            addCriterion("mark_user_id <=", value, "markUserId");
            return (Criteria) this;
        }

        public Criteria andMarkUserIdLike(String value) {
            addCriterion("mark_user_id like", value, "markUserId");
            return (Criteria) this;
        }

        public Criteria andMarkUserIdNotLike(String value) {
            addCriterion("mark_user_id not like", value, "markUserId");
            return (Criteria) this;
        }

        public Criteria andMarkUserIdIn(List<String> values) {
            addCriterion("mark_user_id in", values, "markUserId");
            return (Criteria) this;
        }

        public Criteria andMarkUserIdNotIn(List<String> values) {
            addCriterion("mark_user_id not in", values, "markUserId");
            return (Criteria) this;
        }

        public Criteria andMarkUserIdBetween(String value1, String value2) {
            addCriterion("mark_user_id between", value1, value2, "markUserId");
            return (Criteria) this;
        }

        public Criteria andMarkUserIdNotBetween(String value1, String value2) {
            addCriterion("mark_user_id not between", value1, value2, "markUserId");
            return (Criteria) this;
        }

        public Criteria andMarkUserIsNull() {
            addCriterion("mark_user is null");
            return (Criteria) this;
        }

        public Criteria andMarkUserIsNotNull() {
            addCriterion("mark_user is not null");
            return (Criteria) this;
        }

        public Criteria andMarkUserEqualTo(String value) {
            addCriterion("mark_user =", value, "markUser");
            return (Criteria) this;
        }

        public Criteria andMarkUserNotEqualTo(String value) {
            addCriterion("mark_user <>", value, "markUser");
            return (Criteria) this;
        }

        public Criteria andMarkUserGreaterThan(String value) {
            addCriterion("mark_user >", value, "markUser");
            return (Criteria) this;
        }

        public Criteria andMarkUserGreaterThanOrEqualTo(String value) {
            addCriterion("mark_user >=", value, "markUser");
            return (Criteria) this;
        }

        public Criteria andMarkUserLessThan(String value) {
            addCriterion("mark_user <", value, "markUser");
            return (Criteria) this;
        }

        public Criteria andMarkUserLessThanOrEqualTo(String value) {
            addCriterion("mark_user <=", value, "markUser");
            return (Criteria) this;
        }

        public Criteria andMarkUserLike(String value) {
            addCriterion("mark_user like", value, "markUser");
            return (Criteria) this;
        }

        public Criteria andMarkUserNotLike(String value) {
            addCriterion("mark_user not like", value, "markUser");
            return (Criteria) this;
        }

        public Criteria andMarkUserIn(List<String> values) {
            addCriterion("mark_user in", values, "markUser");
            return (Criteria) this;
        }

        public Criteria andMarkUserNotIn(List<String> values) {
            addCriterion("mark_user not in", values, "markUser");
            return (Criteria) this;
        }

        public Criteria andMarkUserBetween(String value1, String value2) {
            addCriterion("mark_user between", value1, value2, "markUser");
            return (Criteria) this;
        }

        public Criteria andMarkUserNotBetween(String value1, String value2) {
            addCriterion("mark_user not between", value1, value2, "markUser");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUserAnswerCountIsNull() {
            addCriterion("user_answer_count is null");
            return (Criteria) this;
        }

        public Criteria andUserAnswerCountIsNotNull() {
            addCriterion("user_answer_count is not null");
            return (Criteria) this;
        }

        public Criteria andUserAnswerCountEqualTo(Integer value) {
            addCriterion("user_answer_count =", value, "userAnswerCount");
            return (Criteria) this;
        }

        public Criteria andUserAnswerCountNotEqualTo(Integer value) {
            addCriterion("user_answer_count <>", value, "userAnswerCount");
            return (Criteria) this;
        }

        public Criteria andUserAnswerCountGreaterThan(Integer value) {
            addCriterion("user_answer_count >", value, "userAnswerCount");
            return (Criteria) this;
        }

        public Criteria andUserAnswerCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_answer_count >=", value, "userAnswerCount");
            return (Criteria) this;
        }

        public Criteria andUserAnswerCountLessThan(Integer value) {
            addCriterion("user_answer_count <", value, "userAnswerCount");
            return (Criteria) this;
        }

        public Criteria andUserAnswerCountLessThanOrEqualTo(Integer value) {
            addCriterion("user_answer_count <=", value, "userAnswerCount");
            return (Criteria) this;
        }

        public Criteria andUserAnswerCountIn(List<Integer> values) {
            addCriterion("user_answer_count in", values, "userAnswerCount");
            return (Criteria) this;
        }

        public Criteria andUserAnswerCountNotIn(List<Integer> values) {
            addCriterion("user_answer_count not in", values, "userAnswerCount");
            return (Criteria) this;
        }

        public Criteria andUserAnswerCountBetween(Integer value1, Integer value2) {
            addCriterion("user_answer_count between", value1, value2, "userAnswerCount");
            return (Criteria) this;
        }

        public Criteria andUserAnswerCountNotBetween(Integer value1, Integer value2) {
            addCriterion("user_answer_count not between", value1, value2, "userAnswerCount");
            return (Criteria) this;
        }

        public Criteria andPaperCountIsNull() {
            addCriterion("paper_count is null");
            return (Criteria) this;
        }

        public Criteria andPaperCountIsNotNull() {
            addCriterion("paper_count is not null");
            return (Criteria) this;
        }

        public Criteria andPaperCountEqualTo(Integer value) {
            addCriterion("paper_count =", value, "paperCount");
            return (Criteria) this;
        }

        public Criteria andPaperCountNotEqualTo(Integer value) {
            addCriterion("paper_count <>", value, "paperCount");
            return (Criteria) this;
        }

        public Criteria andPaperCountGreaterThan(Integer value) {
            addCriterion("paper_count >", value, "paperCount");
            return (Criteria) this;
        }

        public Criteria andPaperCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("paper_count >=", value, "paperCount");
            return (Criteria) this;
        }

        public Criteria andPaperCountLessThan(Integer value) {
            addCriterion("paper_count <", value, "paperCount");
            return (Criteria) this;
        }

        public Criteria andPaperCountLessThanOrEqualTo(Integer value) {
            addCriterion("paper_count <=", value, "paperCount");
            return (Criteria) this;
        }

        public Criteria andPaperCountIn(List<Integer> values) {
            addCriterion("paper_count in", values, "paperCount");
            return (Criteria) this;
        }

        public Criteria andPaperCountNotIn(List<Integer> values) {
            addCriterion("paper_count not in", values, "paperCount");
            return (Criteria) this;
        }

        public Criteria andPaperCountBetween(Integer value1, Integer value2) {
            addCriterion("paper_count between", value1, value2, "paperCount");
            return (Criteria) this;
        }

        public Criteria andPaperCountNotBetween(Integer value1, Integer value2) {
            addCriterion("paper_count not between", value1, value2, "paperCount");
            return (Criteria) this;
        }

    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}