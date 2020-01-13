package com.plg.shiro.job;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.plg.shiro.entity.OmExamAnswer;
import com.plg.shiro.entity.OmExamPlan;
import com.plg.shiro.entity.OmExamSubmit;
import com.plg.shiro.entity.OmPaper;
import com.plg.shiro.entity.OmQuestion;
import com.plg.shiro.entity.OmQuestionImg;
import com.plg.shiro.entity.Vo.OmQuestionVo;
import com.plg.shiro.service.IExamAnswerService;
import com.plg.shiro.service.IExamPlanService;
import com.plg.shiro.service.IExamSubmitService;
import com.plg.shiro.service.IPaperService;
import com.plg.shiro.service.IQuestionService;
import com.plg.shiro.util.DateUtil;

@Component("examPlanJob")
public class ExamPlanJob {
	@Resource
	private IExamPlanService service;
	
	@Resource
	private IPaperService paperService;
	
	@Resource
	private IExamSubmitService submitService;
	
	@Resource
	private IExamAnswerService answerService;
	
	@Resource
	private IQuestionService questionService;

	//开考考试
	public void startExam() throws Exception {
		List<OmExamPlan>  list = service.selectByStatus("1");//待开考信息列表
		for(OmExamPlan plan:list){
			String begin_time = plan.getBeginTime();
			Date beginDate = DateUtil.string2Date(begin_time, "yyyy-MM-dd HH:mm:ss");
			if(beginDate.before(new Date())){//过了当前时间
				plan.setStatus("2");//正在考试
				service.updateByPrimaryKeySelective(plan);
			}
		}
	}
	
	//关闭考试
	public void endExam() throws Exception {
		List<OmExamPlan>  list = service.selectByStatus("2");//正在进行的考试信息列表
		for(OmExamPlan plan:list){
			String begin_time = plan.getBeginTime();
			OmPaper paper = paperService.selectByPrimaryKey(plan.getPaperId());
			if(paper==null){
				continue;
			}
			int paperTime = paper.getPaperTime();
			Date beginDate = DateUtil.string2Date(begin_time, "yyyy-MM-dd HH:mm:ss");
			Calendar nowTime = Calendar.getInstance();
			nowTime.add(Calendar.MINUTE, -paperTime);
			Date nowdate = nowTime.getTime();
			String autoMarkFlag = plan.getAutoMarkFlag();//是否自动阅卷
			if(beginDate.before(nowdate)){//过了当前时间
				plan.setStatus("3");//关闭考试
				service.updateByPrimaryKeySelective(plan);
				//自动提交答卷
				String status = "1,2";//1开始答卷状态,2 提交答卷的
				List<OmExamSubmit> submitList = submitService.findListByPlanId(plan.getPlanId(),plan.getPaperId(),status);
				for(OmExamSubmit bean:submitList){
					if("2".equals(bean.getStatus())&&!"1".equals(autoMarkFlag)) {
						continue;
					}
					bean.setStatus("2");//2 提交答卷
					bean.setSubmitTime(DateUtil.dateParse(new Date(),""));
					if("1".equals(autoMarkFlag)){//自动阅卷
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
					}
					submitService.updateByPrimaryKeySelective(bean);
				}
			}
		}
	}

}
