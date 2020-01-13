package com.plg.shiro.controller.exam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plg.shiro.entity.OmCourse;
import com.plg.shiro.service.ICourseService;
import com.plg.shiro.util.UUIDUtil;
import com.plg.shiro.util.dwz.AjaxObject;
import com.plg.shiro.util.dwz.LayuiPage;

/**
 *科目管理
 */
@Controller
@RequestMapping({ "/admin/exam/omCourse" })
public class CourseController {
	
	private static Logger logger = LoggerFactory.getLogger(CourseController.class);

	@Resource
	private ICourseService service;
	
	private static final String Course_PATH = "admin/exam/omCourse/";
	
	/**
	 * 科目管理列表页
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/list" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String list(HttpServletRequest request) {
		return Course_PATH+"list";
	}
	
	//获取列表数据
  	@RequestMapping("getList")
    @ResponseBody
  	public AjaxObject getList(HttpServletRequest request,LayuiPage page) throws Exception {
  	  	AjaxObject result = AjaxObject.newOk("success");
  			try {
  				List<OmCourse> list = service.findPageList(request, page);
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
  		String courseId = request.getParameter("courseId");
  		OmCourse bean = new OmCourse();
		if(StringUtils.isNotBlank(courseId)){
			bean = service.selectByPrimaryKey(courseId);
    	}
		request.setAttribute("bean", bean);
    	request.setAttribute("fntype", request.getParameter("fntype"));
    	return Course_PATH+"create";
  	}

    //保存修改操作
    @RequestMapping("save")
    @ResponseBody
	public AjaxObject save(HttpServletRequest request,OmCourse bean){
    	AjaxObject result = AjaxObject.newOk("success");
		try {
			String fntype = request.getParameter("fntype");
			if ("update".equals(fntype)) {
				service.updateByPrimaryKeySelective(bean);
			}else{
				bean.setCourseId(UUIDUtil.getUUID());
				service.insert(bean);
			}
		} catch (Exception e) {
			logger.error("保存修改科目信息："  , e);
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
			logger.error("删除科目信息："  , e);
			return AjaxObject.newError(e.getMessage());
		}
		return result;
	}
    
    //名称是否重复
    @RequestMapping(value = { "/isExistName" })
   	@ResponseBody
   	public AjaxObject isExistName(String courseName,String courseId,HttpServletRequest request){
    	OmCourse bean= service.selectByCourseName(courseName);
    	boolean flag = false;
    	if(bean!=null&&!bean.getCourseId().equals(courseId)){
    		flag = true;
    	}
   		return AjaxObject.newOk("操作成功",flag);
   	}
    

    //获取所有科目
    @RequestMapping(value = { "/getAllCourseList" })
   	@ResponseBody
   	public AjaxObject getAllCourseList(HttpServletRequest request){
    	List<OmCourse> list = service.selectAll();
   		return AjaxObject.newOk("操作成功",list);
   	}
    
    /**
	 * 左侧树列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getTreeList")
    @ResponseBody
    public AjaxObject getTreeList(HttpServletRequest request) throws Exception {
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		List<OmCourse> list = service.selectAll();
		for(OmCourse bean:list){
  			Map<String,Object> map = new HashMap<String,Object>();
  			map.put("title", bean.getCourseName());
  			map.put("value", bean.getCourseId());
  			map.put("pid", "0");
  			dataList.add(map);
  		}
		Map<String,Object> parentMap = new HashMap<String,Object>();
		parentMap.put("title", "全部科目");
		parentMap.put("value", "");
		parentMap.put("id","0");
		parentMap.put("children", dataList);
		parentMap.put("open", true);
		parentMap.put("spread", true);
		List<Map<String,Object>> parentList = new ArrayList<Map<String,Object>>();
		parentList.add(parentMap);
  		return AjaxObject.newOk("操作成功",parentList);
    }
  		
}
