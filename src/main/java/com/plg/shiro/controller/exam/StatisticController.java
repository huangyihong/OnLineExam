package com.plg.shiro.controller.exam;

import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.plg.shiro.entity.OmExamPlanVo;
import com.plg.shiro.entity.OmUserGroup;
import com.plg.shiro.entity.Vo.OmExamSubmitVo;
import com.plg.shiro.service.IExamPlanService;
import com.plg.shiro.service.IExamSubmitService;
import com.plg.shiro.service.IUserGroupService;
import com.plg.shiro.util.ExcelUtils;

/**
 *统计分析
 */
@Controller
@RequestMapping({ "/admin/exam/statistic" })
public class StatisticController {
	
	private static Logger logger = LoggerFactory.getLogger(StatisticController.class);

	@Resource
	private IExamPlanService service;
	
	@Resource
	private IExamSubmitService submitService;
	
	@Resource
	private IUserGroupService groupService;
	
	private static final String PATH = "admin/exam/statistic/";
	
	@RequestMapping(value = { "/index" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String index(HttpServletRequest request) {
		List<OmExamPlanVo> list = service.selectVoByStatus("3");//3考试结束的
		request.setAttribute("list", list);
		return PATH+"index";
	}
	
	/**
	 * 统计分析
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/showRange" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String showRange(HttpServletRequest request) {
		String planId = request.getParameter("planId");
		OmExamPlanVo bean = service.selectVoByPrimaryKey(planId);
		String status = "4";//成绩发布的试卷
		request.setAttribute("bean", bean);
		//最高 最低 平均分 考试人数 优秀人数 良好人数 及格人数 不及格人数
		Map<String, Object> scoreNumMaxMinMap = submitService.sqlQueryScoreNumMaxMin(planId,status,null);
		request.setAttribute("scoreNumMaxMinMap", scoreNumMaxMinMap);
		//获取分组
		List<OmUserGroup> list = groupService.selectAll();
		String[] dataAxis = new String[list.size()];//x轴
		int[] user_num_data = new int[list.size()];//y轴
		int[] perfect_num_data = new int[list.size()];//y轴
		int[] good_num_data = new int[list.size()];//y轴
		int[] pass_num_data = new int[list.size()];//y轴
		int[] nopass_num_data = new int[list.size()];//y轴
		int[] max_score_data = new int[list.size()];//y轴
		int[] min_score_data = new int[list.size()];//y轴
		int[] avg_score_data = new int[list.size()];//y轴
		for(int i = 0; i < list.size(); i++){
			OmUserGroup groupBean = list.get(i);
			dataAxis[i]=groupBean.getGroupName();
			Map<String, Object> maxMinMap = submitService.sqlQueryScoreNumMaxMin(planId,status,groupBean.getGroupId());
			user_num_data[i] = Integer.parseInt((maxMinMap.get("USER_NUM")+""));
			perfect_num_data[i] = Integer.parseInt((maxMinMap.get("PERFECT_NUM")+""));
			good_num_data[i] = Integer.parseInt((maxMinMap.get("GOOD_NUM")+""));
			pass_num_data[i] = Integer.parseInt((maxMinMap.get("PASS_NUM")+""));
			nopass_num_data[i] = Integer.parseInt((maxMinMap.get("NOPASS_NUM")+""));
			max_score_data[i] = Integer.parseInt((maxMinMap.get("MAX_SCORE")+""));
			min_score_data[i] = Integer.parseInt((maxMinMap.get("MIN_SCORE")+""));
			avg_score_data[i] = Integer.parseInt((maxMinMap.get("AVG_SCORE")+""));
		}
		JSONObject detail = new JSONObject();
		detail.put("dataAxis", dataAxis);
		detail.put("user_num_data", user_num_data);
		detail.put("perfect_num_data", perfect_num_data);
		detail.put("good_num_data", good_num_data);
		detail.put("pass_num_data", pass_num_data);
		detail.put("nopass_num_data", nopass_num_data);
		detail.put("max_score_data", max_score_data);
		detail.put("min_score_data", min_score_data);
		detail.put("avg_score_data", avg_score_data);
		request.setAttribute("detail", detail);
		return PATH+"showRange";
	}
	
	
	/**
	 * 统计分析
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/showRangeOld" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String showRangeOld(HttpServletRequest request) {
		String planId = request.getParameter("planId");
		OmExamPlanVo bean = service.selectVoByPrimaryKey(planId);
		String status = "4";//成绩发布的试卷
		List<OmExamSubmitVo> list = submitService.findVoUserList(request,status);
		String[] dataAxis = new String[list.size()];//x轴
		int[] data = new int[list.size()];//y轴
		int userNum = list.size();//考试人数
		int passNum = 0;//及格人数
		int paperScore = bean.getPaperScore();//总分数
		int passingScore = bean.getPassingScore();//及格分数
		String passingType = bean.getPassingType();
		if("2".equals(passingType)){//百分比
			 passingScore =Math.round((paperScore*passingScore)/100);
		}
		for(int i = 0; i < list.size(); i++){
			OmExamSubmitVo beanVo = list.get(i);
			dataAxis[i]=beanVo.getRealName();
			data[i]=beanVo.getTotalScore();
			if(beanVo.getTotalScore()>=passingScore){
				passNum++;
			}
		}
		request.setAttribute("bean", bean);
		JSONObject detail = new JSONObject();
		detail.put("dataAxis", dataAxis);
		detail.put("data", data);
		detail.put("userNum", userNum);
		detail.put("passNum", passNum);
		request.setAttribute("detail", detail);
		//最高 最低 平均分
		Map<String, Object> maxMinMap = submitService.sqlQueryScoreNumMaxMin(planId,status,null);
		request.setAttribute("maxMinMap", maxMinMap);
		return PATH+"showRangeOld";
	}
	
	@RequestMapping("/exportGroupGrade")
	@SuppressWarnings("deprecation")
	@ResponseBody
	public void exportGroupGrade(HttpServletRequest request,HttpServletResponse response)throws Exception {
		String planId = request.getParameter("planId");
		OmExamPlanVo bean = service.selectVoByPrimaryKey(planId);
		String file = bean.getPlanName()+"的统计分析.xls";
		response.reset();
        response.setContentType("application/octet-stream; charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename="+ URLEncoder.encode(file, "UTF-8") );
		String error = null;
		String selectSql = null; 
		try {
				String status = "4";//成绩发布的试卷
				String[] titles= new String[]{"分组名称(单位名称)","考试人数","优秀人数","良好人数","及格人数","不及格人数","最高分","最低分","平均分"," 及格率"};
				String[] mappingFields= new String[]{"groupName","user_num","perfect_num","good_num","pass_num","nopass_num","max_score","min_score","avg_score","bi"};
				List<JSONObject> datalist = new ArrayList<JSONObject>();
				//获取分组
				List<OmUserGroup> list = groupService.selectAll();
				for(OmUserGroup groupBean:list) {
					JSONObject detail = new JSONObject(); 
					detail.put("groupName",groupBean.getGroupName());
					Map<String, Object> maxMinMap = submitService.sqlQueryScoreNumMaxMin(planId,status,groupBean.getGroupId());
					detail.put("user_num", Integer.parseInt((maxMinMap.get("USER_NUM")+"")));
					detail.put("perfect_num", Integer.parseInt((maxMinMap.get("PERFECT_NUM")+"")));
					detail.put("good_num", Integer.parseInt((maxMinMap.get("GOOD_NUM")+"")));
					detail.put("pass_num", Integer.parseInt((maxMinMap.get("PASS_NUM")+"")));
					detail.put("nopass_num", Integer.parseInt((maxMinMap.get("NOPASS_NUM")+"")));
					detail.put("max_score", Integer.parseInt((maxMinMap.get("MAX_SCORE")+"")));
					detail.put("min_score", Integer.parseInt((maxMinMap.get("MIN_SCORE")+"")));
					detail.put("avg_score", Integer.parseInt((maxMinMap.get("AVG_SCORE")+"")));
					if((int)detail.get("user_num")>0) {
						int passAllNum = (int)detail.get("perfect_num")+(int)detail.get("good_num")+(int)detail.get("pass_num");
						DecimalFormat df2  = new DecimalFormat("###.00");
						detail.put("bi", df2.format(passAllNum*100/(int)detail.get("user_num"))+"%");
					}else {
						detail.put("bi", "0");
					}
					datalist.add(detail);
				}
				ExcelUtils.writeOneSheet1(datalist, titles, mappingFields, "结果信息",response.getOutputStream(),"详细列表"); 
			} catch (Exception e) {
				error = e.getMessage();
				throw e;
			} finally {

			}
	}
	
	
  		
}
