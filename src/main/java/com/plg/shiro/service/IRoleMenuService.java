package com.plg.shiro.service;

import java.util.List;

import com.plg.shiro.entity.RoleMenu;

/**
 * 用户-角色关系服务
 * 
 * @author Thinkpad
 *
 */
public interface IRoleMenuService {
	int deleteByPrimaryKey(String id);

	int insert(RoleMenu record);

	RoleMenu selectByPrimaryKey(String id);
	
	List<RoleMenu> selectAll();

	int updateByPrimaryKeySelective(RoleMenu record);

	List<RoleMenu> selectByRoleId(String roleId);

	void saveRoleMenu(String roleId, String menuIds);
}
