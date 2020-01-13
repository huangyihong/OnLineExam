package com.plg.shiro.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.plg.shiro.entity.OmRole;
import com.plg.shiro.entity.OmUser;
import com.plg.shiro.util.dwz.LayuiPage;

public interface IRoleService {
    int deleteByPrimaryKey(String roleId);

    int insert(OmRole record);

    OmRole selectByPrimaryKey(String roleId);
    
    /**
     * 根据用户ID查询用户角色列表
     * @param userId
     * @return
     */
    List<OmRole> selectByUserId(String userId);
    
    List<OmRole> selectAll();

    int updateByPrimaryKeySelective(OmRole record);
    
    List<OmRole> findPageList(HttpServletRequest request, LayuiPage page);

	void deleteBatch(String ids);

	OmRole selectByRoleCode(String roleCode);
}
