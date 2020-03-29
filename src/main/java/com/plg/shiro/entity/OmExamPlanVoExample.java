package com.plg.shiro.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OmExamPlanVoExample extends PageExample{
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OmExamPlanVoExample() {
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

        public Criteria andPaperScoreIsNull() {
            addCriterion("paper_score is null");
            return (Criteria) this;
        }

        public Criteria andPaperScoreIsNotNull() {
            addCriterion("paper_score is not null");
            return (Criteria) this;
        }

        public Criteria andPaperScoreEqualTo(Integer value) {
            addCriterion("paper_score =", value, "paperScore");
            return (Criteria) this;
        }

        public Criteria andPaperScoreNotEqualTo(Integer value) {
            addCriterion("paper_score <>", value, "paperScore");
            return (Criteria) this;
        }

        public Criteria andPaperScoreGreaterThan(Integer value) {
            addCriterion("paper_score >", value, "paperScore");
            return (Criteria) this;
        }

        public Criteria andPaperScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("paper_score >=", value, "paperScore");
            return (Criteria) this;
        }

        public Criteria andPaperScoreLessThan(Integer value) {
            addCriterion("paper_score <", value, "paperScore");
            return (Criteria) this;
        }

        public Criteria andPaperScoreLessThanOrEqualTo(Integer value) {
            addCriterion("paper_score <=", value, "paperScore");
            return (Criteria) this;
        }

        public Criteria andPaperScoreIn(List<Integer> values) {
            addCriterion("paper_score in", values, "paperScore");
            return (Criteria) this;
        }

        public Criteria andPaperScoreNotIn(List<Integer> values) {
            addCriterion("paper_score not in", values, "paperScore");
            return (Criteria) this;
        }

        public Criteria andPaperScoreBetween(Integer value1, Integer value2) {
            addCriterion("paper_score between", value1, value2, "paperScore");
            return (Criteria) this;
        }

        public Criteria andPaperScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("paper_score not between", value1, value2, "paperScore");
            return (Criteria) this;
        }

        public Criteria andPaperTimeIsNull() {
            addCriterion("paper_time is null");
            return (Criteria) this;
        }

        public Criteria andPaperTimeIsNotNull() {
            addCriterion("paper_time is not null");
            return (Criteria) this;
        }

        public Criteria andPaperTimeEqualTo(Integer value) {
            addCriterion("paper_time =", value, "paperTime");
            return (Criteria) this;
        }

        public Criteria andPaperTimeNotEqualTo(Integer value) {
            addCriterion("paper_time <>", value, "paperTime");
            return (Criteria) this;
        }

        public Criteria andPaperTimeGreaterThan(Integer value) {
            addCriterion("paper_time >", value, "paperTime");
            return (Criteria) this;
        }

        public Criteria andPaperTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("paper_time >=", value, "paperTime");
            return (Criteria) this;
        }

        public Criteria andPaperTimeLessThan(Integer value) {
            addCriterion("paper_time <", value, "paperTime");
            return (Criteria) this;
        }

        public Criteria andPaperTimeLessThanOrEqualTo(Integer value) {
            addCriterion("paper_time <=", value, "paperTime");
            return (Criteria) this;
        }

        public Criteria andPaperTimeIn(List<Integer> values) {
            addCriterion("paper_time in", values, "paperTime");
            return (Criteria) this;
        }

        public Criteria andPaperTimeNotIn(List<Integer> values) {
            addCriterion("paper_time not in", values, "paperTime");
            return (Criteria) this;
        }

        public Criteria andPaperTimeBetween(Integer value1, Integer value2) {
            addCriterion("paper_time between", value1, value2, "paperTime");
            return (Criteria) this;
        }

        public Criteria andPaperTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("paper_time not between", value1, value2, "paperTime");
            return (Criteria) this;
        }

        public Criteria andAddModeIsNull() {
            addCriterion("add_mode is null");
            return (Criteria) this;
        }

        public Criteria andAddModeIsNotNull() {
            addCriterion("add_mode is not null");
            return (Criteria) this;
        }

        public Criteria andAddModeEqualTo(String value) {
            addCriterion("add_mode =", value, "addMode");
            return (Criteria) this;
        }

        public Criteria andAddModeNotEqualTo(String value) {
            addCriterion("add_mode <>", value, "addMode");
            return (Criteria) this;
        }

        public Criteria andAddModeGreaterThan(String value) {
            addCriterion("add_mode >", value, "addMode");
            return (Criteria) this;
        }

        public Criteria andAddModeGreaterThanOrEqualTo(String value) {
            addCriterion("add_mode >=", value, "addMode");
            return (Criteria) this;
        }

        public Criteria andAddModeLessThan(String value) {
            addCriterion("add_mode <", value, "addMode");
            return (Criteria) this;
        }

        public Criteria andAddModeLessThanOrEqualTo(String value) {
            addCriterion("add_mode <=", value, "addMode");
            return (Criteria) this;
        }

        public Criteria andAddModeLike(String value) {
            addCriterion("add_mode like", value, "addMode");
            return (Criteria) this;
        }

        public Criteria andAddModeNotLike(String value) {
            addCriterion("add_mode not like", value, "addMode");
            return (Criteria) this;
        }

        public Criteria andAddModeIn(List<String> values) {
            addCriterion("add_mode in", values, "addMode");
            return (Criteria) this;
        }

        public Criteria andAddModeNotIn(List<String> values) {
            addCriterion("add_mode not in", values, "addMode");
            return (Criteria) this;
        }

        public Criteria andAddModeBetween(String value1, String value2) {
            addCriterion("add_mode between", value1, value2, "addMode");
            return (Criteria) this;
        }

        public Criteria andAddModeNotBetween(String value1, String value2) {
            addCriterion("add_mode not between", value1, value2, "addMode");
            return (Criteria) this;
        }

        public Criteria andSingleCountIsNull() {
            addCriterion("single_count is null");
            return (Criteria) this;
        }

        public Criteria andSingleCountIsNotNull() {
            addCriterion("single_count is not null");
            return (Criteria) this;
        }

        public Criteria andSingleCountEqualTo(Integer value) {
            addCriterion("single_count =", value, "singleCount");
            return (Criteria) this;
        }

        public Criteria andSingleCountNotEqualTo(Integer value) {
            addCriterion("single_count <>", value, "singleCount");
            return (Criteria) this;
        }

        public Criteria andSingleCountGreaterThan(Integer value) {
            addCriterion("single_count >", value, "singleCount");
            return (Criteria) this;
        }

        public Criteria andSingleCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("single_count >=", value, "singleCount");
            return (Criteria) this;
        }

        public Criteria andSingleCountLessThan(Integer value) {
            addCriterion("single_count <", value, "singleCount");
            return (Criteria) this;
        }

        public Criteria andSingleCountLessThanOrEqualTo(Integer value) {
            addCriterion("single_count <=", value, "singleCount");
            return (Criteria) this;
        }

        public Criteria andSingleCountIn(List<Integer> values) {
            addCriterion("single_count in", values, "singleCount");
            return (Criteria) this;
        }

        public Criteria andSingleCountNotIn(List<Integer> values) {
            addCriterion("single_count not in", values, "singleCount");
            return (Criteria) this;
        }

        public Criteria andSingleCountBetween(Integer value1, Integer value2) {
            addCriterion("single_count between", value1, value2, "singleCount");
            return (Criteria) this;
        }

        public Criteria andSingleCountNotBetween(Integer value1, Integer value2) {
            addCriterion("single_count not between", value1, value2, "singleCount");
            return (Criteria) this;
        }

        public Criteria andMultiCountIsNull() {
            addCriterion("multi_count is null");
            return (Criteria) this;
        }

        public Criteria andMultiCountIsNotNull() {
            addCriterion("multi_count is not null");
            return (Criteria) this;
        }

        public Criteria andMultiCountEqualTo(Integer value) {
            addCriterion("multi_count =", value, "multiCount");
            return (Criteria) this;
        }

        public Criteria andMultiCountNotEqualTo(Integer value) {
            addCriterion("multi_count <>", value, "multiCount");
            return (Criteria) this;
        }

        public Criteria andMultiCountGreaterThan(Integer value) {
            addCriterion("multi_count >", value, "multiCount");
            return (Criteria) this;
        }

        public Criteria andMultiCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("multi_count >=", value, "multiCount");
            return (Criteria) this;
        }

        public Criteria andMultiCountLessThan(Integer value) {
            addCriterion("multi_count <", value, "multiCount");
            return (Criteria) this;
        }

        public Criteria andMultiCountLessThanOrEqualTo(Integer value) {
            addCriterion("multi_count <=", value, "multiCount");
            return (Criteria) this;
        }

        public Criteria andMultiCountIn(List<Integer> values) {
            addCriterion("multi_count in", values, "multiCount");
            return (Criteria) this;
        }

        public Criteria andMultiCountNotIn(List<Integer> values) {
            addCriterion("multi_count not in", values, "multiCount");
            return (Criteria) this;
        }

        public Criteria andMultiCountBetween(Integer value1, Integer value2) {
            addCriterion("multi_count between", value1, value2, "multiCount");
            return (Criteria) this;
        }

        public Criteria andMultiCountNotBetween(Integer value1, Integer value2) {
            addCriterion("multi_count not between", value1, value2, "multiCount");
            return (Criteria) this;
        }

        public Criteria andJudgeCountIsNull() {
            addCriterion("judge_count is null");
            return (Criteria) this;
        }

        public Criteria andJudgeCountIsNotNull() {
            addCriterion("judge_count is not null");
            return (Criteria) this;
        }

        public Criteria andJudgeCountEqualTo(Integer value) {
            addCriterion("judge_count =", value, "judgeCount");
            return (Criteria) this;
        }

        public Criteria andJudgeCountNotEqualTo(Integer value) {
            addCriterion("judge_count <>", value, "judgeCount");
            return (Criteria) this;
        }

        public Criteria andJudgeCountGreaterThan(Integer value) {
            addCriterion("judge_count >", value, "judgeCount");
            return (Criteria) this;
        }

        public Criteria andJudgeCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("judge_count >=", value, "judgeCount");
            return (Criteria) this;
        }

        public Criteria andJudgeCountLessThan(Integer value) {
            addCriterion("judge_count <", value, "judgeCount");
            return (Criteria) this;
        }

        public Criteria andJudgeCountLessThanOrEqualTo(Integer value) {
            addCriterion("judge_count <=", value, "judgeCount");
            return (Criteria) this;
        }

        public Criteria andJudgeCountIn(List<Integer> values) {
            addCriterion("judge_count in", values, "judgeCount");
            return (Criteria) this;
        }

        public Criteria andJudgeCountNotIn(List<Integer> values) {
            addCriterion("judge_count not in", values, "judgeCount");
            return (Criteria) this;
        }

        public Criteria andJudgeCountBetween(Integer value1, Integer value2) {
            addCriterion("judge_count between", value1, value2, "judgeCount");
            return (Criteria) this;
        }

        public Criteria andJudgeCountNotBetween(Integer value1, Integer value2) {
            addCriterion("judge_count not between", value1, value2, "judgeCount");
            return (Criteria) this;
        }

        public Criteria andBlankCountIsNull() {
            addCriterion("blank_count is null");
            return (Criteria) this;
        }

        public Criteria andBlankCountIsNotNull() {
            addCriterion("blank_count is not null");
            return (Criteria) this;
        }

        public Criteria andBlankCountEqualTo(Integer value) {
            addCriterion("blank_count =", value, "blankCount");
            return (Criteria) this;
        }

        public Criteria andBlankCountNotEqualTo(Integer value) {
            addCriterion("blank_count <>", value, "blankCount");
            return (Criteria) this;
        }

        public Criteria andBlankCountGreaterThan(Integer value) {
            addCriterion("blank_count >", value, "blankCount");
            return (Criteria) this;
        }

        public Criteria andBlankCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("blank_count >=", value, "blankCount");
            return (Criteria) this;
        }

        public Criteria andBlankCountLessThan(Integer value) {
            addCriterion("blank_count <", value, "blankCount");
            return (Criteria) this;
        }

        public Criteria andBlankCountLessThanOrEqualTo(Integer value) {
            addCriterion("blank_count <=", value, "blankCount");
            return (Criteria) this;
        }

        public Criteria andBlankCountIn(List<Integer> values) {
            addCriterion("blank_count in", values, "blankCount");
            return (Criteria) this;
        }

        public Criteria andBlankCountNotIn(List<Integer> values) {
            addCriterion("blank_count not in", values, "blankCount");
            return (Criteria) this;
        }

        public Criteria andBlankCountBetween(Integer value1, Integer value2) {
            addCriterion("blank_count between", value1, value2, "blankCount");
            return (Criteria) this;
        }

        public Criteria andBlankCountNotBetween(Integer value1, Integer value2) {
            addCriterion("blank_count not between", value1, value2, "blankCount");
            return (Criteria) this;
        }

        public Criteria andAnswerCountIsNull() {
            addCriterion("answer_count is null");
            return (Criteria) this;
        }

        public Criteria andAnswerCountIsNotNull() {
            addCriterion("answer_count is not null");
            return (Criteria) this;
        }

        public Criteria andAnswerCountEqualTo(Integer value) {
            addCriterion("answer_count =", value, "answerCount");
            return (Criteria) this;
        }

        public Criteria andAnswerCountNotEqualTo(Integer value) {
            addCriterion("answer_count <>", value, "answerCount");
            return (Criteria) this;
        }

        public Criteria andAnswerCountGreaterThan(Integer value) {
            addCriterion("answer_count >", value, "answerCount");
            return (Criteria) this;
        }

        public Criteria andAnswerCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("answer_count >=", value, "answerCount");
            return (Criteria) this;
        }

        public Criteria andAnswerCountLessThan(Integer value) {
            addCriterion("answer_count <", value, "answerCount");
            return (Criteria) this;
        }

        public Criteria andAnswerCountLessThanOrEqualTo(Integer value) {
            addCriterion("answer_count <=", value, "answerCount");
            return (Criteria) this;
        }

        public Criteria andAnswerCountIn(List<Integer> values) {
            addCriterion("answer_count in", values, "answerCount");
            return (Criteria) this;
        }

        public Criteria andAnswerCountNotIn(List<Integer> values) {
            addCriterion("answer_count not in", values, "answerCount");
            return (Criteria) this;
        }

        public Criteria andAnswerCountBetween(Integer value1, Integer value2) {
            addCriterion("answer_count between", value1, value2, "answerCount");
            return (Criteria) this;
        }

        public Criteria andAnswerCountNotBetween(Integer value1, Integer value2) {
            addCriterion("answer_count not between", value1, value2, "answerCount");
            return (Criteria) this;
        }

        public Criteria andCourseIdIsNull() {
            addCriterion("course_id is null");
            return (Criteria) this;
        }

        public Criteria andCourseIdIsNotNull() {
            addCriterion("course_id is not null");
            return (Criteria) this;
        }

        public Criteria andCourseIdEqualTo(String value) {
            addCriterion("course_id =", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdNotEqualTo(String value) {
            addCriterion("course_id <>", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdGreaterThan(String value) {
            addCriterion("course_id >", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdGreaterThanOrEqualTo(String value) {
            addCriterion("course_id >=", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdLessThan(String value) {
            addCriterion("course_id <", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdLessThanOrEqualTo(String value) {
            addCriterion("course_id <=", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdLike(String value) {
            addCriterion("course_id like", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdNotLike(String value) {
            addCriterion("course_id not like", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdIn(List<String> values) {
            addCriterion("course_id in", values, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdNotIn(List<String> values) {
            addCriterion("course_id not in", values, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdBetween(String value1, String value2) {
            addCriterion("course_id between", value1, value2, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdNotBetween(String value1, String value2) {
            addCriterion("course_id not between", value1, value2, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseNameIsNull() {
            addCriterion("course_name is null");
            return (Criteria) this;
        }

        public Criteria andCourseNameIsNotNull() {
            addCriterion("course_name is not null");
            return (Criteria) this;
        }

        public Criteria andCourseNameEqualTo(String value) {
            addCriterion("course_name =", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotEqualTo(String value) {
            addCriterion("course_name <>", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameGreaterThan(String value) {
            addCriterion("course_name >", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameGreaterThanOrEqualTo(String value) {
            addCriterion("course_name >=", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameLessThan(String value) {
            addCriterion("course_name <", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameLessThanOrEqualTo(String value) {
            addCriterion("course_name <=", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameLike(String value) {
            addCriterion("course_name like", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotLike(String value) {
            addCriterion("course_name not like", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameIn(List<String> values) {
            addCriterion("course_name in", values, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotIn(List<String> values) {
            addCriterion("course_name not in", values, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameBetween(String value1, String value2) {
            addCriterion("course_name between", value1, value2, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotBetween(String value1, String value2) {
            addCriterion("course_name not between", value1, value2, "courseName");
            return (Criteria) this;
        }

        public Criteria andPassingScoreIsNull() {
            addCriterion("passing_score is null");
            return (Criteria) this;
        }

        public Criteria andPassingScoreIsNotNull() {
            addCriterion("passing_score is not null");
            return (Criteria) this;
        }

        public Criteria andPassingScoreEqualTo(Integer value) {
            addCriterion("passing_score =", value, "passingScore");
            return (Criteria) this;
        }

        public Criteria andPassingScoreNotEqualTo(Integer value) {
            addCriterion("passing_score <>", value, "passingScore");
            return (Criteria) this;
        }

        public Criteria andPassingScoreGreaterThan(Integer value) {
            addCriterion("passing_score >", value, "passingScore");
            return (Criteria) this;
        }

        public Criteria andPassingScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("passing_score >=", value, "passingScore");
            return (Criteria) this;
        }

        public Criteria andPassingScoreLessThan(Integer value) {
            addCriterion("passing_score <", value, "passingScore");
            return (Criteria) this;
        }

        public Criteria andPassingScoreLessThanOrEqualTo(Integer value) {
            addCriterion("passing_score <=", value, "passingScore");
            return (Criteria) this;
        }

        public Criteria andPassingScoreIn(List<Integer> values) {
            addCriterion("passing_score in", values, "passingScore");
            return (Criteria) this;
        }

        public Criteria andPassingScoreNotIn(List<Integer> values) {
            addCriterion("passing_score not in", values, "passingScore");
            return (Criteria) this;
        }

        public Criteria andPassingScoreBetween(Integer value1, Integer value2) {
            addCriterion("passing_score between", value1, value2, "passingScore");
            return (Criteria) this;
        }

        public Criteria andPassingScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("passing_score not between", value1, value2, "passingScore");
            return (Criteria) this;
        }

        public Criteria andPassingTypeIsNull() {
            addCriterion("passing_type is null");
            return (Criteria) this;
        }

        public Criteria andPassingTypeIsNotNull() {
            addCriterion("passing_type is not null");
            return (Criteria) this;
        }

        public Criteria andPassingTypeEqualTo(String value) {
            addCriterion("passing_type =", value, "passingType");
            return (Criteria) this;
        }

        public Criteria andPassingTypeNotEqualTo(String value) {
            addCriterion("passing_type <>", value, "passingType");
            return (Criteria) this;
        }

        public Criteria andPassingTypeGreaterThan(String value) {
            addCriterion("passing_type >", value, "passingType");
            return (Criteria) this;
        }

        public Criteria andPassingTypeGreaterThanOrEqualTo(String value) {
            addCriterion("passing_type >=", value, "passingType");
            return (Criteria) this;
        }

        public Criteria andPassingTypeLessThan(String value) {
            addCriterion("passing_type <", value, "passingType");
            return (Criteria) this;
        }

        public Criteria andPassingTypeLessThanOrEqualTo(String value) {
            addCriterion("passing_type <=", value, "passingType");
            return (Criteria) this;
        }

        public Criteria andPassingTypeLike(String value) {
            addCriterion("passing_type like", value, "passingType");
            return (Criteria) this;
        }

        public Criteria andPassingTypeNotLike(String value) {
            addCriterion("passing_type not like", value, "passingType");
            return (Criteria) this;
        }

        public Criteria andPassingTypeIn(List<String> values) {
            addCriterion("passing_type in", values, "passingType");
            return (Criteria) this;
        }

        public Criteria andPassingTypeNotIn(List<String> values) {
            addCriterion("passing_type not in", values, "passingType");
            return (Criteria) this;
        }

        public Criteria andPassingTypeBetween(String value1, String value2) {
            addCriterion("passing_type between", value1, value2, "passingType");
            return (Criteria) this;
        }

        public Criteria andPassingTypeNotBetween(String value1, String value2) {
            addCriterion("passing_type not between", value1, value2, "passingType");
            return (Criteria) this;
        }

        public Criteria andAutoMarkFlagIsNull() {
            addCriterion("auto_mark_flag is null");
            return (Criteria) this;
        }

        public Criteria andAutoMarkFlagIsNotNull() {
            addCriterion("auto_mark_flag is not null");
            return (Criteria) this;
        }

        public Criteria andAutoMarkFlagEqualTo(String value) {
            addCriterion("auto_mark_flag =", value, "autoMarkFlag");
            return (Criteria) this;
        }

        public Criteria andAutoMarkFlagNotEqualTo(String value) {
            addCriterion("auto_mark_flag <>", value, "autoMarkFlag");
            return (Criteria) this;
        }

        public Criteria andAutoMarkFlagGreaterThan(String value) {
            addCriterion("auto_mark_flag >", value, "autoMarkFlag");
            return (Criteria) this;
        }

        public Criteria andAutoMarkFlagGreaterThanOrEqualTo(String value) {
            addCriterion("auto_mark_flag >=", value, "autoMarkFlag");
            return (Criteria) this;
        }

        public Criteria andAutoMarkFlagLessThan(String value) {
            addCriterion("auto_mark_flag <", value, "autoMarkFlag");
            return (Criteria) this;
        }

        public Criteria andAutoMarkFlagLessThanOrEqualTo(String value) {
            addCriterion("auto_mark_flag <=", value, "autoMarkFlag");
            return (Criteria) this;
        }

        public Criteria andAutoMarkFlagLike(String value) {
            addCriterion("auto_mark_flag like", value, "autoMarkFlag");
            return (Criteria) this;
        }

        public Criteria andAutoMarkFlagNotLike(String value) {
            addCriterion("auto_mark_flag not like", value, "autoMarkFlag");
            return (Criteria) this;
        }

        public Criteria andAutoMarkFlagIn(List<String> values) {
            addCriterion("auto_mark_flag in", values, "autoMarkFlag");
            return (Criteria) this;
        }

        public Criteria andAutoMarkFlagNotIn(List<String> values) {
            addCriterion("auto_mark_flag not in", values, "autoMarkFlag");
            return (Criteria) this;
        }

        public Criteria andAutoMarkFlagBetween(String value1, String value2) {
            addCriterion("auto_mark_flag between", value1, value2, "autoMarkFlag");
            return (Criteria) this;
        }

        public Criteria andAutoMarkFlagNotBetween(String value1, String value2) {
            addCriterion("auto_mark_flag not between", value1, value2, "autoMarkFlag");
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