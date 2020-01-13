package com.plg.shiro.controller.system;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plg.shiro.entity.OmUserGroup;
import com.plg.shiro.service.IUserGroupService;
import com.plg.shiro.util.UUIDUtil;
import com.plg.shiro.util.dwz.AjaxObject;
import com.plg.shiro.util.dwz.LayuiPage;

/**
 *用户分组管理
 */
@Controller
@RequestMapping({ "/admin/system/omUserGroup" })
public class UserGroupController {
	
	private static Logger logger = LoggerFactory.getLogger(UserGroupController.class);

	@Resource
	private IUserGroupService service;
	
	private static final String Group_PATH = "admin/system/omUserGroup/";
	
	/**
	 * 用户分组管理列表页
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/list" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String list(HttpServletRequest request) {
		return Group_PATH+"list";
	}
	
	//获取列表数据
  	@RequestMapping("getList")
    @ResponseBody
  	public AjaxObject getList(HttpServletRequest request,LayuiPage page) throws Exception {
  	  	AjaxObject result = AjaxObject.newOk("success");
  			try {
  				List<OmUserGroup> list = service.findPageList(request, page);
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
  		String groupId = request.getParameter("groupId");
  		OmUserGroup bean = new OmUserGroup();
		if(StringUtils.isNotBlank(groupId)){
			bean = service.selectByPrimaryKey(groupId);
    	}
		request.setAttribute("bean", bean);
    	request.setAttribute("fntype", request.getParameter("fntype"));
    	return Group_PATH+"create";
  	}

    //保存修改操作
    @RequestMapping("save")
    @ResponseBody
	public AjaxObject save(HttpServletRequest request,OmUserGroup bean){
    	AjaxObject result = AjaxObject.newOk("success");
		try {
			String fntype = request.getParameter("fntype");
			if ("update".equals(fntype)) {
				service.updateByPrimaryKeySelective(bean);
			}else{
				bean.setGroupId(UUIDUtil.getUUID());
				service.insert(bean);
			}
		} catch (Exception e) {
			logger.error("保存修改用户分组信息："  , e);
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
			logger.error("删除用户分组信息："  , e);
			return AjaxObject.newError(e.getMessage());
		}
		return result;
	}
    
    //名称是否重复
    @RequestMapping(value = { "/isExistName" })
   	@ResponseBody
   	public AjaxObject isExistName(String groupName,String groupId,HttpServletRequest request){
    	OmUserGroup bean= service.selectByGroupName(groupName);
    	boolean flag = false;
    	if(bean!=null&&!bean.getGroupId().equals(groupId)){
    		flag = true;
    	}
   		return AjaxObject.newOk("操作成功",flag);
   	}
    

    //获取所有用户分组
    @RequestMapping(value = { "/getAllGroupList" })
   	@ResponseBody
   	public AjaxObject getAllGroupList(HttpServletRequest request){
    	List<OmUserGroup> list = service.selectAll();
   		return AjaxObject.newOk("操作成功",list);
   	}
    
  		
}
