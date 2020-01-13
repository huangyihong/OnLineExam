package com.plg.shiro.service.impl;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.plg.shiro.dao.OmUserGroupMapper;
import com.plg.shiro.entity.OmUserGroup;
import com.plg.shiro.entity.OmUserGroupExample;
import com.plg.shiro.service.IUserGroupService;
import com.plg.shiro.util.dwz.LayuiPage;

@Service
public class UserGroupService implements IUserGroupService {
	private static final Logger logger = LoggerFactory.getLogger(UserGroupService.class);

	@Resource
	private OmUserGroupMapper omUserGroupMapper;
	
	@Override
	public int deleteByPrimaryKey(String groupId) {
		logger.info("删除用户分组：{}", groupId);
		return omUserGroupMapper.deleteByPrimaryKey(groupId);
	}

	@Override
	public int insert(OmUserGroup record) {
		logger.info("新增用户分组：{}", record.getGroupId());
		return omUserGroupMapper.insert(record);
	}

	@Override
	public OmUserGroup selectByPrimaryKey(String groupId) {
		logger.info("查询用户分组：{}", groupId);
		return omUserGroupMapper.selectByPrimaryKey(groupId);
	}
	
	
	@Override
	public List<OmUserGroup> selectAll() {
		logger.info("查询所有用户分组");
		OmUserGroupExample example = new OmUserGroupExample();
		OmUserGroupExample.Criteria criteria = example.createCriteria();
		example.setOrderByClause("GROUP_NAME");
		return omUserGroupMapper.selectByExample(example);
	}
	
	@Override
	public int updateByPrimaryKeySelective(OmUserGroup record) {
		logger.info("更新用户分组：{}", record.getGroupId());
		return omUserGroupMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<OmUserGroup> findPageList(HttpServletRequest request, LayuiPage page) {
		OmUserGroupExample example = new OmUserGroupExample();
		OmUserGroupExample.Criteria criteria = example.createCriteria();
		String groupName = request.getParameter("groupName");
		if(StringUtils.isNoneBlank(groupName)){
			criteria.andGroupNameLike("%"+groupName+"%");
		}
		example.setLimitPageSize(page.getLimit());
		example.setLimitStart(page.limitStart());
		page.setTotalCount(omUserGroupMapper.countByExample(example));
		example.setOrderByClause("GROUP_NAME");
		return omUserGroupMapper.selectByExample(example);
	}

	@Override
	public void deleteBatch(String ids) {
		OmUserGroupExample example = new OmUserGroupExample();
		OmUserGroupExample.Criteria c = example.createCriteria();
        c.andGroupIdIn(Arrays.asList(ids.split(",")));
        omUserGroupMapper.deleteByExample(example);
	}

	@Override
	public OmUserGroup selectByGroupName(String groupName) {
		OmUserGroupExample example = new OmUserGroupExample();
		OmUserGroupExample.Criteria criteria = example.createCriteria();
		criteria.andGroupNameEqualTo(groupName);
		List<OmUserGroup> list = omUserGroupMapper.selectByExample(example);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
}
