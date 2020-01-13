package com.plg.shiro.service;

import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.plg.shiro.entity.OmMenu;
import com.plg.shiro.entity.OmUser;
import com.plg.shiro.util.dwz.LayuiPage;

public interface IMenuService {
    int deleteByPrimaryKey(String menuId);

    int insert(OmMenu record);

    OmMenu selectByPrimaryKey(String menuId);
    
    List<OmMenu> queryUseMenu(String userId);
    
    HashSet<String> queryUseMenuIds(String userId);
    
    List<OmMenu> selectAll();

    int updateByPrimaryKeySelective(OmMenu record);
    
    List<OmMenu> findPageList(HttpServletRequest request, LayuiPage page);

	void deleteBatch(String ids);

	List<OmMenu> selectListByParentId(String parentId);

	OmMenu selectByMenuCode(String menuCode);
	
	
}
