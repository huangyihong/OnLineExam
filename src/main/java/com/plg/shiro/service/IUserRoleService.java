package com.plg.shiro.service;

import java.util.List;

import com.plg.shiro.bo.UserRoleBo;
import com.plg.shiro.entity.UserRole;

/**
 * 用户-角色关系服务
 * 
 * @author Thinkpad
 *
 */
public interface IUserRoleService {
	int deleteByPrimaryKey(String id);

	int insert(UserRole record);

	UserRole selectByPrimaryKey(String id);
	
	List<UserRole> selectAll();

	int updateByPrimaryKeySelective(UserRole record);

	List<UserRole> selectByUserId(String userId);

	void saveUserRole(String userId, String roleIds);

	List<UserRole> selectByRoleId(String roleId);
	
	void saveRoleUser(String userIds, String roleId);
}
