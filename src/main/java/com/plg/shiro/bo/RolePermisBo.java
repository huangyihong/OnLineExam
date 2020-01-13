package com.plg.shiro.bo;

import java.io.Serializable;

/**
 * 角色-权限关联BO
 * 
 * @author Thinkpad
 *
 */
public class RolePermisBo implements Serializable {
	private static final long serialVersionUID = 1804936585824874891L;

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
	 * 角色code
	 */
	private String roleCode;
	
	/**
	 * 权限ID
	 */
	private Integer permisId;

	/**
	 * 权限名称
	 */
	private String permisName;

	/**
	 * 权限code
	 */
	private String permisCode;

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

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public Integer getPermisId() {
		return permisId;
	}

	public void setPermisId(Integer permisId) {
		this.permisId = permisId;
	}

	public String getPermisName() {
		return permisName;
	}

	public void setPermisName(String permisName) {
		this.permisName = permisName;
	}

	public String getPermisCode() {
		return permisCode;
	}

	public void setPermisCode(String permisCode) {
		this.permisCode = permisCode;
	}
}
