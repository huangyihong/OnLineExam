package com.plg.shiro.controller.exam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.plg.shiro.entity.OmCourse;
import com.plg.shiro.entity.OmQuestion;
import com.plg.shiro.entity.OmQuestionImg;
import com.plg.shiro.service.ICourseService;
import com.plg.shiro.service.IQuestionService;
import com.plg.shiro.util.DateUtil;
import com.plg.shiro.util.DownloadUtils;
import com.plg.shiro.util.ExcelUtils;
import com.plg.shiro.util.UUIDUtil;
import com.plg.shiro.util.dwz.AjaxObject;
import com.plg.shiro.util.dwz.LayuiPage;

/**
 * 题库管理
 *
 */
@Controller
@RequestMapping({ "/admin/exam/omQuestion" })
public class QuestionController {
	private static Logger logger = LoggerFactory.getLogger(QuestionController.class);
	
	@Resource
	private IQuestionService service;
	
	@Resource
	private ICourseService courseService;
	
	private static final String QUESTION_PATH = "admin/exam/omQuestion/";
	
	/**
	 * 题库管理列表页
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/list" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String list(HttpServletRequest request) {
		List<OmCourse> courseList = courseService.selectAll();
    	request.setAttribute("courseList", courseList);
		return QUESTION_PATH+"list";
	}
	
	//获取列表数据
  	@RequestMapping("getList")
    @ResponseBody
  	public AjaxObject getList(HttpServletRequest request,LayuiPage page) throws Exception {
  	  	AjaxObject result = AjaxObject.newOk("success");
  			try {
  				List<OmQuestion> list = service.findPageList(request, page);
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
  		String questionId = request.getParameter("questionId");
  		String questionType = request.getParameter("questionType");
  		OmQuestion bean = new OmQuestion();
		if(StringUtils.isNotBlank(questionId)){
			bean = service.selectByPrimaryKey(questionId);
			if(bean!=null){
				questionType = bean.getQuestionType();
				//获取图片
				List<OmQuestionImg> imgList = service.selectQuestionImgByQuestionId(questionId);
				request.setAttribute("imgList", imgList);
			}
    	}else{
    		bean.setCourseId(request.getParameter("courseId"));
    	}
		request.setAttribute("bean", bean);
    	request.setAttribute("fntype", request.getParameter("fntype"));
    	request.setAttribute("questionType", questionType);
    	List<OmCourse> courseList = courseService.selectAll();
    	request.setAttribute("courseList", courseList);
    	request.setAttribute("courseId", request.getParameter("courseId"));
    	if(StringUtils.isNoneBlank(questionType)){
    		return QUESTION_PATH+"create"+questionType;
    	}
    	return QUESTION_PATH+"create";
  	}

    //保存修改操作
    @RequestMapping("save")
    @ResponseBody
	public AjaxObject save(HttpServletRequest request,OmQuestion bean){
    	AjaxObject result = AjaxObject.newOk("success");
		try {
			String fntype = request.getParameter("fntype");
			String imgIds = request.getParameter("imgIds");
			String imgSrcs = request.getParameter("imgSrcs");
			if ("update".equals(fntype)) {
				bean.setUpdateTime(DateUtil.dateParse(new Date(),""));
				service.updateByPrimaryKeySelective(bean,imgIds,imgSrcs);
			}else{
				bean.setQuestionId(UUIDUtil.getUUID());
				bean.setCreateTime(DateUtil.dateParse(new Date(),""));
				service.insert(bean,imgIds,imgSrcs);
			}
		} catch (Exception e) {
			logger.error("保存修改题库信息："  , e);
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
			logger.error("删除题库信息："  , e);
			return AjaxObject.newError(e.getMessage());
		}
		return result;
	}
    
    /**
	 * 题库选择框列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/selectMultiQuestion" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String selectMultiQuestion(HttpServletRequest request) {
		String questionType = request.getParameter("questionType");
		request.setAttribute("questionType", questionType);
		request.setAttribute("courseId", request.getParameter("courseId"));
		return QUESTION_PATH+"selectMultiQuestion";
	}
	
	//excel导入
	@RequestMapping(value = { "/importQuestion" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String toImportFile(HttpServletRequest request) {
	  	return QUESTION_PATH+"importQuestion";
	}

    @RequestMapping("import")
    @ResponseBody
    public AjaxObject importFile(@RequestParam MultipartFile file,HttpServletRequest request, HttpServletResponse response) {
        AjaxObject result = AjaxObject.newOk("success");
        try {
        	// 判断文件名是否为空
    		if (file == null){
    			 return AjaxObject.newError("上传文件为空");
    		}
    		// 获取文件名
    		String name = file.getOriginalFilename();
    		// 判断文件大小、即名称
    		long size = file.getSize();
    		if (name == null || ("").equals(name) && size == 0){
    			return AjaxObject.newError("上传文件内容为空");
    		}
			String[] mappingFields = {"questionId","questionType","questionName","optionA","rightResult","courseName","questionScore"};
    		List<OmQuestion> list = ExcelUtils.readSheet(OmQuestion.class, mappingFields, 0, 1, file.getInputStream());
    		if(list.size()==0){
    			return AjaxObject.newError("上传文件内容为空");
    		}
    		readExcel(request,file, result,list);
    		
        } catch(Exception e) {
            return AjaxObject.newError(e.getMessage());
        }
        return result;
    }

	private void readExcel(HttpServletRequest request, MultipartFile file, AjaxObject result,List<OmQuestion> list) throws Exception, IOException {
		String message = "";
		List<OmQuestion> addList = new ArrayList<OmQuestion>();
		Map<String, String> questionTypeMap = questionTypeMap();
		Map<String, String> rightResultMap = rightResultMap();
		Map<String,OmCourse> OmCourseMap = OmCourseMap();
		for(int i=0;i<list.size();i++){
			OmQuestion bean=list.get(i);
			if(StringUtils.isBlank(bean.getQuestionType())){
				message +="第"+(i+1)+"行数据题型为空,忽略导入<br>";
				continue;
			}
			bean.setQuestionType(questionTypeMap.get(bean.getQuestionType()));
			if(StringUtils.isBlank(bean.getQuestionName())){
				message +="第"+(i+1)+"行数据题目为空,忽略导入<br>";
				continue;
			}
			if(StringUtils.isBlank(bean.getRightResult())){
				message +="第"+(i+1)+"行数据答案为空,忽略导入<br>";
				continue;
			}
			if("1".equals(bean.getQuestionType())||"2".equals(bean.getQuestionType())){//单选多选
				if(StringUtils.isBlank(bean.getOptionA())){
					message +="第"+(i+1)+"行数据选项内容为空,忽略导入<br>";
					continue;
				}
				String split_str = getOptionSplitStr(bean.getOptionA());
				if(bean.getOptionA().indexOf("H"+split_str)!=-1){
					bean.setOptionH(getOptionABCD(bean.getOptionA(),"H"+split_str,""));
				}
				if(bean.getOptionA().indexOf("G"+split_str)!=-1){
					String split_end = "";
					if(bean.getOptionA().indexOf("H"+split_str)!=-1){
						split_end = "H"+split_str;
					}
					bean.setOptionG(getOptionABCD(bean.getOptionA(),"G"+split_str,split_end));
				}
				if(bean.getOptionA().indexOf("F"+split_str)!=-1){
					String split_end = "";
					if(bean.getOptionA().indexOf("G"+split_str)!=-1){
						split_end = "G"+split_str;
					}
					bean.setOptionF(getOptionABCD(bean.getOptionA(),"F"+split_str,split_end));
				}
				if(bean.getOptionA().indexOf("E"+split_str)!=-1){
					String split_end = "";
					if(bean.getOptionA().indexOf("F"+split_str)!=-1){
						split_end = "F"+split_str;
					}
					bean.setOptionE(getOptionABCD(bean.getOptionA(),"E"+split_str,split_end));
				}
				if(bean.getOptionA().indexOf("D"+split_str)!=-1){
					String split_end = "";
					if(bean.getOptionA().indexOf("E"+split_str)!=-1){
						split_end = "E"+split_str;
					}
					bean.setOptionD(getOptionABCD(bean.getOptionA(),"D"+split_str,split_end));
				}
				if(bean.getOptionA().indexOf("C"+split_str)!=-1){
					String split_end = "";
					if(bean.getOptionA().indexOf("D"+split_str)!=-1){
						split_end = "D"+split_str;
					}
					bean.setOptionC(getOptionABCD(bean.getOptionA(),"C"+split_str,split_end));
				}
				if(bean.getOptionA().indexOf("B"+split_str)!=-1){
					String split_end = "";
					if(bean.getOptionA().indexOf("C"+split_str)!=-1){
						split_end = "C"+split_str;
					}
					bean.setOptionB(getOptionABCD(bean.getOptionA(),"B"+split_str,split_end));
				}
				if(bean.getOptionA().indexOf("A"+split_str)!=-1){
					String split_end = "";
					if(bean.getOptionA().indexOf("B"+split_str)!=-1){
						split_end = "B"+split_str;
					}
					bean.setOptionA(getOptionABCD(bean.getOptionA(),"A"+split_str,split_end));
				}
//				String optionA = getOptionABCD(bean.getOptionA(),"A"+split_str,"B"+split_str);
//				String optionB = getOptionABCD(bean.getOptionA(),"B"+split_str,"C"+split_str);
//				String optionC = getOptionABCD(bean.getOptionA(),"C"+split_str,"D"+split_str);
//				String optionD = getOptionABCD(bean.getOptionA(),"D"+split_str,"");
//				bean.setOptionA(optionA);
//				bean.setOptionB(optionB);
//				bean.setOptionC(optionC);
//				bean.setOptionD(optionD);
				
				bean.setRightResult(bean.getRightResult().replace("?", ""));
				if("2".equals(bean.getQuestionType())){
					char[] rightResult_arr=bean.getRightResult().toCharArray();
					String rightResult = "";
					for(char c:rightResult_arr){
						if(StringUtils.isNoneBlank(rightResult)){
							rightResult +=",";
						}
						rightResult +=c;
					}
					bean.setRightResult(rightResult);
				}
			}
			if("3".equals(bean.getQuestionType())){//判断
				bean.setRightResult(rightResultMap.get(bean.getRightResult().replace("?", "")));
			}
			if("4".equals(bean.getQuestionType())){//填空
				bean.setQuestionName(bean.getQuestionName().replace("（          ）", "(____)"));
				bean.setQuestionName(bean.getQuestionName().replace("(  )", "(____)"));
				bean.setQuestionName(bean.getQuestionName().replace("（）", "(____)"));
				bean.setQuestionName(bean.getQuestionName().replace("()", "(____)"));
			}
			if(StringUtils.isNoneBlank(bean.getCourseName())){
				OmCourse omCourse = OmCourseMap.get(bean.getCourseName());
				if(omCourse!=null){
					bean.setCourseId(omCourse.getCourseId());
				}
			}
			bean.setQuestionId(UUIDUtil.getUUID());
			bean.setCreateTime(DateUtil.dateParse(new Date(),""));
			addList.add(bean);
			service.insert(bean);
		}
		result.setMessage("成功导入：" +addList.size()+"条数据。<br>" +message);
	}

	private String getOptionSplitStr(String optionA) {
		String str="";
		String[] split_arr={".","、",""};
		for(String split_str:split_arr){
			if(optionA.indexOf("A"+split_str)!=-1&&optionA.indexOf("B"+split_str)!=-1){
				str = split_str;
				break;
			}
		}
		return str;
	}
	private String getOptionABCD(String option,String split_start,String split_end) {
		if(StringUtils.isBlank(split_end)){
			return option.substring(option.indexOf(split_start)+2);
		}
		return option.substring(option.indexOf(split_start)+2,option.indexOf(split_end));
	}

	private Map<String, String> questionTypeMap() {
		Map<String,String> questionTypeMap = new HashMap<String,String>();
		questionTypeMap.put("单选", "1");
		questionTypeMap.put("多选", "2");
		questionTypeMap.put("判断", "3");
		questionTypeMap.put("填空", "4");
		questionTypeMap.put("简答", "5");
		questionTypeMap.put("单选题", "1");
		questionTypeMap.put("多选题", "2");
		questionTypeMap.put("判断题", "3");
		questionTypeMap.put("填空题", "4");
		questionTypeMap.put("简答题", "5");
		return questionTypeMap;
	}
	private Map<String, String> rightResultMap() {
		Map<String,String> rightResultMap = new HashMap<String,String>();
		rightResultMap.put("对", "1");
		rightResultMap.put("错", "0");
		rightResultMap.put("正确", "1");
		rightResultMap.put("错误", "0");
		return rightResultMap;
	}
	
	private Map<String, OmCourse> OmCourseMap() {
		Map<String,OmCourse> OmCourseMap = new HashMap<String,OmCourse>();
		List<OmCourse> list = courseService.selectAll();
		for(OmCourse omCourse:list){
			OmCourseMap.put(omCourse.getCourseName(), omCourse);
		}
		return OmCourseMap;
	}

	/**
	 * 模板下载
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/downloadTemplate")
   	@SuppressWarnings("deprecation")
   	@ResponseBody
   	public void downloadTemplate(HttpServletRequest request,HttpServletResponse response)throws Exception {
		String filePath = "/template/题库模板.xls";
		String fileName = "题库模板.xls";
		InputStream in = null;  
        try {  
          in = this.getClass().getResourceAsStream(filePath);  
          DownloadUtils.downloadExcel(response,in,fileName);
        } catch (FileNotFoundException e) {  
        }
    }
	
	@RequestMapping("uploadImg")
    @ResponseBody
    public AjaxObject uploadImg(@RequestParam MultipartFile file,HttpServletRequest request, HttpServletResponse response) {
        AjaxObject result = AjaxObject.newOk("success");
        String prefix="";
	    String imgId="";
	    OutputStream out = null;
        try {
        	// 判断文件名是否为空
    		if (file == null){
    			 return AjaxObject.newError("上传文件为空");
    		}
    		// 获取文件名
    		String originalName = file.getOriginalFilename();
    		prefix=originalName.substring(originalName.lastIndexOf(".")+1);
    		imgId =UUIDUtil.getUUID();
    		String year = DateUtil.date2String(new Date(), "yyyy");
    		// 判断文件大小、即名称
    		long size = file.getSize();
    		if (originalName == null || ("").equals(originalName) && size == 0){
    			return AjaxObject.newError("上传文件内容为空");
    		}
    		String filepath = request.getSession().getServletContext().getRealPath("/upfile")+"/"+year+"/"+ imgId + "." + prefix;
	        File files=new File(filepath);
  
            if(!files.getParentFile().exists()){
                files.getParentFile().mkdirs();
            }
	        file.transferTo(files);
 
	        Map<String,Object> img=new HashMap<>();

		    img.put("src","/upfile/"+year+"/"+ imgId + "." + prefix);
		    img.put("imgId", imgId);
		    result.setData(img);
        } catch(Exception e) {
            return AjaxObject.newError(e.getMessage());
        }finally{
	        try {
	            if(out!=null){
	                out.close();
	            }
	        } catch (IOException e) {
	        }
	    }
        return result;
    }

}
