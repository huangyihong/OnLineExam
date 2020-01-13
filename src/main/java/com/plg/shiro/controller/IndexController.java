package com.plg.shiro.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.plg.shiro.entity.OmMenu;
import com.plg.shiro.entity.OmUser;
import com.plg.shiro.service.IMenuService;

/**
 * 系统主页
 *
 */
@RequestMapping({ "/admin" })
@Controller
public class IndexController {
	private static Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Resource
	private IMenuService menuService;
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
		Subject currentUser = SecurityUtils.getSubject();
		OmUser omUser = (OmUser) SecurityUtils.getSubject().getSession().getAttribute("om_user");
		if(omUser!=null){
			model.addAttribute("currentUser",omUser.getUserName());
		}else {
			return "redirect:/admin/logout";
		}
		//获取菜单
		HashSet<String> menuIdSet = menuService.queryUseMenuIds(omUser.getUserId());//用户拥有菜单
		List<OmMenu> allMenuList = menuService.selectAll();
		List<OmMenu> parentMenuList = menuService.selectListByParentId("0");
		List<Map<String,Object>> menuList = new ArrayList<Map<String,Object>>();
		if(menuIdSet.size()>0){
			for(OmMenu parentMenu:parentMenuList){
				if(menuIdSet.contains(parentMenu.getMenuId())){
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("menu", parentMenu);
					List<OmMenu> childMenuList = new ArrayList<OmMenu>();
					for(OmMenu menu:allMenuList){
						if(parentMenu.getMenuId().equals(menu.getParentId())&&menuIdSet.contains(menu.getMenuId())){
							childMenuList.add(menu);
						}
					}
					map.put("childMenuList", childMenuList);
					menuList.add(map);
				}
			}
		}
		model.addAttribute("menuList",menuList);
		return "admin/index";
	}
}
