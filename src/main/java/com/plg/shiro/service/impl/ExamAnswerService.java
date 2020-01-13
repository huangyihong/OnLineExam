package com.plg.shiro.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.plg.shiro.dao.OmExamAnswerMapper;
import com.plg.shiro.entity.OmExamAnswer;
import com.plg.shiro.entity.OmExamAnswerExample;
import com.plg.shiro.service.IExamAnswerService;
import com.plg.shiro.util.DateUtil;
import com.plg.shiro.util.UUIDUtil;

@Service
public class ExamAnswerService implements IExamAnswerService {
	private static final Logger logger = LoggerFactory.getLogger(ExamAnswerService.class);

	@Resource
	private OmExamAnswerMapper omExamAnswerMapper;
	
	@Override
	public int deleteByPrimaryKey(String answerId) {
		return omExamAnswerMapper.deleteByPrimaryKey(answerId);
	}

	@Override
	public int insert(OmExamAnswer record) {
		return omExamAnswerMapper.insert(record);
	}

	@Override
	public OmExamAnswer selectByPrimaryKey(String answerId) {
		return omExamAnswerMapper.selectByPrimaryKey(answerId);
	}
	
	
	@Override
	public List<OmExamAnswer> selectAll() {
		OmExamAnswerExample example = new OmExamAnswerExample();
		OmExamAnswerExample.Criteria criteria = example.createCriteria();
		return omExamAnswerMapper.selectByExample(example);
	}
	
	@Override
	public int updateByPrimaryKeySelective(OmExamAnswer record) {
		return omExamAnswerMapper.updateByPrimaryKeySelective(record);
	}


	@Override
	public void deleteBatch(String ids) {
		OmExamAnswerExample example = new OmExamAnswerExample();
		OmExamAnswerExample.Criteria c = example.createCriteria();
        c.andAnswerIdIn(Arrays.asList(ids.split(",")));
        omExamAnswerMapper.deleteByExample(example);
	}

	@Override
	public void saveOrUpdate(OmExamAnswer bean) {
		// TODO Auto-generated method stub
		OmExamAnswer oldBean =selectByUserQuestionID(bean.getUserId(), bean.getPlanId(),bean.getPaperId(),bean.getQuestionId());
		if(oldBean!=null){
			oldBean.setAnswerResult(bean.getAnswerResult());
			updateByPrimaryKeySelective(oldBean);
		}else{
			bean.setAnswerId(UUIDUtil.getUUID());
			bean.setCreateTime(DateUtil.dateParse(new Date(),""));
			insert(bean);
		}
	}

	@Override
	public OmExamAnswer selectByUserQuestionID(String userId, String planId,String paperId,String questionId) {
		OmExamAnswerExample example = new OmExamAnswerExample();
		OmExamAnswerExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		criteria.andPlanIdEqualTo(planId);
		criteria.andPaperIdEqualTo(paperId);
		criteria.andQuestionIdEqualTo(questionId);
		List<OmExamAnswer> list = omExamAnswerMapper.selectByExample(example);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public List<OmExamAnswer> selectByUserPaperID(String userId, String planId,String paperId) {
		OmExamAnswerExample example = new OmExamAnswerExample();
		OmExamAnswerExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		criteria.andPlanIdEqualTo(planId);
		criteria.andPaperIdEqualTo(paperId);
		return omExamAnswerMapper.selectByExample(example);
	}

	@Override
	public void updateBatch(List<OmExamAnswer> list) {
		for(OmExamAnswer record:list){
			omExamAnswerMapper.updateByPrimaryKeySelective(record);
		}
	}

}
