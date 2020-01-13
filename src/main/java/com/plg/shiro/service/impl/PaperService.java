package com.plg.shiro.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.plg.shiro.dao.OmPaperDetailMapper;
import com.plg.shiro.dao.OmPaperMapper;
import com.plg.shiro.dao.OmPaperQuestionMapper;
import com.plg.shiro.entity.OmPaper;
import com.plg.shiro.entity.OmPaperDetail;
import com.plg.shiro.entity.OmPaperDetailExample;
import com.plg.shiro.entity.OmPaperExample;
import com.plg.shiro.entity.OmPaperQuestion;
import com.plg.shiro.entity.OmPaperQuestionExample;
import com.plg.shiro.entity.OmQuestion;
import com.plg.shiro.service.IPaperService;
import com.plg.shiro.service.IQuestionService;
import com.plg.shiro.util.UUIDUtil;
import com.plg.shiro.util.dwz.LayuiPage;

@Service
public class PaperService implements IPaperService {
	private static final Logger logger = LoggerFactory.getLogger(PaperService.class);

	@Resource
	private OmPaperMapper omPaperMapper;
	
	@Resource
	private OmPaperQuestionMapper omPaperQuestionMapper;
	
	@Resource
	private IQuestionService questionService;
	
	@Resource
	private OmPaperDetailMapper omPaperDetailMapper;
	
	@Override
	public int deleteByPrimaryKey(String paperId) {
		logger.info("删除试卷：{}", paperId);
		return omPaperMapper.deleteByPrimaryKey(paperId);
	}

	@Override
	public int insert(OmPaper record) {
		logger.info("新增试卷：{}", record.getPaperId());
		record.setDeleted("0");
		return omPaperMapper.insert(record);
	}

	@Override
	public OmPaper selectByPrimaryKey(String paperId) {
		logger.info("查询试卷：{}", paperId);
		return omPaperMapper.selectByPrimaryKey(paperId);
	}
	
	
	@Override
	public List<OmPaper> selectAll() {
		logger.info("查询所有试卷");
		OmPaperExample example = new OmPaperExample();
		OmPaperExample.Criteria criteria = example.createCriteria();
		criteria.andDeletedEqualTo("0");
		example.setOrderByClause("PAPER_NAME");
		return omPaperMapper.selectByExample(example);
	}
	
	@Override
	public int updateByPrimaryKeySelective(OmPaper record) {
		logger.info("更新试卷：{}", record.getPaperId());
		return omPaperMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<OmPaper> findPageList(HttpServletRequest request, LayuiPage page) {
		OmPaperExample example = new OmPaperExample();
		OmPaperExample.Criteria criteria = example.createCriteria();
		String paperName = request.getParameter("paperName");
		if(StringUtils.isNoneBlank(paperName)){
			criteria.andPaperNameLike("%"+paperName+"%");
		}
		criteria.andDeletedEqualTo("0");
		example.setLimitPageSize(page.getLimit());
		example.setLimitStart(page.limitStart());
		page.setTotalCount(omPaperMapper.countByExample(example));
		example.setOrderByClause("PAPER_NAME");
		return omPaperMapper.selectByExample(example);
	}

	@Override
	public void deleteBatch(String ids) {
		OmPaperExample example = new OmPaperExample();
		OmPaperExample.Criteria c = example.createCriteria();
        c.andPaperIdIn(Arrays.asList(ids.split(",")));
        List<OmPaper> list = omPaperMapper.selectByExample(example);
        for(OmPaper bean:list){
        	bean.setDeleted("1");//伪删除
        	omPaperMapper.updateByPrimaryKey(bean);
        }
//        omPaperMapper.deleteByExample(example);
	}

	@Override
	public void insert(OmPaper record, String questionIds) {
		logger.info("新增人工选择的试卷：{}", record.getPaperId());
		//插入试题关联信息
		int paperScore = insertOmPaperQuestion(record, questionIds);
		record.setPaperScore(paperScore);
		record.setDeleted("0");
		omPaperMapper.insert(record);
	}

	private int insertOmPaperQuestion(OmPaper record, String questionIds) {
		int paperScore = 0;
		if(StringUtils.isNotBlank(questionIds)){
			List<String> questionId_arr = Arrays.asList(questionIds.split(","));
			int i=1;
//			for(String questionId:questionId_arr){
//				OmPaperQuestion bean = new OmPaperQuestion();
//				bean.setPaperQuestionId(UUIDUtil.getUUID());
//				bean.setPaperId(record.getPaperId());
//				bean.setQuestionId(questionId);
//				bean.setQuestionOrder(i);
//				omPaperQuestionMapper.insert(bean);
//				i++;
//			}
			//试卷总分
			List<OmQuestion> questionList = questionService.selectQuestionByIds(questionIds);
			for(OmQuestion question:questionList){
				String questionId = question.getQuestionId();
				OmPaperQuestion bean = new OmPaperQuestion();
				bean.setPaperQuestionId(UUIDUtil.getUUID());
				bean.setPaperId(record.getPaperId());
				bean.setQuestionId(questionId);
				bean.setQuestionOrder(i);
				omPaperQuestionMapper.insert(bean);
				paperScore +=question.getQuestionScore();
				i++;
			}
		}
		return paperScore;
	}

	@Override
	public void updateByPrimaryKeySelective(OmPaper record, String questionIds) {
		logger.info("更新人工选择的试卷：{}", record.getPaperId());
		//删除原来的试题关联信息
		deleteOmPaperQuestionByPaperId(record.getPaperId());
		//插入试题关联信息
		int paperScore = insertOmPaperQuestion(record, questionIds);
		record.setPaperScore(paperScore);
		omPaperMapper.updateByPrimaryKeySelective(record);
	}
	
	private void deleteOmPaperQuestionByPaperId(String paperId) {
		OmPaperQuestionExample example = new OmPaperQuestionExample();
		OmPaperQuestionExample.Criteria c = example.createCriteria();
        c.andPaperIdEqualTo(paperId);
        omPaperQuestionMapper.deleteByExample(example);
	}

	//新增随机试卷
	@Override
	public int insertAuto(OmPaper record) {
		logger.info("新增试卷：{}", record.getPaperId());
		int paperScore = insertPaperQuestionAuto(record);
		record.setPaperScore(paperScore);
		record.setDeleted("0");
		return omPaperMapper.insert(record);
	}

	//新增随机试卷的试题关联信息
	private int insertPaperQuestionAuto(OmPaper record) {
		int paperScore = 0;
		List<OmQuestion> questionList = new ArrayList<OmQuestion>();
		if(record.getSingleCount()>0){
			questionList.addAll(questionService.selectAuto("1",record.getCourseId(),record.getSingleCount()));
		}
		if(record.getMultiCount()>0){
			questionList.addAll(questionService.selectAuto("2",record.getCourseId(), record.getMultiCount()));
		}
		if(record.getJudgeCount()>0){
			questionList.addAll(questionService.selectAuto("3",record.getCourseId(), record.getJudgeCount()));
		}
		if(record.getBlankCount()>0){
			questionList.addAll(questionService.selectAuto("4",record.getCourseId(), record.getBlankCount()));
		}
		if(record.getAnswerCount()>0){
			questionList.addAll(questionService.selectAuto("5",record.getCourseId(), record.getAnswerCount()));
		}
		int i=1;
		for(OmQuestion question:questionList){
			OmPaperQuestion bean = new OmPaperQuestion();
			bean.setPaperQuestionId(UUIDUtil.getUUID());
			bean.setPaperId(record.getPaperId());
			bean.setQuestionId(question.getQuestionId());
			bean.setQuestionOrder(i);
			omPaperQuestionMapper.insert(bean);
			paperScore +=question.getQuestionScore();
			i++;
		}
		return paperScore;
	}

	//保存随机试卷
	@Override
	public void updateAuto(OmPaper record) {
		OmPaper oldBean = selectByPrimaryKey(record.getPaperId());
		if(record.getSingleCount()!=oldBean.getSingleCount()||record.getMultiCount()!=oldBean.getMultiCount()||
				record.getJudgeCount()!=oldBean.getJudgeCount()||record.getBlankCount()!=oldBean.getBlankCount()||
				record.getAnswerCount()!=oldBean.getAnswerCount()||!record.getCourseId().equals(oldBean.getCourseId())){
			//删除原来的试题关联信息
			deleteOmPaperQuestionByPaperId(record.getPaperId());
			//插入试题关联信息
			int paperScore = insertPaperQuestionAuto(record);
			record.setPaperScore(paperScore);
		}
		omPaperMapper.updateByPrimaryKeySelective(record);
		
	}

	//重新生成试卷
	@Override
	public void rebuild(OmPaper record) {
		//删除原来的试题关联信息
		deleteOmPaperQuestionByPaperId(record.getPaperId());
		//插入试题关联信息
		int paperScore = insertPaperQuestionAuto(record);
		record.setPaperScore(paperScore);
		omPaperMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<OmPaperDetail> findPaperDeatilByPaperId(String paperId) {
		OmPaperDetailExample example = new OmPaperDetailExample();
		OmPaperDetailExample.Criteria c = example.createCriteria();
        c.andPaperIdEqualTo(paperId);
        example.setOrderByClause("order_num");
		return omPaperDetailMapper.selectByExample(example);
	}

	@Override
	public void insertNewAuto(OmPaper bean, List<OmPaperDetail> detailDTOList) {
		OmPaper bean1 = insertPaperDetail(bean, detailDTOList);
		bean1.setDeleted("0");
		omPaperMapper.insert(bean1);
		
	}

	@Override
	public void updateNewAuto(OmPaper bean, List<OmPaperDetail> detailDTOList) {
		//删除原来的试卷试题详细信息
		deleteOmPaperDetailByPaperId(bean.getPaperId());
		OmPaper bean1 = insertPaperDetail(bean, detailDTOList);
		OmPaper oldBean = this.selectByPrimaryKey(bean.getPaperId());
		bean1.setCreateTime(oldBean.getCreateTime());
		bean1.setDeleted(oldBean.getDeleted());
		omPaperMapper.updateByPrimaryKey(bean1);
	}

	private OmPaper insertPaperDetail(OmPaper bean, List<OmPaperDetail> detailDTOList) {
		int paperScore = 0;
		int singleCount = 0;
		int multiCount = 0;
		int judgeCount = 0;
		int blankCount = 0;
		int answerCount = 0;
		for(OmPaperDetail detailBean:detailDTOList){
			paperScore +=detailBean.getQuestionScore()*detailBean.getQuestionCount();
			String questionType = detailBean.getQuestionType();
  			switch (questionType) {
  				case "1":
  					singleCount +=detailBean.getQuestionCount();
  				    break;
  				case "2":
  					multiCount +=detailBean.getQuestionCount();
  				    break;
  				case "3":
  					judgeCount +=detailBean.getQuestionCount();
  				    break;
  				case "4":
  					blankCount +=detailBean.getQuestionCount();
  				    break;
  				case "5":
  					answerCount +=detailBean.getQuestionCount();
  				    break;    
  			}
			detailBean.setId(UUIDUtil.getUUID());
			detailBean.setPaperId(bean.getPaperId());
			omPaperDetailMapper.insert(detailBean);
		}
		bean.setPaperScore(paperScore);
		bean.setSingleCount(singleCount);
		bean.setMultiCount(multiCount);
		bean.setJudgeCount(judgeCount);
		bean.setBlankCount(blankCount);
		bean.setAnswerCount(answerCount);
		return bean;
	}
	
	private void deleteOmPaperDetailByPaperId(String paperId) {
		OmPaperDetailExample example = new OmPaperDetailExample();
		OmPaperDetailExample.Criteria c = example.createCriteria();
        c.andPaperIdEqualTo(paperId);
        omPaperDetailMapper.deleteByExample(example);
	}

	@Override
	public List<OmQuestion> insertPaperQuestionAuto(String paperId, String answerUserId) {
		List<OmPaperDetail> detailList = this.findPaperDeatilByPaperId(paperId); 
		List<OmQuestion> questionList1 = new ArrayList<OmQuestion>();
  		List<OmQuestion> questionList2 = new ArrayList<OmQuestion>();
  		List<OmQuestion> questionList3 = new ArrayList<OmQuestion>();
  		List<OmQuestion> questionList4 = new ArrayList<OmQuestion>();
  		List<OmQuestion> questionList5 = new ArrayList<OmQuestion>();
		for(OmPaperDetail detailBean:detailList){
			List<OmQuestion> list = questionService.selectAuto(detailBean.getQuestionType(),detailBean.getCourseId(),detailBean.getQuestionCount());
			String questionType = detailBean.getQuestionType();
			for(OmQuestion question:list){
				question.setQuestionScore(detailBean.getQuestionScore());
	  			switch (questionType) {
	  				case "1":
	  					questionList1.add(question);
	  				    break;
	  				case "2":
	  					questionList2.add(question);
	  				    break;
	  				case "3":
	  					questionList3.add(question);
	  				    break;
	  				case "4":
	  					questionList4.add(question);
	  				    break;
	  				case "5":
	  					questionList5.add(question);
	  				    break;    
	  			}
			}
		}
		Map<String,List<OmQuestion>> map = new HashMap<String,List<OmQuestion>>();
		map.put("questionList1", questionList1);
		map.put("questionList2", questionList2);
		map.put("questionList3", questionList3);
		map.put("questionList4", questionList4);
		map.put("questionList5", questionList5);
		List<OmQuestion> questionListAll = new ArrayList<OmQuestion>();
		int i=1;
		for(int j=1;j<=5;j++){
			List<OmQuestion> questionList = map.get("questionList"+j);
			for(OmQuestion question:questionList){
				OmPaperQuestion bean = new OmPaperQuestion();
				bean.setPaperQuestionId(UUIDUtil.getUUID());
				bean.setPaperId(paperId);
				bean.setQuestionId(question.getQuestionId());
				bean.setQuestionOrder(i);
				bean.setUserId(answerUserId);
				bean.setQuestionScore(question.getQuestionScore());
				omPaperQuestionMapper.insert(bean);
				questionListAll.add(question);
				i++;
			}
		}
		return questionListAll;
		
		
	}

}
