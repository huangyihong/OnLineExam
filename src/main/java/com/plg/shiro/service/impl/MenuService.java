package com.plg.shiro.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.plg.shiro.dao.OmMenuMapper;
import com.plg.shiro.entity.OmMenu;
import com.plg.shiro.entity.OmMenuExample;
import com.plg.shiro.entity.OmRole;
import com.plg.shiro.entity.RoleMenu;
import com.plg.shiro.service.IMenuService;
import com.plg.shiro.util.dwz.LayuiPage;

@Service
public class MenuService implements IMenuService {
	private static final Logger logger = LoggerFactory.getLogger(MenuService.class);

	@Resource
	private OmMenuMapper omMenuMapper;
	
	@Resource
	private RoleService roleService;
	
	@Resource
	private RoleMenuService roleMenuService;
	
	@Override
	public int deleteByPrimaryKey(String menuId) {
		logger.info("删除菜单：{}", menuId);
		return omMenuMapper.deleteByPrimaryKey(menuId);
	}

	@Override
	public int insert(OmMenu record) {
		logger.info("新增菜单：{}", record.getMenuId());
		return omMenuMapper.insert(record);
	}

	@Override
	public OmMenu selectByPrimaryKey(String menuId) {
		logger.info("查询菜单：{}", menuId);
		return omMenuMapper.selectByPrimaryKey(menuId);
	}
	
	@Override
	public List<OmMenu> queryUseMenu(String userId) {
		logger.info("查询用户菜单，userId{}", userId);
		HashSet<String> menuIdSet = queryUseMenuIds(userId);
        if(menuIdSet.size()==0){
        	return null;
        }
        OmMenuExample example = new OmMenuExample();
        OmMenuExample.Criteria criteria = example.createCriteria();
        criteria.andMenuIdIn(new ArrayList<>(menuIdSet));
		return omMenuMapper.selectByExample(example);
	}
	
	@Override
	public HashSet<String> queryUseMenuIds(String userId) {
		logger.info("查询用户菜单menuids，userId{}", userId);
		List<OmRole> roleList = roleService.selectByUserId(userId);
		List<RoleMenu> roleMenuList = new ArrayList<RoleMenu>();
		for(OmRole role:roleList){
			roleMenuList.addAll(roleMenuService.selectByRoleId(role.getRoleId()));
		}
		HashSet<String> menuIdSet = new HashSet<String>();
        for(RoleMenu roleMenu:roleMenuList){
        	menuIdSet.add(roleMenu.getMenuId());
        }
		return menuIdSet;
	}

	@Override
	public List<OmMenu> selectAll() {
		logger.info("查询所有菜单");
		OmMenuExample example = new OmMenuExample();
		OmMenuExample.Criteria criteria = example.createCriteria();
		example.setOrderByClause("PARENT_ID,MENU_ORDER");
		return omMenuMapper.selectByExample(example);
	}
	
	@Override
	public int updateByPrimaryKeySelective(OmMenu record) {
		logger.info("更新菜单：{}", record.getMenuId());
		return omMenuMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<OmMenu> findPageList(HttpServletRequest request, LayuiPage page) {
		OmMenuExample example = new OmMenuExample();
		OmMenuExample.Criteria criteria = example.createCriteria();
		String menuName = request.getParameter("menuName");
		if(StringUtils.isNoneBlank(menuName)){
			criteria.andMenuNameLike("%"+menuName+"%");
		}
		String parentId = request.getParameter("parentId");
		if(StringUtils.isNoneBlank(parentId)){
			criteria.andParentIdEqualTo(parentId);
		}
		String menuUrl = request.getParameter("menuUrl");
		if(StringUtils.isNoneBlank(menuUrl)){
			criteria.andParentIdLike("%"+menuUrl+"%");
		}
		example.setLimitPageSize(page.getLimit());
		example.setLimitStart(page.limitStart());
		page.setTotalCount(omMenuMapper.countByExample(example));
		example.setOrderByClause("PARENT_ID,MENU_ORDER");
		return omMenuMapper.selectByExample(example);
	}

	@Override
	public void deleteBatch(String ids) {
		OmMenuExample example = new OmMenuExample();
		OmMenuExample.Criteria c = example.createCriteria();
        c.andMenuIdIn(Arrays.asList(ids.split(",")));
        omMenuMapper.deleteByExample(example);
	}

	@Override
	public List<OmMenu> selectListByParentId(String parentId) {
		logger.info("查询菜单，parentId{}", parentId);
		OmMenuExample example = new OmMenuExample();
		OmMenuExample.Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		example.setOrderByClause("MENU_ORDER");
		return omMenuMapper.selectByExample(example);
	}
	
	@Override
	public OmMenu selectByMenuCode(String menuCode) {
		OmMenuExample example = new OmMenuExample();
		OmMenuExample.Criteria criteria = example.createCriteria();
		criteria.andMenuCodeEqualTo(menuCode);
		List<OmMenu> list = omMenuMapper.selectByExample(example);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
}
