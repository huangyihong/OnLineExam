package com.plg.shiro.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.plg.shiro.dao.OmRoleMapper;
import com.plg.shiro.entity.OmRole;
import com.plg.shiro.entity.OmRoleExample;
import com.plg.shiro.entity.UserRole;
import com.plg.shiro.service.IRoleService;
import com.plg.shiro.service.IUserRoleService;
import com.plg.shiro.util.dwz.LayuiPage;

@Service
public class RoleService implements IRoleService {
	private static final Logger logger = LoggerFactory.getLogger(RoleService.class);

	@Resource
	private OmRoleMapper omRoleMapper;
	
	@Resource
	private IUserRoleService userRoleService;

	@Override
	public int deleteByPrimaryKey(String roleId) {
		logger.info("删除角色：{}", roleId);
		return omRoleMapper.deleteByPrimaryKey(roleId);
	}

	@Override
	public int insert(OmRole record) {
		logger.info("新增角色：{}", record.getRoleId());
		return omRoleMapper.insert(record);
	}

	@Override
	public OmRole selectByPrimaryKey(String roleId) {
		logger.info("查询角色：{}", roleId);
		return omRoleMapper.selectByPrimaryKey(roleId);
	}
	
	@Override
	public List<OmRole> selectByUserId(String userId) {
		logger.info("根据用户ID，查询用户角色，用户ID：{}", userId);
		List<UserRole> list = userRoleService.selectByUserId(userId);
		List<String> roleIdsList = new ArrayList<String>();
		for(UserRole userRole:list){
			roleIdsList.add(userRole.getRoleId());
		}
		if(roleIdsList.size()==0){
			return null;
		}
		OmRoleExample example = new OmRoleExample();
		OmRoleExample.Criteria criteria = example.createCriteria();
		criteria.andRoleIdIn(roleIdsList);
		return omRoleMapper.selectByExample(example);
	}
	
	@Override
	public List<OmRole> selectAll() {
		logger.info("查询所有角色");
		OmRoleExample example = new OmRoleExample();
		OmRoleExample.Criteria criteria = example.createCriteria();
		example.setOrderByClause("ROLE_NAME");
		return omRoleMapper.selectByExample(example);
	}

	@Override
	public int updateByPrimaryKeySelective(OmRole record) {
		logger.info("更新角色：{}", record.getRoleId());
		return omRoleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<OmRole> findPageList(HttpServletRequest request, LayuiPage page) {
		OmRoleExample example = new OmRoleExample();
		OmRoleExample.Criteria criteria = example.createCriteria();
		String roleCode = request.getParameter("roleCode");
		if(StringUtils.isNoneBlank(roleCode)){
			criteria.andRoleCodeEqualTo(roleCode);
		}
		String roleName = request.getParameter("roleName");
		if(StringUtils.isNoneBlank(roleName)){
			criteria.andRoleNameLike("%"+roleName+"%");
		}
		String type = request.getParameter("type");
		if(StringUtils.isNoneBlank(type)){
			criteria.andTypeEqualTo((byte)Integer.parseInt(type));
		}
		example.setLimitPageSize(page.getLimit());
		example.setLimitStart(page.limitStart());
		page.setTotalCount(omRoleMapper.countByExample(example));
		example.setOrderByClause("ROLE_NAME");
		return omRoleMapper.selectByExample(example);
	}

	@Override
	public void deleteBatch(String ids) {
		OmRoleExample example = new OmRoleExample();
		OmRoleExample.Criteria c = example.createCriteria();
        c.andRoleIdIn(Arrays.asList(ids.split(",")));
        omRoleMapper.deleteByExample(example);
	}

	@Override
	public OmRole selectByRoleCode(String roleCode) {
		OmRoleExample example = new OmRoleExample();
		OmRoleExample.Criteria criteria = example.createCriteria();
		criteria.andRoleCodeEqualTo(roleCode);
		List<OmRole> list = omRoleMapper.selectByExample(example);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
}
