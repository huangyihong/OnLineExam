package com.plg.shiro.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.plg.shiro.dao.RoleMenuMapper;
import com.plg.shiro.entity.RoleMenu;
import com.plg.shiro.entity.RoleMenuExample;
import com.plg.shiro.service.IRoleMenuService;
import com.plg.shiro.util.DateUtil;
import com.plg.shiro.util.UUIDUtil;

@Service
public class RoleMenuService implements IRoleMenuService {
	private static final Logger logger = LoggerFactory.getLogger(RoleMenuService.class);

	@Resource
	private RoleMenuMapper roleMenuMapper;

	@Override
	public int deleteByPrimaryKey(String id) {
		logger.info("删除角色-菜单关系：{}", id);
		return roleMenuMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(RoleMenu record) {
		logger.info("新增角色-菜单关系：{}", record.getId());
		return roleMenuMapper.insert(record);
	}

	@Override
	public RoleMenu selectByPrimaryKey(String id) {
		logger.info("查询角色-菜单关系：{}", id);
		return roleMenuMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<RoleMenu> selectAll() {
		logger.info("查询所有角色-菜单关系");
		RoleMenuExample example = new RoleMenuExample();
		RoleMenuExample.Criteria criteria = example.createCriteria();
		return roleMenuMapper.selectByExample(example);
	}
	
	@Override
	public int updateByPrimaryKeySelective(RoleMenu record) {
		logger.info("更新角色-菜单关系：{}", record.getId());
		return roleMenuMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<RoleMenu> selectByRoleId(String roleId) {
		logger.info("查询角色拥有的菜单：{}", roleId);
		RoleMenuExample example = new RoleMenuExample();
		RoleMenuExample.Criteria criteria = example.createCriteria();
		criteria.andRoleIdEqualTo(roleId);
		return roleMenuMapper.selectByExample(example);
	}

	@Override
	public void saveRoleMenu(String roleId, String menuIds) {
		//删除原来的记录
		RoleMenuExample example = new RoleMenuExample();
		RoleMenuExample.Criteria criteria = example.createCriteria();
		criteria.andRoleIdEqualTo(roleId);
		roleMenuMapper.deleteByExample(example);
		
		//插入新记录
		if(StringUtils.isNotBlank(menuIds)){
			List<String> menuId_arr = Arrays.asList(menuIds.split(","));
			for(String menuId:menuId_arr){
				RoleMenu roleMenu =new RoleMenu();
				roleMenu.setId(UUIDUtil.getUUID());
				roleMenu.setRoleId(roleId);
				roleMenu.setMenuId(menuId);
				roleMenu.setCreateTime(DateUtil.dateParse(new Date(),""));
				roleMenuMapper.insertSelective(roleMenu);
			}
		}
		
	}

}
