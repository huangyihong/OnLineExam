package com.plg.shiro.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plg.shiro.common.BaseConstant;
import com.plg.shiro.entity.OmMenu;
import com.plg.shiro.entity.OmUser;
import com.plg.shiro.service.IMenuService;

/**
 * 菜单控制器
 * 
 * @author Thinkpad
 *
 */
@Controller
@RequestMapping("menu")
public class Menu2Controller {
	private static Logger logger = LoggerFactory.getLogger(Menu2Controller.class);

	@Resource
	private IMenuService menuService;
	
	/**
	 * 获取用户菜单
	 */
	@RequestMapping("use_menu")
	@ResponseBody
	public Map<String, Object> queryUseMenu(HttpServletRequest request) {
		OmUser omUser = (OmUser) SecurityUtils.getSubject().getSession().getAttribute(BaseConstant.USER_SESSION_KEY);
		logger.info("获取用户菜单，用户ID：{}", omUser.getUserId());
		
		Map<String, Object> data = new HashMap<>();
		List<OmMenu> menuList = menuService.queryUseMenu(""+omUser.getUserId());
		data.put("menuList", menuList);
		
		return data;
	}

	/**
	 * 人事部页面
	 * 
	 * @return
	 */
	@RequiresPermissions("personnel_department")
	@RequestMapping("personnel_department")
	public String personnelDepartment(HttpServletRequest request) {
		logger.info("============人事部=============");
		return "menu/personnel_department";
	}

	/**
	 * 财务部页面
	 * 
	 * @return
	 */
	@RequiresPermissions("accounting_department")
	@RequestMapping("accounting_department")
	public String accountingDepartment(HttpServletRequest request) {
		logger.info("============财务部=============");
		return "menu/accounting_department";
	}

	/**
	 * 行政部页面
	 * 
	 * @return
	 */
	@RequiresPermissions("administrative_department")
	@RequestMapping("administrative_department")
	public String administrativeDepartment(HttpServletRequest request) {
		logger.info("============行政部=============");
		return "menu/administrative_department";
	}

	/**
	 * 运维部页面
	 * 
	 * @return
	 */
	@RequiresPermissions("operation_department")
	@RequestMapping("operation_department")
	public String operationDepartment(HttpServletRequest request) {
		logger.info("============运维部=============");
		return "menu/operation_department";
	}

	/**
	 * 测试部页面
	 * 
	 * @return
	 */
	@RequiresPermissions("test_department")
	@RequestMapping("test_department")
	public String testDepartment(HttpServletRequest request) {
		logger.info("============测试部=============");
		return "menu/test_department";
	}

	/**
	 * 市场部页面
	 * 
	 * @return
	 */
	@RequiresPermissions("marketing_department")
	@RequestMapping("marketing_department")
	public String marketingDepartment(HttpServletRequest request) {
		logger.info("============市场部=============");
		return "menu/marketing_department";
	}
}
