package com.plg.shiro.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OmQuestionExample extends PageExample{
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OmQuestionExample() {
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

        public Criteria andQuestionIdIsNull() {
            addCriterion("question_id is null");
            return (Criteria) this;
        }

        public Criteria andQuestionIdIsNotNull() {
            addCriterion("question_id is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionIdEqualTo(String value) {
            addCriterion("question_id =", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdNotEqualTo(String value) {
            addCriterion("question_id <>", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdGreaterThan(String value) {
            addCriterion("question_id >", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdGreaterThanOrEqualTo(String value) {
            addCriterion("question_id >=", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdLessThan(String value) {
            addCriterion("question_id <", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdLessThanOrEqualTo(String value) {
            addCriterion("question_id <=", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdLike(String value) {
            addCriterion("question_id like", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdNotLike(String value) {
            addCriterion("question_id not like", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdIn(List<String> values) {
            addCriterion("question_id in", values, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdNotIn(List<String> values) {
            addCriterion("question_id not in", values, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdBetween(String value1, String value2) {
            addCriterion("question_id between", value1, value2, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdNotBetween(String value1, String value2) {
            addCriterion("question_id not between", value1, value2, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionNameIsNull() {
            addCriterion("question_name is null");
            return (Criteria) this;
        }

        public Criteria andQuestionNameIsNotNull() {
            addCriterion("question_name is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionNameEqualTo(String value) {
            addCriterion("question_name =", value, "questionName");
            return (Criteria) this;
        }

        public Criteria andQuestionNameNotEqualTo(String value) {
            addCriterion("question_name <>", value, "questionName");
            return (Criteria) this;
        }

        public Criteria andQuestionNameGreaterThan(String value) {
            addCriterion("question_name >", value, "questionName");
            return (Criteria) this;
        }

        public Criteria andQuestionNameGreaterThanOrEqualTo(String value) {
            addCriterion("question_name >=", value, "questionName");
            return (Criteria) this;
        }

        public Criteria andQuestionNameLessThan(String value) {
            addCriterion("question_name <", value, "questionName");
            return (Criteria) this;
        }

        public Criteria andQuestionNameLessThanOrEqualTo(String value) {
            addCriterion("question_name <=", value, "questionName");
            return (Criteria) this;
        }

        public Criteria andQuestionNameLike(String value) {
            addCriterion("question_name like", value, "questionName");
            return (Criteria) this;
        }

        public Criteria andQuestionNameNotLike(String value) {
            addCriterion("question_name not like", value, "questionName");
            return (Criteria) this;
        }

        public Criteria andQuestionNameIn(List<String> values) {
            addCriterion("question_name in", values, "questionName");
            return (Criteria) this;
        }

        public Criteria andQuestionNameNotIn(List<String> values) {
            addCriterion("question_name not in", values, "questionName");
            return (Criteria) this;
        }

        public Criteria andQuestionNameBetween(String value1, String value2) {
            addCriterion("question_name between", value1, value2, "questionName");
            return (Criteria) this;
        }

        public Criteria andQuestionNameNotBetween(String value1, String value2) {
            addCriterion("question_name not between", value1, value2, "questionName");
            return (Criteria) this;
        }

        public Criteria andOptionAIsNull() {
            addCriterion("option_a is null");
            return (Criteria) this;
        }

        public Criteria andOptionAIsNotNull() {
            addCriterion("option_a is not null");
            return (Criteria) this;
        }

        public Criteria andOptionAEqualTo(String value) {
            addCriterion("option_a =", value, "optionA");
            return (Criteria) this;
        }

        public Criteria andOptionANotEqualTo(String value) {
            addCriterion("option_a <>", value, "optionA");
            return (Criteria) this;
        }

        public Criteria andOptionAGreaterThan(String value) {
            addCriterion("option_a >", value, "optionA");
            return (Criteria) this;
        }

        public Criteria andOptionAGreaterThanOrEqualTo(String value) {
            addCriterion("option_a >=", value, "optionA");
            return (Criteria) this;
        }

        public Criteria andOptionALessThan(String value) {
            addCriterion("option_a <", value, "optionA");
            return (Criteria) this;
        }

        public Criteria andOptionALessThanOrEqualTo(String value) {
            addCriterion("option_a <=", value, "optionA");
            return (Criteria) this;
        }

        public Criteria andOptionALike(String value) {
            addCriterion("option_a like", value, "optionA");
            return (Criteria) this;
        }

        public Criteria andOptionANotLike(String value) {
            addCriterion("option_a not like", value, "optionA");
            return (Criteria) this;
        }

        public Criteria andOptionAIn(List<String> values) {
            addCriterion("option_a in", values, "optionA");
            return (Criteria) this;
        }

        public Criteria andOptionANotIn(List<String> values) {
            addCriterion("option_a not in", values, "optionA");
            return (Criteria) this;
        }

        public Criteria andOptionABetween(String value1, String value2) {
            addCriterion("option_a between", value1, value2, "optionA");
            return (Criteria) this;
        }

        public Criteria andOptionANotBetween(String value1, String value2) {
            addCriterion("option_a not between", value1, value2, "optionA");
            return (Criteria) this;
        }

        public Criteria andOptionBIsNull() {
            addCriterion("option_b is null");
            return (Criteria) this;
        }

        public Criteria andOptionBIsNotNull() {
            addCriterion("option_b is not null");
            return (Criteria) this;
        }

        public Criteria andOptionBEqualTo(String value) {
            addCriterion("option_b =", value, "optionB");
            return (Criteria) this;
        }

        public Criteria andOptionBNotEqualTo(String value) {
            addCriterion("option_b <>", value, "optionB");
            return (Criteria) this;
        }

        public Criteria andOptionBGreaterThan(String value) {
            addCriterion("option_b >", value, "optionB");
            return (Criteria) this;
        }

        public Criteria andOptionBGreaterThanOrEqualTo(String value) {
            addCriterion("option_b >=", value, "optionB");
            return (Criteria) this;
        }

        public Criteria andOptionBLessThan(String value) {
            addCriterion("option_b <", value, "optionB");
            return (Criteria) this;
        }

        public Criteria andOptionBLessThanOrEqualTo(String value) {
            addCriterion("option_b <=", value, "optionB");
            return (Criteria) this;
        }

        public Criteria andOptionBLike(String value) {
            addCriterion("option_b like", value, "optionB");
            return (Criteria) this;
        }

        public Criteria andOptionBNotLike(String value) {
            addCriterion("option_b not like", value, "optionB");
            return (Criteria) this;
        }

        public Criteria andOptionBIn(List<String> values) {
            addCriterion("option_b in", values, "optionB");
            return (Criteria) this;
        }

        public Criteria andOptionBNotIn(List<String> values) {
            addCriterion("option_b not in", values, "optionB");
            return (Criteria) this;
        }

        public Criteria andOptionBBetween(String value1, String value2) {
            addCriterion("option_b between", value1, value2, "optionB");
            return (Criteria) this;
        }

        public Criteria andOptionBNotBetween(String value1, String value2) {
            addCriterion("option_b not between", value1, value2, "optionB");
            return (Criteria) this;
        }

        public Criteria andOptionCIsNull() {
            addCriterion("option_c is null");
            return (Criteria) this;
        }

        public Criteria andOptionCIsNotNull() {
            addCriterion("option_c is not null");
            return (Criteria) this;
        }

        public Criteria andOptionCEqualTo(String value) {
            addCriterion("option_c =", value, "optionC");
            return (Criteria) this;
        }

        public Criteria andOptionCNotEqualTo(String value) {
            addCriterion("option_c <>", value, "optionC");
            return (Criteria) this;
        }

        public Criteria andOptionCGreaterThan(String value) {
            addCriterion("option_c >", value, "optionC");
            return (Criteria) this;
        }

        public Criteria andOptionCGreaterThanOrEqualTo(String value) {
            addCriterion("option_c >=", value, "optionC");
            return (Criteria) this;
        }

        public Criteria andOptionCLessThan(String value) {
            addCriterion("option_c <", value, "optionC");
            return (Criteria) this;
        }

        public Criteria andOptionCLessThanOrEqualTo(String value) {
            addCriterion("option_c <=", value, "optionC");
            return (Criteria) this;
        }

        public Criteria andOptionCLike(String value) {
            addCriterion("option_c like", value, "optionC");
            return (Criteria) this;
        }

        public Criteria andOptionCNotLike(String value) {
            addCriterion("option_c not like", value, "optionC");
            return (Criteria) this;
        }

        public Criteria andOptionCIn(List<String> values) {
            addCriterion("option_c in", values, "optionC");
            return (Criteria) this;
        }

        public Criteria andOptionCNotIn(List<String> values) {
            addCriterion("option_c not in", values, "optionC");
            return (Criteria) this;
        }

        public Criteria andOptionCBetween(String value1, String value2) {
            addCriterion("option_c between", value1, value2, "optionC");
            return (Criteria) this;
        }

        public Criteria andOptionCNotBetween(String value1, String value2) {
            addCriterion("option_c not between", value1, value2, "optionC");
            return (Criteria) this;
        }

        public Criteria andOptionDIsNull() {
            addCriterion("option_d is null");
            return (Criteria) this;
        }

        public Criteria andOptionDIsNotNull() {
            addCriterion("option_d is not null");
            return (Criteria) this;
        }

        public Criteria andOptionDEqualTo(String value) {
            addCriterion("option_d =", value, "optionD");
            return (Criteria) this;
        }

        public Criteria andOptionDNotEqualTo(String value) {
            addCriterion("option_d <>", value, "optionD");
            return (Criteria) this;
        }

        public Criteria andOptionDGreaterThan(String value) {
            addCriterion("option_d >", value, "optionD");
            return (Criteria) this;
        }

        public Criteria andOptionDGreaterThanOrEqualTo(String value) {
            addCriterion("option_d >=", value, "optionD");
            return (Criteria) this;
        }

        public Criteria andOptionDLessThan(String value) {
            addCriterion("option_d <", value, "optionD");
            return (Criteria) this;
        }

        public Criteria andOptionDLessThanOrEqualTo(String value) {
            addCriterion("option_d <=", value, "optionD");
            return (Criteria) this;
        }

        public Criteria andOptionDLike(String value) {
            addCriterion("option_d like", value, "optionD");
            return (Criteria) this;
        }

        public Criteria andOptionDNotLike(String value) {
            addCriterion("option_d not like", value, "optionD");
            return (Criteria) this;
        }

        public Criteria andOptionDIn(List<String> values) {
            addCriterion("option_d in", values, "optionD");
            return (Criteria) this;
        }

        public Criteria andOptionDNotIn(List<String> values) {
            addCriterion("option_d not in", values, "optionD");
            return (Criteria) this;
        }

        public Criteria andOptionDBetween(String value1, String value2) {
            addCriterion("option_d between", value1, value2, "optionD");
            return (Criteria) this;
        }

        public Criteria andOptionDNotBetween(String value1, String value2) {
            addCriterion("option_d not between", value1, value2, "optionD");
            return (Criteria) this;
        }

        public Criteria andRightResultIsNull() {
            addCriterion("right_result is null");
            return (Criteria) this;
        }

        public Criteria andRightResultIsNotNull() {
            addCriterion("right_result is not null");
            return (Criteria) this;
        }

        public Criteria andRightResultEqualTo(String value) {
            addCriterion("right_result =", value, "rightResult");
            return (Criteria) this;
        }

        public Criteria andRightResultNotEqualTo(String value) {
            addCriterion("right_result <>", value, "rightResult");
            return (Criteria) this;
        }

        public Criteria andRightResultGreaterThan(String value) {
            addCriterion("right_result >", value, "rightResult");
            return (Criteria) this;
        }

        public Criteria andRightResultGreaterThanOrEqualTo(String value) {
            addCriterion("right_result >=", value, "rightResult");
            return (Criteria) this;
        }

        public Criteria andRightResultLessThan(String value) {
            addCriterion("right_result <", value, "rightResult");
            return (Criteria) this;
        }

        public Criteria andRightResultLessThanOrEqualTo(String value) {
            addCriterion("right_result <=", value, "rightResult");
            return (Criteria) this;
        }

        public Criteria andRightResultLike(String value) {
            addCriterion("right_result like", value, "rightResult");
            return (Criteria) this;
        }

        public Criteria andRightResultNotLike(String value) {
            addCriterion("right_result not like", value, "rightResult");
            return (Criteria) this;
        }

        public Criteria andRightResultIn(List<String> values) {
            addCriterion("right_result in", values, "rightResult");
            return (Criteria) this;
        }

        public Criteria andRightResultNotIn(List<String> values) {
            addCriterion("right_result not in", values, "rightResult");
            return (Criteria) this;
        }

        public Criteria andRightResultBetween(String value1, String value2) {
            addCriterion("right_result between", value1, value2, "rightResult");
            return (Criteria) this;
        }

        public Criteria andRightResultNotBetween(String value1, String value2) {
            addCriterion("right_result not between", value1, value2, "rightResult");
            return (Criteria) this;
        }

        public Criteria andQuestionScoreIsNull() {
            addCriterion("question_score is null");
            return (Criteria) this;
        }

        public Criteria andQuestionScoreIsNotNull() {
            addCriterion("question_score is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionScoreEqualTo(Integer value) {
            addCriterion("question_score =", value, "questionScore");
            return (Criteria) this;
        }

        public Criteria andQuestionScoreNotEqualTo(Integer value) {
            addCriterion("question_score <>", value, "questionScore");
            return (Criteria) this;
        }

        public Criteria andQuestionScoreGreaterThan(Integer value) {
            addCriterion("question_score >", value, "questionScore");
            return (Criteria) this;
        }

        public Criteria andQuestionScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("question_score >=", value, "questionScore");
            return (Criteria) this;
        }

        public Criteria andQuestionScoreLessThan(Integer value) {
            addCriterion("question_score <", value, "questionScore");
            return (Criteria) this;
        }

        public Criteria andQuestionScoreLessThanOrEqualTo(Integer value) {
            addCriterion("question_score <=", value, "questionScore");
            return (Criteria) this;
        }

        public Criteria andQuestionScoreIn(List<Integer> values) {
            addCriterion("question_score in", values, "questionScore");
            return (Criteria) this;
        }

        public Criteria andQuestionScoreNotIn(List<Integer> values) {
            addCriterion("question_score not in", values, "questionScore");
            return (Criteria) this;
        }

        public Criteria andQuestionScoreBetween(Integer value1, Integer value2) {
            addCriterion("question_score between", value1, value2, "questionScore");
            return (Criteria) this;
        }

        public Criteria andQuestionScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("question_score not between", value1, value2, "questionScore");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeIsNull() {
            addCriterion("question_type is null");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeIsNotNull() {
            addCriterion("question_type is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeEqualTo(String value) {
            addCriterion("question_type =", value, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeNotEqualTo(String value) {
            addCriterion("question_type <>", value, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeGreaterThan(String value) {
            addCriterion("question_type >", value, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeGreaterThanOrEqualTo(String value) {
            addCriterion("question_type >=", value, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeLessThan(String value) {
            addCriterion("question_type <", value, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeLessThanOrEqualTo(String value) {
            addCriterion("question_type <=", value, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeLike(String value) {
            addCriterion("question_type like", value, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeNotLike(String value) {
            addCriterion("question_type not like", value, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeIn(List<String> values) {
            addCriterion("question_type in", values, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeNotIn(List<String> values) {
            addCriterion("question_type not in", values, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeBetween(String value1, String value2) {
            addCriterion("question_type between", value1, value2, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeNotBetween(String value1, String value2) {
            addCriterion("question_type not between", value1, value2, "questionType");
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

        public Criteria andAnswerOrderIsNull() {
            addCriterion("answer_order is null");
            return (Criteria) this;
        }

        public Criteria andAnswerOrderIsNotNull() {
            addCriterion("answer_order is not null");
            return (Criteria) this;
        }

        public Criteria andAnswerOrderEqualTo(String value) {
            addCriterion("answer_order =", value, "answerOrder");
            return (Criteria) this;
        }

        public Criteria andAnswerOrderNotEqualTo(String value) {
            addCriterion("answer_order <>", value, "answerOrder");
            return (Criteria) this;
        }

        public Criteria andAnswerOrderGreaterThan(String value) {
            addCriterion("answer_order >", value, "answerOrder");
            return (Criteria) this;
        }

        public Criteria andAnswerOrderGreaterThanOrEqualTo(String value) {
            addCriterion("answer_order >=", value, "answerOrder");
            return (Criteria) this;
        }

        public Criteria andAnswerOrderLessThan(String value) {
            addCriterion("answer_order <", value, "answerOrder");
            return (Criteria) this;
        }

        public Criteria andAnswerOrderLessThanOrEqualTo(String value) {
            addCriterion("answer_order <=", value, "answerOrder");
            return (Criteria) this;
        }

        public Criteria andAnswerOrderLike(String value) {
            addCriterion("answer_order like", value, "answerOrder");
            return (Criteria) this;
        }

        public Criteria andAnswerOrderNotLike(String value) {
            addCriterion("answer_order not like", value, "answerOrder");
            return (Criteria) this;
        }

        public Criteria andAnswerOrderIn(List<String> values) {
            addCriterion("answer_order in", values, "answerOrder");
            return (Criteria) this;
        }

        public Criteria andAnswerOrderNotIn(List<String> values) {
            addCriterion("answer_order not in", values, "answerOrder");
            return (Criteria) this;
        }

        public Criteria andAnswerOrderBetween(String value1, String value2) {
            addCriterion("answer_order between", value1, value2, "answerOrder");
            return (Criteria) this;
        }

        public Criteria andAnswerOrderNotBetween(String value1, String value2) {
            addCriterion("answer_order not between", value1, value2, "answerOrder");
            return (Criteria) this;
        }

        public Criteria andIsArtificialIsNull() {
            addCriterion("is_artificial is null");
            return (Criteria) this;
        }

        public Criteria andIsArtificialIsNotNull() {
            addCriterion("is_artificial is not null");
            return (Criteria) this;
        }

        public Criteria andIsArtificialEqualTo(String value) {
            addCriterion("is_artificial =", value, "isArtificial");
            return (Criteria) this;
        }

        public Criteria andIsArtificialNotEqualTo(String value) {
            addCriterion("is_artificial <>", value, "isArtificial");
            return (Criteria) this;
        }

        public Criteria andIsArtificialGreaterThan(String value) {
            addCriterion("is_artificial >", value, "isArtificial");
            return (Criteria) this;
        }

        public Criteria andIsArtificialGreaterThanOrEqualTo(String value) {
            addCriterion("is_artificial >=", value, "isArtificial");
            return (Criteria) this;
        }

        public Criteria andIsArtificialLessThan(String value) {
            addCriterion("is_artificial <", value, "isArtificial");
            return (Criteria) this;
        }

        public Criteria andIsArtificialLessThanOrEqualTo(String value) {
            addCriterion("is_artificial <=", value, "isArtificial");
            return (Criteria) this;
        }

        public Criteria andIsArtificialLike(String value) {
            addCriterion("is_artificial like", value, "isArtificial");
            return (Criteria) this;
        }

        public Criteria andIsArtificialNotLike(String value) {
            addCriterion("is_artificial not like", value, "isArtificial");
            return (Criteria) this;
        }

        public Criteria andIsArtificialIn(List<String> values) {
            addCriterion("is_artificial in", values, "isArtificial");
            return (Criteria) this;
        }

        public Criteria andIsArtificialNotIn(List<String> values) {
            addCriterion("is_artificial not in", values, "isArtificial");
            return (Criteria) this;
        }

        public Criteria andIsArtificialBetween(String value1, String value2) {
            addCriterion("is_artificial between", value1, value2, "isArtificial");
            return (Criteria) this;
        }

        public Criteria andIsArtificialNotBetween(String value1, String value2) {
            addCriterion("is_artificial not between", value1, value2, "isArtificial");
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

        public Criteria andDeletedIsNull() {
            addCriterion("deleted is null");
            return (Criteria) this;
        }

        public Criteria andDeletedIsNotNull() {
            addCriterion("deleted is not null");
            return (Criteria) this;
        }

        public Criteria andDeletedEqualTo(String value) {
            addCriterion("deleted =", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotEqualTo(String value) {
            addCriterion("deleted <>", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedGreaterThan(String value) {
            addCriterion("deleted >", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedGreaterThanOrEqualTo(String value) {
            addCriterion("deleted >=", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedLessThan(String value) {
            addCriterion("deleted <", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedLessThanOrEqualTo(String value) {
            addCriterion("deleted <=", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedLike(String value) {
            addCriterion("deleted like", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotLike(String value) {
            addCriterion("deleted not like", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedIn(List<String> values) {
            addCriterion("deleted in", values, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotIn(List<String> values) {
            addCriterion("deleted not in", values, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedBetween(String value1, String value2) {
            addCriterion("deleted between", value1, value2, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotBetween(String value1, String value2) {
            addCriterion("deleted not between", value1, value2, "deleted");
            return (Criteria) this;
        }

        public Criteria andOptionEIsNull() {
            addCriterion("option_e is null");
            return (Criteria) this;
        }

        public Criteria andOptionEIsNotNull() {
            addCriterion("option_e is not null");
            return (Criteria) this;
        }

        public Criteria andOptionEEqualTo(String value) {
            addCriterion("option_e =", value, "optionE");
            return (Criteria) this;
        }

        public Criteria andOptionENotEqualTo(String value) {
            addCriterion("option_e <>", value, "optionE");
            return (Criteria) this;
        }

        public Criteria andOptionEGreaterThan(String value) {
            addCriterion("option_e >", value, "optionE");
            return (Criteria) this;
        }

        public Criteria andOptionEGreaterThanOrEqualTo(String value) {
            addCriterion("option_e >=", value, "optionE");
            return (Criteria) this;
        }

        public Criteria andOptionELessThan(String value) {
            addCriterion("option_e <", value, "optionE");
            return (Criteria) this;
        }

        public Criteria andOptionELessThanOrEqualTo(String value) {
            addCriterion("option_e <=", value, "optionE");
            return (Criteria) this;
        }

        public Criteria andOptionELike(String value) {
            addCriterion("option_e like", value, "optionE");
            return (Criteria) this;
        }

        public Criteria andOptionENotLike(String value) {
            addCriterion("option_e not like", value, "optionE");
            return (Criteria) this;
        }

        public Criteria andOptionEIn(List<String> values) {
            addCriterion("option_e in", values, "optionE");
            return (Criteria) this;
        }

        public Criteria andOptionENotIn(List<String> values) {
            addCriterion("option_e not in", values, "optionE");
            return (Criteria) this;
        }

        public Criteria andOptionEBetween(String value1, String value2) {
            addCriterion("option_e between", value1, value2, "optionE");
            return (Criteria) this;
        }

        public Criteria andOptionENotBetween(String value1, String value2) {
            addCriterion("option_e not between", value1, value2, "optionE");
            return (Criteria) this;
        }

        public Criteria andOptionFIsNull() {
            addCriterion("option_f is null");
            return (Criteria) this;
        }

        public Criteria andOptionFIsNotNull() {
            addCriterion("option_f is not null");
            return (Criteria) this;
        }

        public Criteria andOptionFEqualTo(String value) {
            addCriterion("option_f =", value, "optionF");
            return (Criteria) this;
        }

        public Criteria andOptionFNotEqualTo(String value) {
            addCriterion("option_f <>", value, "optionF");
            return (Criteria) this;
        }

        public Criteria andOptionFGreaterThan(String value) {
            addCriterion("option_f >", value, "optionF");
            return (Criteria) this;
        }

        public Criteria andOptionFGreaterThanOrEqualTo(String value) {
            addCriterion("option_f >=", value, "optionF");
            return (Criteria) this;
        }

        public Criteria andOptionFLessThan(String value) {
            addCriterion("option_f <", value, "optionF");
            return (Criteria) this;
        }

        public Criteria andOptionFLessThanOrEqualTo(String value) {
            addCriterion("option_f <=", value, "optionF");
            return (Criteria) this;
        }

        public Criteria andOptionFLike(String value) {
            addCriterion("option_f like", value, "optionF");
            return (Criteria) this;
        }

        public Criteria andOptionFNotLike(String value) {
            addCriterion("option_f not like", value, "optionF");
            return (Criteria) this;
        }

        public Criteria andOptionFIn(List<String> values) {
            addCriterion("option_f in", values, "optionF");
            return (Criteria) this;
        }

        public Criteria andOptionFNotIn(List<String> values) {
            addCriterion("option_f not in", values, "optionF");
            return (Criteria) this;
        }

        public Criteria andOptionFBetween(String value1, String value2) {
            addCriterion("option_f between", value1, value2, "optionF");
            return (Criteria) this;
        }

        public Criteria andOptionFNotBetween(String value1, String value2) {
            addCriterion("option_f not between", value1, value2, "optionF");
            return (Criteria) this;
        }

        public Criteria andOptionGIsNull() {
            addCriterion("option_g is null");
            return (Criteria) this;
        }

        public Criteria andOptionGIsNotNull() {
            addCriterion("option_g is not null");
            return (Criteria) this;
        }

        public Criteria andOptionGEqualTo(String value) {
            addCriterion("option_g =", value, "optionG");
            return (Criteria) this;
        }

        public Criteria andOptionGNotEqualTo(String value) {
            addCriterion("option_g <>", value, "optionG");
            return (Criteria) this;
        }

        public Criteria andOptionGGreaterThan(String value) {
            addCriterion("option_g >", value, "optionG");
            return (Criteria) this;
        }

        public Criteria andOptionGGreaterThanOrEqualTo(String value) {
            addCriterion("option_g >=", value, "optionG");
            return (Criteria) this;
        }

        public Criteria andOptionGLessThan(String value) {
            addCriterion("option_g <", value, "optionG");
            return (Criteria) this;
        }

        public Criteria andOptionGLessThanOrEqualTo(String value) {
            addCriterion("option_g <=", value, "optionG");
            return (Criteria) this;
        }

        public Criteria andOptionGLike(String value) {
            addCriterion("option_g like", value, "optionG");
            return (Criteria) this;
        }

        public Criteria andOptionGNotLike(String value) {
            addCriterion("option_g not like", value, "optionG");
            return (Criteria) this;
        }

        public Criteria andOptionGIn(List<String> values) {
            addCriterion("option_g in", values, "optionG");
            return (Criteria) this;
        }

        public Criteria andOptionGNotIn(List<String> values) {
            addCriterion("option_g not in", values, "optionG");
            return (Criteria) this;
        }

        public Criteria andOptionGBetween(String value1, String value2) {
            addCriterion("option_g between", value1, value2, "optionG");
            return (Criteria) this;
        }

        public Criteria andOptionGNotBetween(String value1, String value2) {
            addCriterion("option_g not between", value1, value2, "optionG");
            return (Criteria) this;
        }

        public Criteria andOptionHIsNull() {
            addCriterion("option_h is null");
            return (Criteria) this;
        }

        public Criteria andOptionHIsNotNull() {
            addCriterion("option_h is not null");
            return (Criteria) this;
        }

        public Criteria andOptionHEqualTo(String value) {
            addCriterion("option_h =", value, "optionH");
            return (Criteria) this;
        }

        public Criteria andOptionHNotEqualTo(String value) {
            addCriterion("option_h <>", value, "optionH");
            return (Criteria) this;
        }

        public Criteria andOptionHGreaterThan(String value) {
            addCriterion("option_h >", value, "optionH");
            return (Criteria) this;
        }

        public Criteria andOptionHGreaterThanOrEqualTo(String value) {
            addCriterion("option_h >=", value, "optionH");
            return (Criteria) this;
        }

        public Criteria andOptionHLessThan(String value) {
            addCriterion("option_h <", value, "optionH");
            return (Criteria) this;
        }

        public Criteria andOptionHLessThanOrEqualTo(String value) {
            addCriterion("option_h <=", value, "optionH");
            return (Criteria) this;
        }

        public Criteria andOptionHLike(String value) {
            addCriterion("option_h like", value, "optionH");
            return (Criteria) this;
        }

        public Criteria andOptionHNotLike(String value) {
            addCriterion("option_h not like", value, "optionH");
            return (Criteria) this;
        }

        public Criteria andOptionHIn(List<String> values) {
            addCriterion("option_h in", values, "optionH");
            return (Criteria) this;
        }

        public Criteria andOptionHNotIn(List<String> values) {
            addCriterion("option_h not in", values, "optionH");
            return (Criteria) this;
        }

        public Criteria andOptionHBetween(String value1, String value2) {
            addCriterion("option_h between", value1, value2, "optionH");
            return (Criteria) this;
        }

        public Criteria andOptionHNotBetween(String value1, String value2) {
            addCriterion("option_h not between", value1, value2, "optionH");
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