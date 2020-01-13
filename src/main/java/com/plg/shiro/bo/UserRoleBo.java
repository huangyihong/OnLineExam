package com.plg.shiro.bo;

import java.io.Serializable;

/**
 * 用户-角色关系BO
 * 
 * @author Thinkpad
 *
 */
public class UserRoleBo implements Serializable {
	private static final long serialVersionUID = 8350841768304100224L;

	/**
	 * id
	 */
	private Integer id;

	/**
	 * 角色ID
	 */
	private Integer roleId;
	
	/**
	 * 角色名称
	 */
	private String roleName;

	/**
	 * 用户ID
	 */
	private Integer userId;
	
	/**
	 * 用户姓名
	 */
	private String userName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
