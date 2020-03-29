package com.plg.shiro.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.plg.shiro.dao.OmExamPlanMapper;
import com.plg.shiro.dao.OmExamPlanVoMapper;
import com.plg.shiro.entity.OmExamPlan;
import com.plg.shiro.entity.OmExamPlanExample;
import com.plg.shiro.entity.OmExamPlanVo;
import com.plg.shiro.entity.OmExamPlanVoExample;
import com.plg.shiro.entity.OmExamSubmit;
import com.plg.shiro.entity.OmExamUser;
import com.plg.shiro.entity.OmUser;
import com.plg.shiro.service.IExamPlanService;
import com.plg.shiro.service.IExamUserService;
import com.plg.shiro.util.dwz.LayuiPage;

@Service
public class ExamPlanService implements IExamPlanService {
	private static final Logger logger = LoggerFactory.getLogger(ExamPlanService.class);

	@Resource
	private OmExamPlanMapper omExamPlanMapper;
	
	@Resource
	private IExamUserService examUserService;
	
	@Resource
	private OmExamPlanVoMapper omExamPlanVoMapper;
	
	@Resource
	private ExamSubmitService submitService;
	
	@Override
	public int deleteByPrimaryKey(String planId) {
		logger.info("删除考试安排：{}", planId);
		return omExamPlanMapper.deleteByPrimaryKey(planId);
	}

	@Override
	public int insert(OmExamPlan record) {
		logger.info("新增考试安排：{}", record.getPlanId());
		record.setDeleted("0");
		return omExamPlanMapper.insert(record);
	}

	@Override
	public OmExamPlan selectByPrimaryKey(String planId) {
		logger.info("查询考试安排：{}", planId);
		return omExamPlanMapper.selectByPrimaryKey(planId);
	}
	
	
	@Override
	public List<OmExamPlan> selectAll() {
		logger.info("查询所有考试安排");
		OmExamPlanExample example = new OmExamPlanExample();
		OmExamPlanExample.Criteria criteria = example.createCriteria();
		criteria.andDeletedEqualTo("0");
		example.setOrderByClause("CREATE_TIME desc");
		return omExamPlanMapper.selectByExample(example);
	}
	
	@Override
	public int updateByPrimaryKeySelective(OmExamPlan record) {
		logger.info("更新考试安排：{}", record.getPlanId());
		return omExamPlanMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<OmExamPlan> findPageList(HttpServletRequest request, LayuiPage page) {
		OmExamPlanExample example = new OmExamPlanExample();
		OmExamPlanExample.Criteria criteria = example.createCriteria();
		String planName = request.getParameter("planName");
		if(StringUtils.isNoneBlank(planName)){
			criteria.andPlanNameLike("%"+planName+"%");
		}
		criteria.andDeletedEqualTo("0");
		example.setLimitPageSize(page.getLimit());
		example.setLimitStart(page.limitStart());
		page.setTotalCount(omExamPlanMapper.countByExample(example));
		example.setOrderByClause("CREATE_TIME desc");
		return omExamPlanMapper.selectByExample(example);
	}

	@Override
	public void deleteBatch(String ids) {
		OmExamPlanExample example = new OmExamPlanExample();
		OmExamPlanExample.Criteria c = example.createCriteria();
        c.andPlanIdIn(Arrays.asList(ids.split(",")));
        List<OmExamPlan> list = omExamPlanMapper.selectByExample(example);
        for(OmExamPlan bean:list){
        	bean.setDeleted("1");//伪删除
        	omExamPlanMapper.updateByPrimaryKey(bean);
        }
//        omExamPlanMapper.deleteByExample(example);
	}
	
	@Override
	public List<OmExamPlanVo> findVoPageList(HttpServletRequest request, LayuiPage page) {
		OmExamPlanVoExample example = new OmExamPlanVoExample();
		OmExamPlanVoExample.Criteria criteria = example.createCriteria();
		String planName = request.getParameter("planName");
		if(StringUtils.isNoneBlank(planName)){
			criteria.andPlanNameLike("%"+planName+"%");
		}
//		String beginTime = request.getParameter("beginTime");
//		if(StringUtils.isNoneBlank(beginTime)){
//			criteria.andBeginTimeGreaterThanOrEqualTo(beginTime+" 00:00:00");
//			criteria.andBeginTimeLessThanOrEqualTo(beginTime+" 24:00:00");
//		}
		example.setLimitPageSize(page.getLimit());
		example.setLimitStart(page.limitStart());
		page.setTotalCount(omExamPlanVoMapper.countByExample(example));
		example.setOrderByClause("CREATE_TIME desc");
		return omExamPlanVoMapper.selectByExample(example);
	}
	
	@Override
	public List<OmExamPlanVo> selectVoAll() {
		logger.info("查询所有考试安排");
		OmExamPlanVoExample example = new OmExamPlanVoExample();
		OmExamPlanVoExample.Criteria criteria = example.createCriteria();
		example.setOrderByClause("CREATE_TIME desc");
		return omExamPlanVoMapper.selectByExample(example);
	}

	@Override
	public List<OmExamPlanVo> findUserPageList(HttpServletRequest request, LayuiPage page, OmUser user, String examUserType) {
		//获取当前用户被授权的考试安排1或者阅卷安排2
		List<OmExamUser> listExam = examUserService.selectUserExam(user, examUserType);
		List<String> planIdList = new ArrayList<String>();
		for(OmExamUser examUser:listExam){
			planIdList.add(examUser.getPlanId());
		}
		if(planIdList.size()>0){
			OmExamPlanVoExample example = new OmExamPlanVoExample();
			OmExamPlanVoExample.Criteria criteria = example.createCriteria();
			criteria.andPlanIdIn(planIdList);
			example.setLimitPageSize(page.getLimit());
			example.setLimitStart(page.limitStart());
			example.setOrderByClause("CREATE_TIME desc");
			page.setTotalCount(omExamPlanVoMapper.countByExample(example));
			return omExamPlanVoMapper.selectByExample(example);
		}else{
			return null;
		}
	}

	@Override
	public List<OmExamPlanVo> findUserList(HttpServletRequest request, OmUser user, String examUserType) {
		//examUserType 获取当前用户被授权的考试安排1或者阅卷安排2
		List<OmExamUser> listExam = examUserService.selectUserExam(user, examUserType);
		List<String> planIdList = new ArrayList<String>();
		for(OmExamUser examUser:listExam){
			if("1".equals(examUserType)){//考试人员，已提交答卷，不显示
				OmExamSubmit submitBean =submitService.selectByUserId(examUser.getPlanId(), "", user.getUserId());
				if(submitBean==null||"1".equals(submitBean.getStatus())){//开始答题
					planIdList.add(examUser.getPlanId());
				}
			}
		}
		if(planIdList.size()>0){
			OmExamPlanVoExample example = new OmExamPlanVoExample();
			OmExamPlanVoExample.Criteria criteria = example.createCriteria();
			criteria.andPlanIdIn(planIdList);
//			String status = request.getParameter("status");
//			if(StringUtils.isNotBlank(status)){
//				criteria.andStatusIn(Arrays.asList(status.split(",")));
//			}
			example.setOrderByClause("CREATE_TIME desc");
			return omExamPlanVoMapper.selectByExample(example);
		}else{
			return null;
		}
	}

	@Override
	public List<OmExamPlan> selectByStatus(String status) {
		OmExamPlanExample example = new OmExamPlanExample();
		OmExamPlanExample.Criteria criteria = example.createCriteria();
//		criteria.andStatusEqualTo(status);
		criteria.andDeletedEqualTo("0");
		example.setOrderByClause("CREATE_TIME desc");
		return omExamPlanMapper.selectByExample(example);
	}
	
	@Override
	public List<OmExamPlanVo> selectVoByStatus(String status) {
		OmExamPlanVoExample example = new OmExamPlanVoExample();
		OmExamPlanVoExample.Criteria criteria = example.createCriteria();
//		if(StringUtils.isNotBlank(status)){
//			criteria.andStatusIn(Arrays.asList(status.split(",")));
//		}
		example.setOrderByClause("CREATE_TIME desc");
		return omExamPlanVoMapper.selectByExample(example);
	}

	@Override
	public OmExamPlanVo selectVoByPrimaryKey(String planId) {
		return omExamPlanVoMapper.selectByPrimaryKey(planId);
	}

}
