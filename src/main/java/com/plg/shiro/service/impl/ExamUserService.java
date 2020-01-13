package com.plg.shiro.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.plg.shiro.dao.OmExamUserMapper;
import com.plg.shiro.entity.OmExamUser;
import com.plg.shiro.entity.OmExamUserExample;
import com.plg.shiro.entity.OmUser;
import com.plg.shiro.service.IExamUserService;
import com.plg.shiro.util.DateUtil;
import com.plg.shiro.util.UUIDUtil;

@Service
public class ExamUserService implements IExamUserService {
	private static final Logger logger = LoggerFactory.getLogger(ExamUserService.class);

	@Resource
	private OmExamUserMapper omExamUserMapper;

	@Override
	public void insertExamUser(OmExamUser bean, String userIds, String groupIds) {
		//删除原来的记录
		deleteExample(bean);
		
		//插入用户
		if(StringUtils.isNotBlank(userIds)){
			List<String> userId_arr = Arrays.asList(userIds.split(","));
			for(String userId:userId_arr){
				bean.setExamUserId(UUIDUtil.getUUID());
				bean.setUserId(userId);
				bean.setGroupId("");
				bean.setCreateTime(DateUtil.dateParse(new Date(),""));
				omExamUserMapper.insertSelective(bean);
			}
		}
		//插入用户分组
		if(StringUtils.isNotBlank(groupIds)){
			List<String> groupId_arr = Arrays.asList(groupIds.split(","));
			for(String groupId:groupId_arr){
				bean.setExamUserId(UUIDUtil.getUUID());
				bean.setGroupId(groupId);
				bean.setUserId("");
				bean.setCreateTime(DateUtil.dateParse(new Date(),""));
				omExamUserMapper.insertSelective(bean);
			}
		}
		
		
	}

	private void deleteExample(OmExamUser bean) {
		OmExamUserExample example = new OmExamUserExample();
		OmExamUserExample.Criteria criteria = example.createCriteria();
		criteria.andPlanIdEqualTo(bean.getPlanId());
		criteria.andPaperIdEqualTo(bean.getPaperId());
		criteria.andExamUserTypeEqualTo(bean.getExamUserType());
		omExamUserMapper.deleteByExample(example);
	}

	@Override
	public List<OmExamUser> selectAll(String planId, String examUserType) {
		OmExamUserExample example = new OmExamUserExample();
		OmExamUserExample.Criteria criteria = example.createCriteria();
		criteria.andPlanIdEqualTo(planId);
		criteria.andExamUserTypeEqualTo(examUserType);
		return omExamUserMapper.selectByExample(example);
	}
	
	/**
	 * 获取用户授权的考试或者阅卷
	 */
	@Override
	public List<OmExamUser> selectUserExam(OmUser user, String examUserType) {
		OmExamUserExample example = new OmExamUserExample();
		OmExamUserExample.Criteria criteria = example.createCriteria();
		criteria.andExamUserTypeEqualTo(examUserType);
		criteria.andUserIdEqualTo(user.getUserId());
		if(StringUtils.isNotBlank(user.getGroupId())){
			OmExamUserExample.Criteria criteria2 = example.createCriteria();
			criteria2.andExamUserTypeEqualTo(examUserType);
			criteria2.andGroupIdEqualTo(user.getGroupId());
			example.or(criteria2);
		}
		return omExamUserMapper.selectByExample(example);
	}
}
