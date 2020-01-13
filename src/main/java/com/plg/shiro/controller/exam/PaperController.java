package com.plg.shiro.controller.exam;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plg.shiro.entity.OmCourse;
import com.plg.shiro.entity.OmExamAnswer;
import com.plg.shiro.entity.OmExamPlan;
import com.plg.shiro.entity.OmPaper;
import com.plg.shiro.entity.OmPaperDetail;
import com.plg.shiro.entity.OmQuestion;
import com.plg.shiro.entity.OmQuestionImg;
import com.plg.shiro.entity.OmUser;
import com.plg.shiro.entity.Vo.OmExamSubmitVo;
import com.plg.shiro.entity.Vo.OmQuestionVo;
import com.plg.shiro.service.ICourseService;
import com.plg.shiro.service.IExamAnswerService;
import com.plg.shiro.service.IExamPlanService;
import com.plg.shiro.service.IExamSubmitService;
import com.plg.shiro.service.IPaperService;
import com.plg.shiro.service.IQuestionService;
import com.plg.shiro.util.AjaxUtil;
import com.plg.shiro.util.DateUtil;
import com.plg.shiro.util.DownloadUtils;
import com.plg.shiro.util.UUIDUtil;
import com.plg.shiro.util.WordExport;
import com.plg.shiro.util.WordUtils;
import com.plg.shiro.util.dwz.AjaxObject;
import com.plg.shiro.util.dwz.LayuiPage;

/**
 * 试卷管理
 *
 */
@Controller
@RequestMapping({ "/admin/exam/omPaper" })
public class PaperController {
	private static Logger logger = LoggerFactory.getLogger(PaperController.class);
	
	@Resource
	private IPaperService service;
	
	@Resource
	private IQuestionService questionService;
	
	@Resource
	private IExamPlanService planService;
	
	@Resource
	private IExamAnswerService answerService;
	
	@Resource
	private ICourseService courseService;
	
	@Resource
	private IExamSubmitService submitService;
	
	private static final String PAPER_PATH = "admin/exam/omPaper/";
	
	/**
	 * 试卷管理列表页
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/list" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String list(HttpServletRequest request) {
		return PAPER_PATH+"list";
	}
	
	@RequestMapping(value = { "/selectPaper" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String selectPaper(HttpServletRequest request) {
		return PAPER_PATH+"selectPaper";
	}
	
	//获取列表数据
  	@RequestMapping("getList")
    @ResponseBody
  	public AjaxObject getList(HttpServletRequest request,LayuiPage page) throws Exception {
  	  	AjaxObject result = AjaxObject.newOk("success");
  			try {
  				List<OmPaper> list = service.findPageList(request, page);
  				result.setData(list);
  				result.setTotal(page.getTotalCount());
  			} catch(Exception e) {
  				return AjaxObject.newError(e.getMessage());
  			}
  			return result;
  	}
  	
  	 //新增修改页面
  	@RequestMapping(value = { "/create" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
  	public String create(HttpServletRequest request) {
  		String paperId = request.getParameter("paperId");
  		String addMode = request.getParameter("addMode");
  		OmPaper bean = new OmPaper();
  		List<OmPaperDetail> detailList = new ArrayList<OmPaperDetail>();
		if(StringUtils.isNotBlank(paperId)){
			bean = service.selectByPrimaryKey(paperId);
			if(bean!=null){
				addMode = bean.getAddMode();
				if("1".equals(addMode)){//手工添加
					List<OmQuestion> questionList = questionService.selectPaperQuestion(bean.getPaperId());
					request.setAttribute("questionList", questionList);
				}else{
					//随机试卷  各类别试题列表
					detailList = service.findPaperDeatilByPaperId(bean.getPaperId()); 
				}
			}
    	}
		if(StringUtils.isBlank(addMode)){
			addMode = "1";//默认人工
		}
		request.setAttribute("bean", bean);
    	request.setAttribute("fntype", request.getParameter("fntype"));
    	request.setAttribute("addMode", addMode);
    	List<OmCourse> courseList = courseService.selectAll();
    	request.setAttribute("courseList", courseList);
    	if(!"1".equals(addMode)){
    		if(detailList.size()==0){
    			detailList.add(new OmPaperDetail());
    		}
    		request.setAttribute("detailList", detailList);
    		return PAPER_PATH+"autoCreate";
    	}
    	return PAPER_PATH+"create";
  	}

    //保存修改操作
    @RequestMapping("save")
    @ResponseBody
	public AjaxObject save(HttpServletRequest request,OmPaper bean,String questionIds){
    	AjaxObject result = AjaxObject.newOk("success");
		try {
			String fntype = request.getParameter("fntype");
			if ("update".equals(fntype)) {
				bean.setUpdateTime(DateUtil.dateParse(new Date(),""));
				if("1".equals(bean.getAddMode())){//人工添加
					service.updateByPrimaryKeySelective(bean,questionIds);
				}else{
					String detaiDTOListJson = request.getParameter("detaiDTOListJson");
					List<OmPaperDetail> detailDTOList = new ArrayList<OmPaperDetail>();
					if (StringUtils.isNotBlank(detaiDTOListJson)) {
						detailDTOList = AjaxUtil.getJavaCollection(new OmPaperDetail(), detaiDTOListJson);
					}
					service.updateNewAuto(bean,detailDTOList);
				}
			}else{
				bean.setPaperId(UUIDUtil.getUUID());
				bean.setCreateTime(DateUtil.dateParse(new Date(),""));
				if("1".equals(bean.getAddMode())){//人工添加
					service.insert(bean,questionIds);
				}else{//随机试卷
					String detaiDTOListJson = request.getParameter("detaiDTOListJson");
					List<OmPaperDetail> detailDTOList = new ArrayList<OmPaperDetail>();
					if (StringUtils.isNotBlank(detaiDTOListJson)) {
						detailDTOList = AjaxUtil.getJavaCollection(new OmPaperDetail(), detaiDTOListJson);
					}
					service.insertNewAuto(bean,detailDTOList);
				}
			}
		} catch (Exception e) {
			logger.error("保存修改试卷信息："  , e);
			return AjaxObject.newError(e.getMessage());
		}
		return result;
	}
    
    //批量删除操作
    @RequestMapping("del")
    @ResponseBody
	public AjaxObject del(HttpServletRequest request,String ids){
    	AjaxObject result = AjaxObject.newOk("success");
		try {
			service.deleteBatch(ids);
		} catch (Exception e) {
			logger.error("删除试卷信息："  , e);
			return AjaxObject.newError(e.getMessage());
		}
		return result;
	}
    
    //试卷预览
  	@RequestMapping(value = { "/show" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
  	public String show(HttpServletRequest request) {
  		String paperId = request.getParameter("paperId");
  		OmPaper bean = service.selectByPrimaryKey(paperId);
  		List<OmQuestionVo> questionList1 = new ArrayList<OmQuestionVo>();
  		List<OmQuestionVo> questionList2 = new ArrayList<OmQuestionVo>();
  		List<OmQuestionVo> questionList3 = new ArrayList<OmQuestionVo>();
  		List<OmQuestionVo> questionList4 = new ArrayList<OmQuestionVo>();
  		List<OmQuestionVo> questionList5 = new ArrayList<OmQuestionVo>();
  		if("1".equals(bean.getAddMode())){//人工
  			List<OmQuestion> questionList = questionService.selectPaperQuestion(bean.getPaperId());
  	  		for(OmQuestion questionBean:questionList){
  	  			String questionType = questionBean.getQuestionType();
  	  		    OmQuestionVo questionVo = new OmQuestionVo();
  		        BeanUtils.copyProperties(questionBean,questionVo);
  	  		    //获取问题图片
  	  		    List<OmQuestionImg> imgList = questionService.selectQuestionImgByQuestionId(questionBean.getQuestionId());
  	  		    if(imgList.size()>0) {
  	  		    	questionVo.setImgList(imgList);
  	  		    }
  	  			switch (questionType) {
  	  				case "1":
  	  					questionList1.add(questionVo);
  	  				    break;
  	  				case "2":
  	  					questionList2.add(questionVo);
  	  				    break;
  	  				case "3":
  	  					questionList3.add(questionVo);
  	  				    break;
  	  				case "4":
  	  					questionList4.add(questionVo);
  	  				    break;
  	  				case "5":
  	  					questionList5.add(questionVo);
  	  				    break;    
  	  			}
  	  		}
  		}else{
  			List<OmPaperDetail> detailList = service.findPaperDeatilByPaperId(bean.getPaperId()); 
  			for(OmPaperDetail detailBean:detailList){
  				List<OmQuestion> list = questionService.selectAuto(detailBean.getQuestionType(),detailBean.getCourseId(),detailBean.getQuestionCount());
  				String questionType = detailBean.getQuestionType();
  				for(OmQuestion questionBean:list){
  					questionBean.setQuestionScore(detailBean.getQuestionScore());
  					OmQuestionVo questionVo = new OmQuestionVo();
  	  		        BeanUtils.copyProperties(questionBean,questionVo);
  	  	  		    //获取问题图片
  	  	  		    List<OmQuestionImg> imgList = questionService.selectQuestionImgByQuestionId(questionBean.getQuestionId());
  	  	  		    if(imgList.size()>0) {
  	  	  		    	questionVo.setImgList(imgList);
  	  	  		    }
  		  			switch (questionType) {
  		  				case "1":
  		  					questionList1.add(questionVo);
  		  				    break;
  		  				case "2":
  		  					questionList2.add(questionVo);
  		  				    break;
  		  				case "3":
  		  					questionList3.add(questionVo);
  		  				    break;
  		  				case "4":
  		  					questionList4.add(questionVo);
  		  				    break;
  		  				case "5":
  		  					questionList5.add(questionVo);
  		  				    break;    
  		  			}
  				}
  			}
  		}
  		request.setAttribute("questionList1", questionList1);
  		request.setAttribute("questionList2", questionList2);
  		request.setAttribute("questionList3", questionList3);
  		request.setAttribute("questionList4", questionList4);
  		request.setAttribute("questionList5", questionList5);
  		request.setAttribute("bean", bean);
  		String[] questionTypeArr = {"一","二","三","四","五"};
  		request.setAttribute("questionTypeArr",questionTypeArr);
    	return PAPER_PATH+"show";
  	}
  	
    //获取试卷的试题列表
    @RequestMapping(value = { "/getPaperQuestionList" })
   	@ResponseBody
   	public AjaxObject getPaperQuestionList(HttpServletRequest request,String paperId){
    	List<OmQuestion> questionList = questionService.selectPaperQuestion(paperId);
   		return AjaxObject.newOk("操作成功",questionList);
   	}
    
    //考试
  	@RequestMapping(value = { "/exam" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
  	public String exam(HttpServletRequest request) {
  		Subject currentUser = SecurityUtils.getSubject();
		OmUser omUser = (OmUser) SecurityUtils.getSubject().getSession().getAttribute("om_user");
  		String paperId = request.getParameter("paperId");
  		OmPaper bean = service.selectByPrimaryKey(paperId);
  		String planId = request.getParameter("planId");
  		if(StringUtils.isNoneBlank(planId)){
  			OmExamPlan plan = planService.selectByPrimaryKey(planId);
  			request.setAttribute("plan", plan);
  		}
  		String type = request.getParameter("type");
  		String answerUserId =  request.getParameter("answerUserId");//阅卷
  		if(StringUtils.isBlank(answerUserId)){
  			answerUserId =omUser.getUserId();//答卷和回顾自己的试卷
  		}
  		//答卷结果记录
  		OmExamSubmitVo examSubmit = submitService.selectVoByUserId(planId, paperId, answerUserId);
  		request.setAttribute("examSubmit", examSubmit);
  		
  		//获取试卷的题目数据
  		getPaperQuestion(request, answerUserId, paperId, bean, planId);
  		request.setAttribute("bean", bean);
  		String[] questionTypeArr = {"一","二","三","四","五"};
  		request.setAttribute("questionTypeArr",questionTypeArr);
  		request.setAttribute("type", type);
  		if("markExam".equals(type)){
  			return PAPER_PATH+"markExam";
  		}
  		if("historyExam".equals(type)||"monitorExam".equals(type)){
  			return PAPER_PATH+"historyExam";
  		}
    	return PAPER_PATH+"exam";
  	}

	private void getPaperQuestion(HttpServletRequest request, String answerUserId, String paperId, OmPaper bean,
			String planId) {
		List<OmQuestionVo> questionList1 = new ArrayList<OmQuestionVo>();
  		List<OmQuestionVo> questionList2 = new ArrayList<OmQuestionVo>();
  		List<OmQuestionVo> questionList3 = new ArrayList<OmQuestionVo>();
  		List<OmQuestionVo> questionList4 = new ArrayList<OmQuestionVo>();
  		List<OmQuestionVo> questionList5 = new ArrayList<OmQuestionVo>();
		//答案
		List<OmExamAnswer> answerList = answerService.selectByUserPaperID(answerUserId, planId, paperId);
		Map<String,OmExamAnswer> answerMap = new HashMap<String,OmExamAnswer>();
		for(OmExamAnswer answer:answerList ){
			answerMap.put(answer.getQuestionId(), answer);
		}
		List<OmQuestion> questionList = new ArrayList<OmQuestion>();
		if("1".equals(bean.getAddMode())){//人工
			questionList = questionService.selectPaperQuestion(bean.getPaperId());
		}else{
			//随机试卷
			questionList = questionService.selectUserPaperQuestion(bean.getPaperId(),answerUserId);
			if(questionList.size()==0){
				//生成试卷
				questionList =service.insertPaperQuestionAuto(bean.getPaperId(),answerUserId);
			}
		}
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
  		    //获取问题图片
  		    List<OmQuestionImg> imgList = questionService.selectQuestionImgByQuestionId(questionBean.getQuestionId());
  		    if(imgList.size()>0) {
  		    	questionVo.setImgList(imgList);
  		    }
  			switch (questionType) {
  				case "1":
  					questionList1.add(questionVo);
  				    break;
  				case "2":
  					questionList2.add(questionVo);
  				    break;
  				case "3":
  					questionList3.add(questionVo);
  				    break;
  				case "4":
  					questionList4.add(questionVo);
  				    break;
  				case "5":
  					questionList5.add(questionVo);
  				    break;    
  			}
  		}
  		request.setAttribute("questionList1", questionList1);
  		request.setAttribute("questionList2", questionList2);
  		request.setAttribute("questionList3", questionList3);
  		request.setAttribute("questionList4", questionList4);
  		request.setAttribute("questionList5", questionList5);
	}
  	
    //答题 每一道题
    @RequestMapping("saveQuestionResult")
    @ResponseBody
	public AjaxObject saveQuestionResult(HttpServletRequest request,OmExamAnswer bean){
    	AjaxObject result = AjaxObject.newOk("success");
		try {
			Subject currentUser = SecurityUtils.getSubject();
			OmUser omUser = (OmUser) SecurityUtils.getSubject().getSession().getAttribute("om_user");
			bean.setUserId(omUser.getUserId());
			answerService.saveOrUpdate(bean);
		} catch (Exception e) {
			logger.error("保存答题信息："  , e);
			return AjaxObject.newError(e.getMessage());
		}
		return result;
	}
    
    //随机试卷重新生成
    @RequestMapping("rebuild")
    @ResponseBody
	public AjaxObject rebuild(HttpServletRequest request,String paperId){
    	AjaxObject result = AjaxObject.newOk("success");
		try {
			OmPaper bean = service.selectByPrimaryKey(paperId);
			service.rebuild(bean);
		} catch (Exception e) {
			logger.error("重新生成试卷："  , e);
			return AjaxObject.newError(e.getMessage());
		}
		return result;
	}
    
    //导出试卷word
    @RequestMapping("/exportPaper2")
    @ResponseBody
  	public void exportPaper2(HttpServletRequest request,HttpServletResponse response) {
  		String paperId = request.getParameter("paperId");
  		OmPaper bean = service.selectByPrimaryKey(paperId);
  	    //构造word map数据
  		Map<String,Object> map = new HashMap<String,Object>();
  		map.put("paperName", bean.getPaperName());
		map.put("paperScore", bean.getPaperScore()+"");
		map.put("paperTime", bean.getPaperTime()+"");
		Map<String,Object> header2 = new HashMap<String, Object>();
        header2.put("width", 100);
        header2.put("height", 150);
        header2.put("type", "png");
        try {
			header2.put("content", WordUtils.inputStream2ByteArray(new FileInputStream("C:\\Users\\Lenovo\\Desktop\\1.png"), true));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        map.put("picture", header2);
  		List<OmQuestion> questionList1 = new ArrayList<OmQuestion>();
  		List<OmQuestion> questionList2 = new ArrayList<OmQuestion>();
  		List<OmQuestion> questionList3 = new ArrayList<OmQuestion>();
  		List<OmQuestion> questionList4 = new ArrayList<OmQuestion>();
  		List<OmQuestion> questionList5 = new ArrayList<OmQuestion>();
		List<OmQuestion> questionList = questionService.selectPaperQuestion(bean.getPaperId());
  		for(OmQuestion questionBean:questionList){
  			String questionType = questionBean.getQuestionType();
  			switch (questionType) {
  				case "1":
  					questionList1.add(questionBean);
  				    break;
  				case "2":
  					questionList2.add(questionBean);
  				    break;
  				case "3":
  					questionList3.add(questionBean);
  				    break;
  				case "4":
  					questionList4.add(questionBean);
  				    break;
  				case "5":
  					questionList5.add(questionBean);
  				    break;    
  			}
  		}
  		String[] questionTypeArr = {"一","二","三","四","五"};
		int questionTypeNum = 0;
		List<Map<String,String>> question1MapList = new ArrayList<Map<String,String>>();
		if(questionList1.size()>0){
			Map<String,String> questionMapTemp = null;
			for (int i=0;i<questionList1.size();i++) {
				questionMapTemp = new HashMap<String,String>();
				OmQuestion question = questionList1.get(i);
				String questionName = "";
				if(i==0){
					questionName +=questionTypeArr[questionTypeNum]+"、单项选择题\n ";
					questionTypeNum++;
				}
				questionName +=(i+1)+"."+question.getQuestionName()+"("+question.getQuestionScore()+"分)\n ";
				if(StringUtils.isNotBlank(question.getOptionA())){
					questionName +="A、"+question.getOptionA()+"\n ";
				}
				if(StringUtils.isNotBlank(question.getOptionB())){
					questionName +="B、"+question.getOptionB()+"\n ";
				}
				if(StringUtils.isNotBlank(question.getOptionC())){
					questionName +="C、"+question.getOptionC()+"\n ";
				}
				if(StringUtils.isNotBlank(question.getOptionD())){
					questionName +="D、"+question.getOptionD()+"\n ";
				}
				if(StringUtils.isNotBlank(question.getOptionE())){
					questionName +="E、"+question.getOptionE()+"\n ";
				}
				if(StringUtils.isNotBlank(question.getOptionF())){
					questionName +="F、"+question.getOptionF()+"\n ";
				}
				if(StringUtils.isNotBlank(question.getOptionG())){
					questionName +="G、"+question.getOptionG()+"\n ";
				}
				if(StringUtils.isNotBlank(question.getOptionH())){
					questionName +="H、"+question.getOptionH()+"\n ";
				}
				questionMapTemp.put("question.name",questionName);
				question1MapList.add(questionMapTemp);
			}
		}
		List<Map<String,String>> question2MapList = new ArrayList<Map<String,String>>();
		if(questionList2.size()>0){
			Map<String,String> questionMapTemp = null;
			for (int i=0;i<questionList2.size();i++) {
				questionMapTemp = new HashMap<String,String>();
				OmQuestion question = questionList2.get(i);
				String questionName = "";
				if(i==0){
					questionName +=questionTypeArr[questionTypeNum]+"、多项选择题\n ";
					questionTypeNum++;
				}
				questionName +=(i+1)+"."+question.getQuestionName()+"("+question.getQuestionScore()+"分)\n ";
				if(StringUtils.isNotBlank(question.getOptionA())){
					questionName +="A、"+question.getOptionA()+"\n ";
				}
				if(StringUtils.isNotBlank(question.getOptionB())){
					questionName +="B、"+question.getOptionB()+"\n ";
				}
				if(StringUtils.isNotBlank(question.getOptionC())){
					questionName +="C、"+question.getOptionC()+"\n ";
				}
				if(StringUtils.isNotBlank(question.getOptionD())){
					questionName +="D、"+question.getOptionD()+"\n ";
				}
				if(StringUtils.isNotBlank(question.getOptionE())){
					questionName +="E、"+question.getOptionE()+"\n ";
				}
				if(StringUtils.isNotBlank(question.getOptionF())){
					questionName +="F、"+question.getOptionF()+"\n ";
				}
				if(StringUtils.isNotBlank(question.getOptionG())){
					questionName +="G、"+question.getOptionG()+"\n ";
				}
				if(StringUtils.isNotBlank(question.getOptionH())){
					questionName +="H、"+question.getOptionH()+"\n ";
				}
				questionMapTemp.put("question.name",questionName);
				question2MapList.add(questionMapTemp);
			}
		}
		List<Map<String,String>> question3MapList = new ArrayList<Map<String,String>>();
		if(questionList3.size()>0){
			Map<String,String> questionMapTemp = null;
			for (int i=0;i<questionList3.size();i++) {
				questionMapTemp = new HashMap<String,String>();
				OmQuestion question = questionList3.get(i);
				String questionName = "";
				if(i==0){
					questionName +=questionTypeArr[questionTypeNum]+"、判断题\n ";
					questionTypeNum++;
				}
				questionName +=(i+1)+"."+question.getQuestionName()+"("+question.getQuestionScore()+"分)\n ";
				questionMapTemp.put("question.name",questionName);
				question3MapList.add(questionMapTemp);
			}
		}
		List<Map<String,String>> question4MapList = new ArrayList<Map<String,String>>();
		if(questionList4.size()>0){
			Map<String,String> questionMapTemp = null;
			for (int i=0;i<questionList4.size();i++) {
				questionMapTemp = new HashMap<String,String>();
				OmQuestion question = questionList4.get(i);
				String questionName = "";
				if(i==0){
					questionName +=questionTypeArr[questionTypeNum]+"、填空题\n ";
					questionTypeNum++;
				}
				questionName +=(i+1)+"."+question.getQuestionName()+"("+question.getQuestionScore()+"分)\n ";
				questionMapTemp.put("question.name",questionName);
				question4MapList.add(questionMapTemp);
			}
		}
		List<Map<String,String>> question5MapList = new ArrayList<Map<String,String>>();
		if(questionList5.size()>0){
			Map<String,String> questionMapTemp = null;
			for (int i=0;i<questionList5.size();i++) {
				questionMapTemp = new HashMap<String,String>();
				OmQuestion question = questionList5.get(i);
				String questionName = "";
				if(i==0){
					questionName +=questionTypeArr[questionTypeNum]+"、简答题\n ";
					questionTypeNum++;
				}
				questionName +=(i+1)+"."+question.getQuestionName()+"("+question.getQuestionScore()+"分)\n ";
				questionMapTemp.put("question.name",questionName);
				question5MapList.add(questionMapTemp);
			}
		}
		
		String demoTemplate = "/template/paperTemplate.docx";//模板存放位置
		String targetPath = this.getClass().getClassLoader().getResource("/template").getPath()+bean.getPaperName()+".doc";//生成文档存放位置
		
		//初始化导出
		WordExport export = new WordExport();
        try {
        	InputStream in =  this.getClass().getResourceAsStream(demoTemplate);  
            export.init(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
  			try {
  	            export.export(map);
  	            int table = 0;
  	            if(questionList1.size()>0){
	            	 export.export(question1MapList, table++,0);
  	            }else{
  	            	export.deleteTable(table++);
  	            }
  	            if(questionList2.size()>0){
	            	 export.export(question2MapList, table++,0);
	            }else{
	            	export.deleteTable(table++);
	            }
  	           if(questionList3.size()>0){
	            	 export.export(question3MapList, table++,0);
	            }else{
	            	export.deleteTable(table++);
	            }
	  	        if(questionList4.size()>0){
	            	 export.export(question4MapList, table++,0);
	            }else{
	            	export.deleteTable(table++);
	            }
	  	        if(questionList5.size()>0){
	            	 export.export(question5MapList, table++,0);
	            }else{
	            	export.deleteTable(table++);
	            }

			
  	           
  				File file = new File(targetPath);

  				//创建xml父级文件夹
  				if(file.exists()==false){
  					file.createNewFile();
  				}
  	            export.generate(targetPath);
  	            InputStream inS = new FileInputStream(file); 
				DownloadUtils.downloadExcel(response,inS,bean.getPaperName()+".doc");
  	            
  	        } catch (Exception e) {
  	            e.printStackTrace();
  	        }
  	}
    
  //导出试卷word
    @RequestMapping("/exportPaper")
    @ResponseBody
  	public void exportPaper(HttpServletRequest request,HttpServletResponse response) {
  		String paperId = request.getParameter("paperId");
  		OmPaper bean = service.selectByPrimaryKey(paperId);
  	    //构造word map数据
  		Map<String,Object> map = new HashMap<String,Object>();
  		map.put("paperName", bean.getPaperName());
		map.put("paperScore", bean.getPaperScore()+"");
		map.put("paperTime", bean.getPaperTime()+"");
  		List<OmQuestion> questionList1 = new ArrayList<OmQuestion>();
  		List<OmQuestion> questionList2 = new ArrayList<OmQuestion>();
  		List<OmQuestion> questionList3 = new ArrayList<OmQuestion>();
  		List<OmQuestion> questionList4 = new ArrayList<OmQuestion>();
  		List<OmQuestion> questionList5 = new ArrayList<OmQuestion>();
		List<OmQuestion> questionList = questionService.selectPaperQuestion(bean.getPaperId());
  		for(OmQuestion questionBean:questionList){
  			String questionType = questionBean.getQuestionType();
  			switch (questionType) {
  				case "1":
  					questionList1.add(questionBean);
  				    break;
  				case "2":
  					questionList2.add(questionBean);
  				    break;
  				case "3":
  					questionList3.add(questionBean);
  				    break;
  				case "4":
  					questionList4.add(questionBean);
  				    break;
  				case "5":
  					questionList5.add(questionBean);
  				    break;    
  			}
  		}
  		String[] questionTypeArr = {"一","二","三","四","五"};
		int questionTypeNum = 0;
		int questionTotalNum = 0;
		if(questionList1.size()>0){
			for (int i=0;i<questionList1.size();i++) {
				OmQuestion question = questionList1.get(i);
				String questionName = "";
				if(i==0){
					questionName +=questionTypeArr[questionTypeNum]+"、单项选择题\n ";
					questionTypeNum++;
				}
				questionName +=(i+1)+"."+question.getQuestionName()+"("+question.getQuestionScore()+"分)\n ";
				if(StringUtils.isNotBlank(question.getOptionA())){
					questionName +="A、"+question.getOptionA()+"\n ";
				}
				if(StringUtils.isNotBlank(question.getOptionB())){
					questionName +="B、"+question.getOptionB()+"\n ";
				}
				if(StringUtils.isNotBlank(question.getOptionC())){
					questionName +="C、"+question.getOptionC()+"\n ";
				}
				if(StringUtils.isNotBlank(question.getOptionD())){
					questionName +="D、"+question.getOptionD()+"\n ";
				}
				if(StringUtils.isNotBlank(question.getOptionE())){
					questionName +="E、"+question.getOptionE()+"\n ";
				}
				if(StringUtils.isNotBlank(question.getOptionF())){
					questionName +="F、"+question.getOptionF()+"\n ";
				}
				if(StringUtils.isNotBlank(question.getOptionG())){
					questionName +="G、"+question.getOptionG()+"\n ";
				}
				if(StringUtils.isNotBlank(question.getOptionH())){
					questionName +="H、"+question.getOptionH()+"\n ";
				}
				map.put("question"+questionTotalNum, questionName);
				//获取图片
				List<OmQuestionImg> imgList = questionService.selectQuestionImgByQuestionId(question.getQuestionId());
				for(int k=0;k<imgList.size();k++) {
					OmQuestionImg imgBean = imgList.get(k);
					Map<String,Object> pic = new HashMap<String, Object>();
			        pic.put("width", 100);
			        pic.put("height", 150);
			        pic.put("type", imgBean.getImgSrc().substring(imgBean.getImgSrc().lastIndexOf(".")+1));
			        try {
						pic.put("content", WordUtils.inputStream2ByteArray(new FileInputStream(request.getSession().getServletContext().getRealPath("")+imgBean.getImgSrc()), true));
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					map.put("pic"+questionTotalNum+"_"+k, pic);
				}
				
				questionTotalNum++;
			}
		}
		if(questionList2.size()>0){
			for (int i=0;i<questionList2.size();i++) {
				OmQuestion question = questionList2.get(i);
				String questionName = "";
				if(i==0){
					questionName +=questionTypeArr[questionTypeNum]+"、多项选择题\n ";
					questionTypeNum++;
				}
				questionName +=(i+1)+"."+question.getQuestionName()+"("+question.getQuestionScore()+"分)\n ";
				if(StringUtils.isNotBlank(question.getOptionA())){
					questionName +="A、"+question.getOptionA()+"\n ";
				}
				if(StringUtils.isNotBlank(question.getOptionB())){
					questionName +="B、"+question.getOptionB()+"\n ";
				}
				if(StringUtils.isNotBlank(question.getOptionC())){
					questionName +="C、"+question.getOptionC()+"\n ";
				}
				if(StringUtils.isNotBlank(question.getOptionD())){
					questionName +="D、"+question.getOptionD()+"\n ";
				}
				if(StringUtils.isNotBlank(question.getOptionE())){
					questionName +="E、"+question.getOptionE()+"\n ";
				}
				if(StringUtils.isNotBlank(question.getOptionF())){
					questionName +="F、"+question.getOptionF()+"\n ";
				}
				if(StringUtils.isNotBlank(question.getOptionG())){
					questionName +="G、"+question.getOptionG()+"\n ";
				}
				if(StringUtils.isNotBlank(question.getOptionH())){
					questionName +="H、"+question.getOptionH()+"\n ";
				}
				map.put("question"+questionTotalNum, questionName);
				//获取图片
				List<OmQuestionImg> imgList = questionService.selectQuestionImgByQuestionId(question.getQuestionId());
				for(int k=0;k<imgList.size();k++) {
					OmQuestionImg imgBean = imgList.get(k);
					Map<String,Object> pic = new HashMap<String, Object>();
			        pic.put("width", 100);
			        pic.put("height", 150);
			        pic.put("type", imgBean.getImgSrc().substring(imgBean.getImgSrc().lastIndexOf(".")+1));
			        try {
						pic.put("content", WordUtils.inputStream2ByteArray(new FileInputStream(request.getSession().getServletContext().getRealPath("")+imgBean.getImgSrc()), true));
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					map.put("pic"+questionTotalNum+"_"+k, pic);
				}
				
				questionTotalNum++;
			}
		}
		if(questionList3.size()>0){
			for (int i=0;i<questionList3.size();i++) {
				OmQuestion question = questionList3.get(i);
				String questionName = "";
				if(i==0){
					questionName +=questionTypeArr[questionTypeNum]+"、判断题\n ";
					questionTypeNum++;
				}
				questionName +=(i+1)+"."+question.getQuestionName()+"("+question.getQuestionScore()+"分)\n ";
				map.put("question"+questionTotalNum, questionName);
				//获取图片
				List<OmQuestionImg> imgList = questionService.selectQuestionImgByQuestionId(question.getQuestionId());
				for(int k=0;k<imgList.size();k++) {
					OmQuestionImg imgBean = imgList.get(k);
					Map<String,Object> pic = new HashMap<String, Object>();
			        pic.put("width", 100);
			        pic.put("height", 150);
			        pic.put("type", imgBean.getImgSrc().substring(imgBean.getImgSrc().lastIndexOf(".")+1));
			        try {
						pic.put("content", WordUtils.inputStream2ByteArray(new FileInputStream(request.getSession().getServletContext().getRealPath("")+imgBean.getImgSrc()), true));
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					map.put("pic"+questionTotalNum+"_"+k, pic);
				}
				
				questionTotalNum++;
			}
		}
		if(questionList4.size()>0){
			for (int i=0;i<questionList4.size();i++) {
				OmQuestion question = questionList4.get(i);
				String questionName = "";
				if(i==0){
					questionName +=questionTypeArr[questionTypeNum]+"、填空题\n ";
					questionTypeNum++;
				}
				questionName +=(i+1)+"."+question.getQuestionName()+"("+question.getQuestionScore()+"分)\n ";
				map.put("question"+questionTotalNum, questionName);
				//获取图片
				List<OmQuestionImg> imgList = questionService.selectQuestionImgByQuestionId(question.getQuestionId());
				for(int k=0;k<imgList.size();k++) {
					OmQuestionImg imgBean = imgList.get(k);
					Map<String,Object> pic = new HashMap<String, Object>();
			        pic.put("width", 100);
			        pic.put("height", 150);
			        pic.put("type", imgBean.getImgSrc().substring(imgBean.getImgSrc().lastIndexOf(".")+1));
			        try {
						pic.put("content", WordUtils.inputStream2ByteArray(new FileInputStream(request.getSession().getServletContext().getRealPath("")+imgBean.getImgSrc()), true));
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					map.put("pic"+questionTotalNum+"_"+k, pic);
				}
				
				questionTotalNum++;
			}
		}
		if(questionList5.size()>0){
			for (int i=0;i<questionList5.size();i++) {
				OmQuestion question = questionList5.get(i);
				String questionName = "";
				if(i==0){
					questionName +=questionTypeArr[questionTypeNum]+"、简答题\n ";
					questionTypeNum++;
				}
				questionName +=(i+1)+"."+question.getQuestionName()+"("+question.getQuestionScore()+"分)\n ";
				map.put("question"+questionTotalNum, questionName);
				//获取图片
				List<OmQuestionImg> imgList = questionService.selectQuestionImgByQuestionId(question.getQuestionId());
				for(int k=0;k<imgList.size();k++) {
					OmQuestionImg imgBean = imgList.get(k);
					Map<String,Object> pic = new HashMap<String, Object>();
			        pic.put("width", 100);
			        pic.put("height", 150);
			        pic.put("type", imgBean.getImgSrc().substring(imgBean.getImgSrc().lastIndexOf(".")+1));
			        try {
						pic.put("content", WordUtils.inputStream2ByteArray(new FileInputStream(request.getSession().getServletContext().getRealPath("")+imgBean.getImgSrc()), true));
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					map.put("pic"+questionTotalNum+"_"+k, pic);
				}
				
				questionTotalNum++;
			}
		}
		
		String demoTemplate = "/template/paperTemplate2.docx";//模板存放位置
		String targetPath = this.getClass().getClassLoader().getResource("/template").getPath()+bean.getPaperName()+".docx";//生成文档存放位置
		
		//初始化导出
		WordExport export = new WordExport();
        try {
        	InputStream in =  this.getClass().getResourceAsStream(demoTemplate);  
            export.init(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
  			try {
  	            export.export(map);
  	           
  				File file = new File(targetPath);

  				//创建xml父级文件夹
  				if(file.exists()==false){
  					file.createNewFile();
  				}
  	            export.generate(targetPath);
  	            InputStream inS = new FileInputStream(file); 
				DownloadUtils.downloadExcel(response,inS,bean.getPaperName()+".doc");
  	            
  	        } catch (Exception e) {
  	            e.printStackTrace();
  	        }
  	}
    
}
