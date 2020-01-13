package com.plg.shiro.controller.system;

import java.util.ArrayList;
import java.util.Date;
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

import com.plg.shiro.entity.OmMenu;
import com.plg.shiro.entity.OmRole;
import com.plg.shiro.entity.RoleMenu;
import com.plg.shiro.service.IMenuService;
import com.plg.shiro.service.IRoleMenuService;
import com.plg.shiro.util.DateUtil;
import com.plg.shiro.util.UUIDUtil;
import com.plg.shiro.util.dwz.AjaxObject;
import com.plg.shiro.util.dwz.LayuiPage;

/**
 * 菜单模块
 */
@Controller
@RequestMapping({ "/admin/system/omMenu" })
public class MenuController {
	
	private static Logger logger = LoggerFactory.getLogger(MenuController.class);

	@Resource
	private IMenuService service;
	
	@Resource
	private IRoleMenuService roleMenuService;
	
	private static final String MENU_PATH = "admin/system/omMenu/";
	
	/**
	 * 菜单管理列表页
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/list" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String list(HttpServletRequest request) {
		//一级菜单
    	List<OmMenu> parentMenuList = service.selectListByParentId("0");
    	request.setAttribute("parentMenuList", parentMenuList);
		return MENU_PATH+"list";
	}
	
	//获取列表数据
  	@RequestMapping("getList")
    @ResponseBody
  	public AjaxObject getList(HttpServletRequest request,LayuiPage page) throws Exception {
  	  	AjaxObject result = AjaxObject.newOk("success");
  			try {
  				List<OmMenu> list = service.findPageList(request, page);
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
  		String menuId = request.getParameter("menuId");
  		OmMenu bean = new OmMenu();
		if(StringUtils.isNotBlank(menuId)){
			bean = service.selectByPrimaryKey(menuId);
    	}else{
    		bean.setMenuOrder(1);
    	}
		request.setAttribute("bean", bean);
    	request.setAttribute("fntype", request.getParameter("fntype"));
    	//一级菜单
    	List<OmMenu> parentMenuList = service.selectListByParentId("0");
    	request.setAttribute("parentMenuList", parentMenuList);
    	return MENU_PATH+"create";
  	}

    //保存修改操作
    @RequestMapping("save")
    @ResponseBody
	public AjaxObject save(HttpServletRequest request,OmMenu bean){
    	AjaxObject result = AjaxObject.newOk("success");
		try {
			String fntype = request.getParameter("fntype");
			if ("update".equals(fntype)) {
				bean.setUpdateTime(DateUtil.dateParse(new Date(),""));
				service.updateByPrimaryKeySelective(bean);
			}else{
				bean.setMenuId(UUIDUtil.getUUID());
				bean.setCreateTime(DateUtil.dateParse(new Date(),""));
				service.insert(bean);
			}
		} catch (Exception e) {
			logger.error("保存修改菜单信息："  , e);
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
			logger.error("删除菜单信息："  , e);
			return AjaxObject.newError(e.getMessage());
		}
		return result;
	}

    //获取所有菜单
    @RequestMapping(value = { "/getAllMenuList" })
   	@ResponseBody
   	public AjaxObject getAllMenuList(HttpServletRequest request){
    	List<OmMenu> allMenuList = service.selectAll();
  		List<OmMenu> parentMenuList = service.selectListByParentId("0");
  		List<Map<String,Object>> menuList = new ArrayList<Map<String,Object>>();
  		for(OmMenu parentMenu:parentMenuList){
  			Map<String,Object> map = new HashMap<String,Object>();
  			map.put("title", parentMenu.getMenuName());
  			map.put("name", parentMenu.getMenuName());
  			map.put("id", parentMenu.getMenuId());
  			List<Map<String,Object>> childMenuList = new ArrayList<Map<String,Object>>();
  			for(OmMenu menu:allMenuList){
  				if(parentMenu.getMenuId().equals(menu.getParentId())){
  					Map<String,Object> childMap = new HashMap<String,Object>();
  					childMap.put("title", menu.getMenuName());
  					childMap.put("name", menu.getMenuName());
  					childMap.put("id", menu.getMenuId());
  					childMenuList.add(childMap);
  				}
  			}
  			if(childMenuList.size()>0){
  				map.put("children", childMenuList);
  				map.put("open", true);
  			}
  			
  			menuList.add(map);
  		}
   		return AjaxObject.newOk("操作成功",menuList);
   	}
    
    //获取角色菜单
    @RequestMapping(value = { "/getRoleMenuList" })
   	@ResponseBody
   	public AjaxObject getRoleMenuList(HttpServletRequest request,String roleId){
    	List<RoleMenu> list = roleMenuService.selectByRoleId(roleId);
   		return AjaxObject.newOk("操作成功",list);
   	}
    
    //保存角色菜单操作
    @RequestMapping("saveRoleMenu")
    @ResponseBody
	public AjaxObject saveRoleMenu(HttpServletRequest request){
    	AjaxObject result = AjaxObject.newOk("success");
		try {
			String roleId = request.getParameter("roleId");
			String menuIds = request.getParameter("menuIds");
			roleMenuService.saveRoleMenu(roleId,menuIds);
		} catch (Exception e) {
			logger.error("保存角色菜单信息："  , e);
			return AjaxObject.newError(e.getMessage());
		}
		return result;
	}
    
    //菜单授权编码是否重复
    @RequestMapping(value = { "/isExistName" })
   	@ResponseBody
   	public AjaxObject isExistName(String menuCode,String menuId,HttpServletRequest request){
    	OmMenu bean= service.selectByMenuCode(menuCode);
    	boolean flag = false;
    	if(bean!=null&&!bean.getMenuId().equals(menuId)){
    		flag = true;
    	}
   		return AjaxObject.newOk("操作成功",flag);
   	}
    
    
  		
}
