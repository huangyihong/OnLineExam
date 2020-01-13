package com.plg.shiro.entity;

import java.util.ArrayList;
import java.util.List;

public class OmPaperQuestionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OmPaperQuestionExample() {
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

        public Criteria andPaperQuestionIdIsNull() {
            addCriterion("paper_question_id is null");
            return (Criteria) this;
        }

        public Criteria andPaperQuestionIdIsNotNull() {
            addCriterion("paper_question_id is not null");
            return (Criteria) this;
        }

        public Criteria andPaperQuestionIdEqualTo(String value) {
            addCriterion("paper_question_id =", value, "paperQuestionId");
            return (Criteria) this;
        }

        public Criteria andPaperQuestionIdNotEqualTo(String value) {
            addCriterion("paper_question_id <>", value, "paperQuestionId");
            return (Criteria) this;
        }

        public Criteria andPaperQuestionIdGreaterThan(String value) {
            addCriterion("paper_question_id >", value, "paperQuestionId");
            return (Criteria) this;
        }

        public Criteria andPaperQuestionIdGreaterThanOrEqualTo(String value) {
            addCriterion("paper_question_id >=", value, "paperQuestionId");
            return (Criteria) this;
        }

        public Criteria andPaperQuestionIdLessThan(String value) {
            addCriterion("paper_question_id <", value, "paperQuestionId");
            return (Criteria) this;
        }

        public Criteria andPaperQuestionIdLessThanOrEqualTo(String value) {
            addCriterion("paper_question_id <=", value, "paperQuestionId");
            return (Criteria) this;
        }

        public Criteria andPaperQuestionIdLike(String value) {
            addCriterion("paper_question_id like", value, "paperQuestionId");
            return (Criteria) this;
        }

        public Criteria andPaperQuestionIdNotLike(String value) {
            addCriterion("paper_question_id not like", value, "paperQuestionId");
            return (Criteria) this;
        }

        public Criteria andPaperQuestionIdIn(List<String> values) {
            addCriterion("paper_question_id in", values, "paperQuestionId");
            return (Criteria) this;
        }

        public Criteria andPaperQuestionIdNotIn(List<String> values) {
            addCriterion("paper_question_id not in", values, "paperQuestionId");
            return (Criteria) this;
        }

        public Criteria andPaperQuestionIdBetween(String value1, String value2) {
            addCriterion("paper_question_id between", value1, value2, "paperQuestionId");
            return (Criteria) this;
        }

        public Criteria andPaperQuestionIdNotBetween(String value1, String value2) {
            addCriterion("paper_question_id not between", value1, value2, "paperQuestionId");
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

        public Criteria andQuestionOrderIsNull() {
            addCriterion("question_order is null");
            return (Criteria) this;
        }

        public Criteria andQuestionOrderIsNotNull() {
            addCriterion("question_order is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionOrderEqualTo(Integer value) {
            addCriterion("question_order =", value, "questionOrder");
            return (Criteria) this;
        }

        public Criteria andQuestionOrderNotEqualTo(Integer value) {
            addCriterion("question_order <>", value, "questionOrder");
            return (Criteria) this;
        }

        public Criteria andQuestionOrderGreaterThan(Integer value) {
            addCriterion("question_order >", value, "questionOrder");
            return (Criteria) this;
        }

        public Criteria andQuestionOrderGreaterThanOrEqualTo(Integer value) {
            addCriterion("question_order >=", value, "questionOrder");
            return (Criteria) this;
        }

        public Criteria andQuestionOrderLessThan(Integer value) {
            addCriterion("question_order <", value, "questionOrder");
            return (Criteria) this;
        }

        public Criteria andQuestionOrderLessThanOrEqualTo(Integer value) {
            addCriterion("question_order <=", value, "questionOrder");
            return (Criteria) this;
        }

        public Criteria andQuestionOrderIn(List<Integer> values) {
            addCriterion("question_order in", values, "questionOrder");
            return (Criteria) this;
        }

        public Criteria andQuestionOrderNotIn(List<Integer> values) {
            addCriterion("question_order not in", values, "questionOrder");
            return (Criteria) this;
        }

        public Criteria andQuestionOrderBetween(Integer value1, Integer value2) {
            addCriterion("question_order between", value1, value2, "questionOrder");
            return (Criteria) this;
        }

        public Criteria andQuestionOrderNotBetween(Integer value1, Integer value2) {
            addCriterion("question_order not between", value1, value2, "questionOrder");
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