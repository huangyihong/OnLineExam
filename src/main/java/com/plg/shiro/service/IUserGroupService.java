package com.plg.shiro.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.plg.shiro.entity.OmUserGroup;
import com.plg.shiro.util.dwz.LayuiPage;

public interface IUserGroupService {
    int deleteByPrimaryKey(String groupId);

    int insert(OmUserGroup record);

    OmUserGroup selectByPrimaryKey(String groupId);
    
    List<OmUserGroup> selectAll();

    int updateByPrimaryKeySelective(OmUserGroup record);
    
    List<OmUserGroup> findPageList(HttpServletRequest request, LayuiPage page);

	void deleteBatch(String ids);

	OmUserGroup selectByGroupName(String courseName);
	
}
