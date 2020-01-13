package com.plg.shiro.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.plg.shiro.dao.UserRoleMapper;
import com.plg.shiro.entity.UserRole;
import com.plg.shiro.entity.UserRoleExample;
import com.plg.shiro.service.IUserRoleService;
import com.plg.shiro.util.DateUtil;
import com.plg.shiro.util.UUIDUtil;

@Service
public class UserRoleService implements IUserRoleService {
	private static final Logger logger = LoggerFactory.getLogger(UserRoleService.class);

	@Resource
	private UserRoleMapper userRoleMapper;

	@Override
	public int deleteByPrimaryKey(String id) {
		logger.info("删除用户-角色关系：{}", id);
		return userRoleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(UserRole record) {
		logger.info("新增用户-角色关系：{}", record.getId());
		return userRoleMapper.insert(record);
	}

	@Override
	public UserRole selectByPrimaryKey(String id) {
		logger.info("查询用户-角色关系：{}", id);
		return userRoleMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<UserRole> selectAll() {
		logger.info("查询所有用户-角色关系");
		UserRoleExample example = new UserRoleExample();
		UserRoleExample.Criteria criteria = example.createCriteria();
		return userRoleMapper.selectByExample(example);
	}
	
	@Override
	public int updateByPrimaryKeySelective(UserRole record) {
		logger.info("更新用户-角色关系：{}", record.getId());
		return userRoleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<UserRole> selectByUserId(String userId) {
		logger.info("查询用户拥有的角色：{}", userId);
		UserRoleExample example = new UserRoleExample();
		UserRoleExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		return userRoleMapper.selectByExample(example);
	}

	@Override
	public void saveUserRole(String userId, String roleIds) {
		//删除原来的记录
		UserRoleExample example = new UserRoleExample();
		UserRoleExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		userRoleMapper.deleteByExample(example);
		
		//插入新记录
		if(StringUtils.isNotBlank(roleIds)){
			List<String> roleId_arr = Arrays.asList(roleIds.split(","));
			for(String roleId:roleId_arr){
				UserRole userRole =new UserRole();
				userRole.setId(UUIDUtil.getUUID());
				userRole.setUserId(userId);
				userRole.setRoleId(roleId);
				userRole.setCreateTime(DateUtil.dateParse(new Date(),""));
				userRoleMapper.insertSelective(userRole);
			}
		}
		
	}

	@Override
	public List<UserRole> selectByRoleId(String roleId) {
		logger.info("查询角色授权的用户：{}", roleId);
		UserRoleExample example = new UserRoleExample();
		UserRoleExample.Criteria criteria = example.createCriteria();
		criteria.andRoleIdEqualTo(roleId);
		return userRoleMapper.selectByExample(example);
	}
	
	@Override
	public void saveRoleUser(String userIds, String roleId) {
		//删除原来的记录
		UserRoleExample example = new UserRoleExample();
		UserRoleExample.Criteria criteria = example.createCriteria();
		criteria.andRoleIdEqualTo(roleId);
		userRoleMapper.deleteByExample(example);
		
		//插入新记录
		if(StringUtils.isNotBlank(userIds)){
			List<String> userId_arr = Arrays.asList(userIds.split(","));
			for(String userId:userId_arr){
				UserRole userRole =new UserRole();
				userRole.setId(UUIDUtil.getUUID());
				userRole.setUserId(userId);
				userRole.setRoleId(roleId);
				userRole.setCreateTime(DateUtil.dateParse(new Date(),""));
				userRoleMapper.insertSelective(userRole);
			}
		}
		
	}

}
