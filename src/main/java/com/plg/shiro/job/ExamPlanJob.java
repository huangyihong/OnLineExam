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
//		List<OmExamPlan>  list = service.selectByStatus("1");//待开考信息列表
//		for(OmExamPlan plan:list){
//			String begin_time = plan.getBeginTime();
//			Date beginDate = DateUtil.string2Date(begin_time, "yyyy-MM-dd HH:mm:ss");
//			if(beginDate.before(new Date())){//过了当前时间
//				plan.setStatus("2");//正在考试
//				service.updateByPrimaryKeySelective(plan);
//			}
//		}
	}
	
	//关闭考试
	public void endExam() throws Exception {
//		List<OmExamPlan>  list = service.selectByStatus("");//正在进行的考试信息列表
//		for(OmExamPlan plan:list){
////			String begin_time = plan.getBeginTime();
//			OmPaper paper = paperService.selectByPrimaryKey(plan.getPaperId());
//			if(paper==null){
//				continue;
//			}
//			int paperTime = paper.getPaperTime();
////			Date beginDate = DateUtil.string2Date(begin_time, "yyyy-MM-dd HH:mm:ss");
////			Calendar nowTime = Calendar.getInstance();
////			nowTime.add(Calendar.MINUTE, -paperTime);
////			Date nowdate = nowTime.getTime();
//			String autoMarkFlag = plan.getAutoMarkFlag();//是否自动阅卷
//			if(beginDate.before(nowdate)){//过了当前时间
////				plan.setStatus("3");//关闭考试
////				service.updateByPrimaryKeySelective(plan);
//				//自动提交答卷
//				String status = "1,2";//1开始答卷状态,2 提交答卷的
//				List<OmExamSubmit> submitList = submitService.findListByPlanId(plan.getPlanId(),plan.getPaperId(),status);
//				for(OmExamSubmit bean:submitList){
//					if("2".equals(bean.getStatus())&&!"1".equals(autoMarkFlag)) {
//						continue;
//					}
//					if(!"2".equals(bean.getStatus())){
//						bean.setStatus("2");//2 提交答卷
//						bean.setSubmitTime(DateUtil.dateParse(new Date(),""));
//					}
//					if("1".equals(autoMarkFlag)){//自动阅卷
//						autoMarkTotalScore(plan, paper, bean);
//					}
//					submitService.updateByPrimaryKeySelective(bean);
//				}
//			}
//		}
		List<OmExamPlan>  list = service.selectByStatus("");//考试信息列表
		for(OmExamPlan plan:list){
			OmPaper paper = paperService.selectByPrimaryKey(plan.getPaperId());
			if(paper==null){
				continue;
			}
			//考试时间
			int paperTime = paper.getPaperTime();
			Calendar nowTime = Calendar.getInstance();
			nowTime.add(Calendar.MINUTE, -paperTime);
			Date nowdate = nowTime.getTime();
			
			String autoMarkFlag = plan.getAutoMarkFlag();//是否自动阅卷
			//自动提交答卷
			String status = "1";
			List<OmExamSubmit> submitList = submitService.findListByPlanId(plan.getPlanId(),plan.getPaperId(),status);
			for(OmExamSubmit bean:submitList){
				Date beginDate = bean.getStartTime();
				if(beginDate.before(nowdate)){//过了当前时间
					bean.setStatus("2");//2 提交答卷
					bean.setSubmitTime(DateUtil.dateParse(new Date(),""));
				}else {
					continue;
				}
				if("1".equals(autoMarkFlag)){//自动阅卷
					bean = submitService.autoMarkTotalScore(plan, paper, bean);
				}
				submitService.updateByPrimaryKeySelective(bean);
			}	
		}
		
	}

}
