package com.plg.shiro.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.plg.shiro.dao.OmExamSubmitMapper;
import com.plg.shiro.dao.OmExamSubmitVoMapper;
import com.plg.shiro.entity.OmExamAnswer;
import com.plg.shiro.entity.OmExamPlan;
import com.plg.shiro.entity.OmExamSubmit;
import com.plg.shiro.entity.OmExamSubmitExample;
import com.plg.shiro.entity.OmExamUser;
import com.plg.shiro.entity.OmPaper;
import com.plg.shiro.entity.OmQuestion;
import com.plg.shiro.entity.OmUser;
import com.plg.shiro.entity.Vo.OmExamSubmitVo;
import com.plg.shiro.entity.Vo.OmExamSubmitVoExample;
import com.plg.shiro.entity.Vo.OmQuestionVo;
import com.plg.shiro.service.IExamAnswerService;
import com.plg.shiro.service.IExamSubmitService;
import com.plg.shiro.service.IExamUserService;
import com.plg.shiro.service.IQuestionService;
import com.plg.shiro.util.DateUtil;
import com.plg.shiro.util.dwz.LayuiPage;

@Service
public class ExamSubmitService implements IExamSubmitService {
	private static final Logger logger = LoggerFactory.getLogger(ExamSubmitService.class);

	@Resource
	private OmExamSubmitMapper omExamSubmitMapper;
	
	@Resource
	private OmExamSubmitVoMapper omExamSubmitVoMapper;
	
	@Resource
	private IExamUserService examUserService;
	
	@Resource
	private IExamAnswerService answerService;
	
	@Resource
	private IQuestionService questionService;

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void insertExamSubmit(OmExamSubmit record) {
		omExamSubmitMapper.insert(record);
	}

	@Override
	public int updateByPrimaryKeySelective(OmExamSubmit record) {
		return omExamSubmitMapper.updateByPrimaryKeySelective(record);
	}
	
	@Override
	public OmExamSubmit selectByUserId(String planId, String paperId, String userId) {
		OmExamSubmitExample example = new OmExamSubmitExample();
		OmExamSubmitExample.Criteria criteria = example.createCriteria();
		criteria.andPlanIdEqualTo(planId);
		if(StringUtils.isNoneBlank(paperId)){
			criteria.andPaperIdEqualTo(paperId);
		}
		criteria.andUserIdEqualTo(userId);
		List<OmExamSubmit> list = omExamSubmitMapper.selectByExample(example);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<OmExamSubmit> findPageList(HttpServletRequest request, LayuiPage page) {
		OmExamSubmitExample example = new OmExamSubmitExample();
		OmExamSubmitExample.Criteria criteria = example.createCriteria();
		example.setLimitPageSize(page.getLimit());
		example.setLimitStart(page.limitStart());
		example.setOrderByClause("START_TIME desc");
		page.setTotalCount(omExamSubmitMapper.countByExample(example));
		return omExamSubmitMapper.selectByExample(example);
	}
	
	@Override
	public List<OmExamSubmitVo> findVoPageList(HttpServletRequest request, LayuiPage page) {
		OmExamSubmitVoExample example = new OmExamSubmitVoExample();
		OmExamSubmitVoExample.Criteria criteria = example.createCriteria();
		String planId = request.getParameter("planId");
		if(StringUtils.isNotBlank(planId)){
			criteria.andPlanIdEqualTo(planId);
		}
		String planName = request.getParameter("planName");
		if(StringUtils.isNotBlank(planName)){
			criteria.andPlanNameLike("%"+planName+"%");
		}
		String paperName = request.getParameter("paperName");
		if(StringUtils.isNotBlank(paperName)){
			criteria.andPaperNameLike("%"+paperName+"%");
		}
		String realName = request.getParameter("realName");
		if(StringUtils.isNotBlank(realName)){
			criteria.andRealNameLike("%"+realName+"%");
		}
		example.setLimitPageSize(page.getLimit());
		example.setLimitStart(page.limitStart());
		example.setOrderByClause("START_TIME desc");
		page.setTotalCount(omExamSubmitVoMapper.countByExample(example));
		return omExamSubmitVoMapper.selectByExample(example);
	}
	
	@Override
	public OmExamSubmitVo selectVoByUserId(String planId, String paperId, String userId) {
		OmExamSubmitVoExample example = new OmExamSubmitVoExample();
		OmExamSubmitVoExample.Criteria criteria = example.createCriteria();
		criteria.andPlanIdEqualTo(planId);
		criteria.andPaperIdEqualTo(paperId);
		criteria.andUserIdEqualTo(userId);
		List<OmExamSubmitVo> list = omExamSubmitVoMapper.selectByExample(example);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<OmExamSubmitVo> findUserPageList(HttpServletRequest request, LayuiPage page, OmUser user,String examUserType,String status) {
		//获取当前用户被授权的考试安排1或者阅卷安排2
		List<OmExamUser> listExam = examUserService.selectUserExam(user, examUserType);
		List<String> planIdList = new ArrayList<String>();
		for(OmExamUser examUser:listExam){
			planIdList.add(examUser.getPlanId());
		}
		if(planIdList.size()>0){
			OmExamSubmitVoExample example = new OmExamSubmitVoExample();
			OmExamSubmitVoExample.Criteria criteria = example.createCriteria();
			criteria.andPlanIdIn(planIdList);
			if(StringUtils.isNotBlank(status)){
				criteria.andStatusEqualTo(status);
			}
			if("1".equals(examUserType)){//回顾我的试卷
				criteria.andUserIdEqualTo(user.getUserId());
			}
			String planId = request.getParameter("planId");
			if(StringUtils.isNotBlank(planId)){
				criteria.andPlanIdEqualTo(planId);
			}
			String planName = request.getParameter("planName");
			if(StringUtils.isNotBlank(planName)){
				criteria.andPlanNameLike("%"+planName+"%");
			}
			String paperName = request.getParameter("paperName");
			if(StringUtils.isNotBlank(paperName)){
				criteria.andPaperNameLike("%"+paperName+"%");
			}
			String realName = request.getParameter("realName");
			if(StringUtils.isNotBlank(realName)){
				criteria.andRealNameLike("%"+realName+"%");
			}
			example.setLimitPageSize(page.getLimit());
			example.setLimitStart(page.limitStart());
			example.setOrderByClause("START_TIME desc");
			page.setTotalCount(omExamSubmitVoMapper.countByExample(example));
			return omExamSubmitVoMapper.selectByExample(example);
		}else{
			return null;
		}
		
	}

	@Override
	public List<OmExamSubmit> findListByPlanId(String planId, String paperId, String status) {
		OmExamSubmitExample example = new OmExamSubmitExample();
		OmExamSubmitExample.Criteria criteria = example.createCriteria();
		criteria.andPlanIdEqualTo(planId);
		criteria.andPaperIdEqualTo(paperId);
		if(StringUtils.isNotBlank(status)){
			criteria.andStatusIn(Arrays.asList(status.split(",")));
		}
		return omExamSubmitMapper.selectByExample(example);
	}

	@Override
	public OmExamSubmit selectByPrimaryKey(String submitId) {
		return omExamSubmitMapper.selectByPrimaryKey(submitId);
	}
	
	@Override
	public List<OmExamSubmitVo> findUserPageList(HttpServletRequest request, LayuiPage page, String status) {
		OmExamSubmitVoExample example = new OmExamSubmitVoExample();
		OmExamSubmitVoExample.Criteria criteria = example.createCriteria();
		queryCommon(request, status, criteria);
		example.setLimitPageSize(page.getLimit());
		example.setLimitStart(page.limitStart());
		page.setTotalCount(omExamSubmitVoMapper.countByExample(example));
		if("3,4".equals(status)){//发布成绩页面
			example.setOrderByClause("status,START_TIME desc");
		}
		if("1,2".equals(status)){//监控页面
			example.setOrderByClause("user_answer_count desc,START_TIME desc");
		}
		return omExamSubmitVoMapper.selectByExample(example);
		
	}

	@Override
	public void updateBatch(String ids, String status) {
		OmExamSubmitExample example = new OmExamSubmitExample();
		OmExamSubmitExample.Criteria c = example.createCriteria();
        c.andSubmitIdIn(Arrays.asList(ids.split(",")));
        List<OmExamSubmit> list = omExamSubmitMapper.selectByExample(example);
        for(OmExamSubmit bean:list){
        	bean.setStatus(status);
	        if("4".equals(bean.getStatus())){//成绩发布
				bean.setPublishTime(DateUtil.dateParse(new Date(),""));
			}
	        omExamSubmitMapper.updateByPrimaryKeySelective(bean);
        }
	}

	@Override
	public List<OmExamSubmitVo> getList(HttpServletRequest request,String status) {
		OmExamSubmitVoExample example = new OmExamSubmitVoExample();
		OmExamSubmitVoExample.Criteria criteria = example.createCriteria();
		queryCommon(request, status, criteria);
		example.setOrderByClause("START_TIME desc");
		if("3,4".equals(status)){//发布成绩页面
			example.setOrderByClause("status,START_TIME desc");
		}
		return omExamSubmitVoMapper.selectByExample(example);
	}

	private void queryCommon(HttpServletRequest request, String status, OmExamSubmitVoExample.Criteria criteria) {
		String submitId = request.getParameter("submitId");
		if(StringUtils.isNotBlank(submitId)){
			criteria.andSubmitIdIn(Arrays.asList(submitId.split(",")));
		}
		String paperId = request.getParameter("paperId");
		if(StringUtils.isNotBlank(paperId)){
			criteria.andPaperIdEqualTo(paperId);
		}
		String planId = request.getParameter("planId");
		if(StringUtils.isNotBlank(planId)){
			criteria.andPlanIdEqualTo(planId);
		}
		String planName = request.getParameter("planName");
		if(StringUtils.isNotBlank(planName)){
			criteria.andPlanNameLike("%"+planName+"%");
		}
		String paperName = request.getParameter("paperName");
		if(StringUtils.isNotBlank(paperName)){
			criteria.andPaperNameLike("%"+paperName+"%");
		}
		String realName = request.getParameter("realName");
		if(StringUtils.isNotBlank(realName)){
			criteria.andRealNameLike("%"+realName+"%");
		}
		if(StringUtils.isNotBlank(status)){
			criteria.andStatusIn(Arrays.asList(status.split(",")));
		}
	}
	
	@Override
	public List<OmExamSubmitVo> findVoUserList(HttpServletRequest request, String status) {
		OmExamSubmitVoExample example = new OmExamSubmitVoExample();
		OmExamSubmitVoExample.Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(status)){
			criteria.andStatusIn(Arrays.asList(status.split(",")));
		}
		String planId = request.getParameter("planId");
		if(StringUtils.isNotBlank(planId)){
			criteria.andPlanIdEqualTo(planId);
		}
		String planName = request.getParameter("planName");
		if(StringUtils.isNotBlank(planName)){
			criteria.andPlanNameLike("%"+planName+"%");
		}
		String paperName = request.getParameter("paperName");
		if(StringUtils.isNotBlank(paperName)){
			criteria.andPaperNameLike("%"+paperName+"%");
		}
		String realName = request.getParameter("realName");
		if(StringUtils.isNotBlank(realName)){
			criteria.andRealNameLike("%"+realName+"%");
		}
		example.setOrderByClause("START_TIME desc");
		if("3,4".equals(status)){//发布成绩页面
			example.setOrderByClause("status,START_TIME desc");
		}
		return omExamSubmitVoMapper.selectByExample(example);
	}
	
	@Override
	public Map<String, Object> sqlQueryScoreNumMaxMin(String planId, String status,String groupId) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select IFNULL(max(t.total_score),0) as max_score,IFNULL(min(t.total_score),0) as min_score,ROUND(IFNULL(avg(t.total_score),0)) as avg_score, "
				+ "count(1) as user_num, IFNULL(sum(CASE when total_score>=90 then 1 else 0 end),0) AS perfect_num,\n" + 
				"IFNULL(sum(CASE when total_score>=70 and total_score<90 then 1 else 0 end),0)  AS good_num,\n" + 
				"IFNULL(sum(CASE when total_score>=60 and total_score<70 then 1 else 0 end),0) AS pass_num,\n" + 
				"IFNULL(sum(CASE when total_score<60 then 1 else 0 end),0) AS nopass_num ");
		sqlBuffer.append("from om_exam_submit t where 1=1 ");
		sqlBuffer.append("and plan_id = '"+planId+"' ");
		sqlBuffer.append("and status = '"+status+"' ");
		if(StringUtils.isNotBlank(groupId)) {
			sqlBuffer.append("and user_id in (select user_id from om_user t where t.group_id='"+groupId+"' ) ");
		}
		Map<String, Object> map = jdbcTemplate.queryForMap(sqlBuffer.toString());
		return map;
	}

	@Override
	public OmExamSubmit autoMarkTotalScore(OmExamPlan plan, OmPaper paper, OmExamSubmit bean) {
		String answerUserId = bean.getUserId();
		String planId = plan.getPlanId();
		String paperId = plan.getPaperId();
		//答案
		List<OmExamAnswer> answerList = answerService.selectByUserPaperID(answerUserId, planId, paperId);
		Map<String,OmExamAnswer> answerMap = new HashMap<String,OmExamAnswer>();
		for(OmExamAnswer answer:answerList ){
			answerMap.put(answer.getQuestionId(), answer);
		}
		List<OmQuestion> questionList = new ArrayList<OmQuestion>();
		if("1".equals(paper.getAddMode())){//人工
			questionList = questionService.selectPaperQuestion(paper.getPaperId());
		}else{
			//随机试卷
			questionList = questionService.selectUserPaperQuestion(paper.getPaperId(),answerUserId);
		}
		int totalScore=0;
		for(OmQuestion questionBean:questionList){
			String questionType = questionBean.getQuestionType();
		    OmQuestionVo questionVo = new OmQuestionVo();
		    BeanUtils.copyProperties(questionBean,questionVo);
		    if(answerMap.get(questionBean.getQuestionId())!=null){
		    	questionVo.setAnswerResult(answerMap.get(questionBean.getQuestionId()).getAnswerResult());
		    	questionVo.setAnswerId(answerMap.get(questionBean.getQuestionId()).getAnswerId());
		    	questionVo.setMarkScore(answerMap.get(questionBean.getQuestionId()).getMarkScore());
		    	questionVo.setMarkText(answerMap.get(questionBean.getQuestionId()).getMarkText());
		    }
		    if(StringUtils.isNotBlank(questionVo.getAnswerResult())&&questionVo.getAnswerResult().equals(questionVo.getRightResult())){
		    	totalScore +=questionVo.getQuestionScore();
		    }
		}
		//自动阅卷
		bean.setTotalScore(totalScore);
		bean.setMarkTime(DateUtil.dateParse(new Date(),""));
		bean.setMarkUser("系统自动阅卷");
		bean.setPublishTime(DateUtil.dateParse(new Date(),""));
		bean.setStatus("4");
		return bean;
	}
	
	

	
}
