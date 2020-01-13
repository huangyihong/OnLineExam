package com.plg.shiro.controller.system;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plg.shiro.entity.OmRole;
import com.plg.shiro.entity.RoleMenu;
import com.plg.shiro.entity.UserRole;
import com.plg.shiro.service.IRoleService;
import com.plg.shiro.service.IUserRoleService;
import com.plg.shiro.util.DateUtil;
import com.plg.shiro.util.UUIDUtil;
import com.plg.shiro.util.dwz.AjaxObject;
import com.plg.shiro.util.dwz.LayuiPage;

/**
 * 角色模块
 */
@Controller
@RequestMapping({ "/admin/system/omRole" })
public class RoleController {
	
	private static Logger logger = LoggerFactory.getLogger(RoleController.class);

	@Resource
	private IRoleService service;
	
	@Resource
	private IUserRoleService userRoleService;
	
	private static final String ROLE_PATH = "admin/system/omRole/";
	
	/**
	 * 角色管理列表页
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/list" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String list(HttpServletRequest request) {
		return ROLE_PATH+"list";
	}
	
	//获取列表数据
  	@RequestMapping("getList")
    @ResponseBody
  	public AjaxObject getList(HttpServletRequest request,LayuiPage page) throws Exception {
  	  	AjaxObject result = AjaxObject.newOk("success");
  			try {
  				List<OmRole> list = service.findPageList(request, page);
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
  		String roleId = request.getParameter("roleId");
  		OmRole bean = new OmRole();
		if(StringUtils.isNotBlank(roleId)){
			bean = service.selectByPrimaryKey(roleId);
    	}
		request.setAttribute("bean", bean);
    	request.setAttribute("fntype", request.getParameter("fntype"));
    	return ROLE_PATH+"create";
  	}

    //保存修改操作
    @RequestMapping("save")
    @ResponseBody
	public AjaxObject save(HttpServletRequest request,OmRole bean){
    	AjaxObject result = AjaxObject.newOk("success");
		try {
			String fntype = request.getParameter("fntype");
			if ("update".equals(fntype)) {
				bean.setUpdateTime(DateUtil.dateParse(new Date(),""));
				service.updateByPrimaryKeySelective(bean);
			}else{
				bean.setRoleId(UUIDUtil.getUUID());
				bean.setCreateTime(DateUtil.dateParse(new Date(),""));
				service.insert(bean);
			}
		} catch (Exception e) {
			logger.error("保存修改角色信息："  , e);
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
			logger.error("删除角色信息："  , e);
			return AjaxObject.newError(e.getMessage());
		}
		return result;
	}
    
    //角色编码是否重复
    @RequestMapping(value = { "/isExistName" })
   	@ResponseBody
   	public AjaxObject isExistName(String roleCode,String roleId,HttpServletRequest request){
    	OmRole bean= service.selectByRoleCode(roleCode);
    	boolean flag = false;
    	if(bean!=null&&!bean.getRoleId().equals(roleId)){
    		flag = true;
    	}
   		return AjaxObject.newOk("操作成功",flag);
   	}
    
    //获取所有角色
    @RequestMapping(value = { "/getAllRoleList" })
   	@ResponseBody
   	public AjaxObject getAllRoleList(HttpServletRequest request){
    	List<OmRole> list = service.selectAll();
   		return AjaxObject.newOk("操作成功",list);
   	}
    
    //获取用户角色
    @RequestMapping(value = { "/getUserRoleList" })
   	@ResponseBody
   	public AjaxObject getUserRoleList(HttpServletRequest request,String userId){
    	List<UserRole> list = userRoleService.selectByUserId(userId);
   		return AjaxObject.newOk("操作成功",list);
   	}
    
    //保存用户角色操作
    @RequestMapping("saveUserRole")
    @ResponseBody
	public AjaxObject saveUserRole(HttpServletRequest request){
    	AjaxObject result = AjaxObject.newOk("success");
		try {
			String userId = request.getParameter("userId");
			String roleIds = request.getParameter("roleIds");
			userRoleService.saveUserRole(userId,roleIds);
		} catch (Exception e) {
			logger.error("保存用户角色信息："  , e);
			return AjaxObject.newError(e.getMessage());
		}
		return result;
	}
    
    /**
   	 * 角色菜单授权页面
   	 * @param request
   	 * @return
   	 */
   	@RequestMapping(value = { "/assignMenu" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
   	public String assignRole(HttpServletRequest request) {
   		String roleId = request.getParameter("roleId");
   		request.setAttribute("roleId", roleId);
   		return ROLE_PATH+"assignMenu";
   	}
   	
   	/**
   	 * 角色用户授权页面
   	 * @param request
   	 * @return
   	 */
   	@RequestMapping(value = { "/assignUser" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
   	public String assignUser(HttpServletRequest request) {
   		String roleId = request.getParameter("roleId");
   		request.setAttribute("roleId", roleId);
   		return ROLE_PATH+"assignUser";
   	}
   	
    //获取角色授权用户
    @RequestMapping(value = { "/getRoleUserList" })
   	@ResponseBody
   	public AjaxObject getRoleMenuList(HttpServletRequest request,String roleId){
    	List<UserRole> list = userRoleService.selectByRoleId(roleId);
   		return AjaxObject.newOk("操作成功",list);
   	}
    
    //保存角色授权用户操作
    @RequestMapping("saveRoleUser")
    @ResponseBody
	public AjaxObject saveRoleUser(HttpServletRequest request){
    	AjaxObject result = AjaxObject.newOk("success");
		try {
			String userIds = request.getParameter("userIds");
			String roleId = request.getParameter("roleId");
			userRoleService.saveRoleUser(userIds,roleId);
		} catch (Exception e) {
			logger.error("保存用户角色信息："  , e);
			return AjaxObject.newError(e.getMessage());
		}
		return result;
	}

}
