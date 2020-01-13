package com.plg.shiro.service.impl;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.plg.shiro.dao.OmQuestionExtMapper;
import com.plg.shiro.dao.OmQuestionImgMapper;
import com.plg.shiro.dao.OmQuestionMapper;
import com.plg.shiro.entity.OmPaperQuestion;
import com.plg.shiro.entity.OmQuestion;
import com.plg.shiro.entity.OmQuestionExample;
import com.plg.shiro.entity.OmQuestionImg;
import com.plg.shiro.entity.OmQuestionImgExample;
import com.plg.shiro.service.IQuestionService;
import com.plg.shiro.util.dwz.LayuiPage;

@Service
public class QuestionService implements IQuestionService {
	private static final Logger logger = LoggerFactory.getLogger(QuestionService.class);

	@Resource
	private OmQuestionMapper omQuestionMapper;
	
	@Resource
	private OmQuestionExtMapper omQuestionExtMapper;
	
	@Resource
	private OmQuestionImgMapper omQuestionImgMapper;
	
	@Override
	public int deleteByPrimaryKey(String questionId) {
		logger.info("删除题目：{}", questionId);
		return omQuestionMapper.deleteByPrimaryKey(questionId);
	}

	@Override
	public int insert(OmQuestion record) {
		logger.info("新增题目：{}", record.getQuestionId());
		record.setDeleted("0");
		return omQuestionMapper.insert(record);
	}
	
	@Override
	public int insert(OmQuestion record,String imgIds,String imgSrcs) {
		logger.info("新增题目：{}", record.getQuestionId());
		record.setDeleted("0");
		saveQuestionImg(record.getQuestionId(), imgIds, imgSrcs);
		return omQuestionMapper.insert(record);
	}

	private void saveQuestionImg(String questionId, String imgIds, String imgSrcs) {
		//新增图片
		if(StringUtils.isNoneBlank(imgIds)) {
			String[] imgId_arr = imgIds.split(",");
			String[] imgSrc_arr = imgSrcs.split(",");
			for(int i=0;i<imgId_arr.length;i++) {
				OmQuestionImg imgBean = new OmQuestionImg();
				imgBean.setId(imgId_arr[i]);
				imgBean.setImgSrc(imgSrc_arr[i]);
				imgBean.setQuestionId(questionId);
				imgBean.setImgOrder(i);
				omQuestionImgMapper.insert(imgBean);
			}
		}
	}
	
	private void deleteQuestionImg(String questionId) {
		//删除图片
		OmQuestionImgExample example = new OmQuestionImgExample();
		OmQuestionImgExample.Criteria criteria = example.createCriteria();
		criteria.andQuestionIdEqualTo(questionId);
		omQuestionImgMapper.deleteByExample(example);
	}

	@Override
	public OmQuestion selectByPrimaryKey(String questionId) {
		logger.info("查询题目：{}", questionId);
		return omQuestionMapper.selectByPrimaryKey(questionId);
	}
	
	@Override
	public List<OmQuestion> selectAll() {
		logger.info("查询所有题目");
		OmQuestionExample example = new OmQuestionExample();
		OmQuestionExample.Criteria criteria = example.createCriteria();
		criteria.andDeletedEqualTo("0");
		example.setOrderByClause("QUESTION_NAME");
		return omQuestionMapper.selectByExample(example);
	}
	
	@Override
	public int updateByPrimaryKeySelective(OmQuestion record) {
		logger.info("更新题目：{}", record.getQuestionId());
		return omQuestionMapper.updateByPrimaryKeySelective(record);
	}
	
	@Override
	public int updateByPrimaryKeySelective(OmQuestion record,String imgIds,String imgSrcs) {
		logger.info("更新题目：{}", record.getQuestionId());
		deleteQuestionImg(record.getQuestionId());
		saveQuestionImg(record.getQuestionId(), imgIds, imgSrcs);
		return omQuestionMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<OmQuestion> findPageList(HttpServletRequest request, LayuiPage page) {
		OmQuestionExample example = new OmQuestionExample();
		OmQuestionExample.Criteria criteria = example.createCriteria();
		String questionName = request.getParameter("questionName");
		if(StringUtils.isNoneBlank(questionName)){
			criteria.andQuestionNameLike("%"+questionName+"%");
		}
		String questionType = request.getParameter("questionType");
		if(StringUtils.isNoneBlank(questionType)){
			criteria.andQuestionTypeEqualTo(questionType);
		}
		String courseId = request.getParameter("courseId");
		if(StringUtils.isNoneBlank(courseId)){
			criteria.andCourseIdEqualTo(courseId);
		}
		criteria.andDeletedEqualTo("0");
		example.setLimitPageSize(page.getLimit());
		example.setLimitStart(page.limitStart());
		page.setTotalCount(omQuestionMapper.countByExample(example));
		example.setOrderByClause("create_time desc,UPDATE_TIME desc,question_name");
		return omQuestionMapper.selectByExample(example);
	}

	@Override
	public void deleteBatch(String ids) {
		OmQuestionExample example = new OmQuestionExample();
		OmQuestionExample.Criteria c = example.createCriteria();
        c.andQuestionIdIn(Arrays.asList(ids.split(",")));
        List<OmQuestion> list = omQuestionMapper.selectByExample(example);
        for(OmQuestion bean:list){
        	bean.setDeleted("1");//伪删除
        	omQuestionMapper.updateByPrimaryKey(bean);
        }
        //omQuestionMapper.deleteByExample(example);
	}

	@Override
	public List<OmQuestion> selectAuto(String questionType,String courseId, int count) {
		OmQuestionExample example = new OmQuestionExample();
		OmQuestionExample.Criteria criteria = example.createCriteria();
		criteria.andQuestionTypeEqualTo(questionType);
		criteria.andCourseIdEqualTo(courseId);
		criteria.andDeletedEqualTo("0");
		example.setLimitPageSize(count);
		return omQuestionExtMapper.selectByExample(example);
	}

	@Override
	public List<OmQuestion> selectPaperQuestion(String paperId) {
		return omQuestionExtMapper.selectPaperQuestion(paperId);
	}

	@Override
	public List<OmQuestion> selectQuestionByIds(String ids) {
		OmQuestionExample example = new OmQuestionExample();
		OmQuestionExample.Criteria c = example.createCriteria();
        c.andQuestionIdIn(Arrays.asList(ids.split(",")));
        return omQuestionMapper.selectByExample(example);
	}

	@Override
	public List<OmQuestion> selectUserPaperQuestion(String paperId, String answerUserId) {
		OmPaperQuestion paperQuestion = new OmPaperQuestion();
		paperQuestion.setPaperId(paperId);
		paperQuestion.setUserId(answerUserId);
		return omQuestionExtMapper.selectUserPaperQuestion(paperQuestion);
	}

	@Override
	public List<OmQuestionImg> selectQuestionImgByQuestionId(String questionId) {
		OmQuestionImgExample example = new OmQuestionImgExample();
		OmQuestionImgExample.Criteria criteria = example.createCriteria();
		criteria.andQuestionIdEqualTo(questionId);
		example.setOrderByClause("IMG_ORDER");
		return omQuestionImgMapper.selectByExample(example);
	}

}
