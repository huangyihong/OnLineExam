package com.plg.shiro.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.plg.shiro.dao.OmExamSubmitMapper;
import com.plg.shiro.dao.OmExamSubmitVoMapper;
import com.plg.shiro.entity.OmCourseExample;
import com.plg.shiro.entity.OmExamSubmit;
import com.plg.shiro.entity.OmExamSubmitExample;
import com.plg.shiro.entity.OmExamUser;
import com.plg.shiro.entity.OmUser;
import com.plg.shiro.entity.Vo.OmExamSubmitVo;
import com.plg.shiro.entity.Vo.OmExamSubmitVoExample;
import com.plg.shiro.service.IExamSubmitService;
import com.plg.shiro.service.IExamUserService;
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
		String planStatus = request.getParameter("plan_status");
		if(StringUtils.isNotBlank(planStatus)){
			criteria.andPlanStatusIn(Arrays.asList(planStatus.split(",")));
		}
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
		example.setOrderByClause("START_TIME desc");
		if("3,4".equals(status)){//发布成绩页面
			example.setOrderByClause("status,START_TIME desc");
		}
		return omExamSubmitVoMapper.selectByExample(example);
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
	
	

	
}
