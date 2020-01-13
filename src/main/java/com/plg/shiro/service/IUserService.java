package com.plg.shiro.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.plg.shiro.entity.OmUser;
import com.plg.shiro.util.dwz.LayuiPage;

/**
 * 用户服务接口
 *
 */
public interface IUserService {

	int deleteByPrimaryKey(String userId);

	int insert(OmUser record);

	OmUser selectByPrimaryKey(String userId);
	
	OmUser selectByUserName(String userName);

	int updateByPrimaryKeySelective(OmUser record);
	
	List<OmUser> selectAll();

	List<OmUser> findPageList(HttpServletRequest request, LayuiPage page);

	void deleteBatch(String ids);
}
