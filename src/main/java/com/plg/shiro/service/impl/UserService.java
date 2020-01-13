package com.plg.shiro.service.impl;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.plg.shiro.dao.OmUserMapper;
import com.plg.shiro.entity.OmUser;
import com.plg.shiro.entity.OmUserExample;
import com.plg.shiro.service.IUserService;
import com.plg.shiro.util.dwz.LayuiPage;

@Service
public class UserService implements IUserService {
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Resource
	private OmUserMapper omUserMapper;

	@Override
	public int deleteByPrimaryKey(String userId) {
		logger.info("删除用户:{}", userId);
		return omUserMapper.deleteByPrimaryKey(userId);
	}

	@Override
	public int insert(OmUser record) {
		logger.info("新增用户：{}", record.getUserId());
		return omUserMapper.insert(record);
	}

	@Override
	public OmUser selectByPrimaryKey(String userId) {
		logger.info("查询用户：{}", userId);
		return omUserMapper.selectByPrimaryKey(userId);
	}
	
	@Override
	public OmUser selectByUserName(String userName) {
		logger.info("通过用户名查询用户，用户名userName：{}", userName);
		OmUserExample example = new OmUserExample();
		OmUserExample.Criteria criteria = example.createCriteria();
		criteria.andUserNameEqualTo(userName);
		List<OmUser> list = omUserMapper.selectByExample(example);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<OmUser> selectAll() {
		logger.info("查询所有用户");
		OmUserExample example = new OmUserExample();
		OmUserExample.Criteria criteria = example.createCriteria();
		example.setOrderByClause("USER_NAME");
		return omUserMapper.selectByExample(example);
	}
	
	@Override
	public int updateByPrimaryKeySelective(OmUser record) {
		logger.info("更新用户：{}", record.getUserId());
		return omUserMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<OmUser> findPageList(HttpServletRequest request, LayuiPage page) {
		OmUserExample example = new OmUserExample();
		OmUserExample.Criteria criteria = example.createCriteria();
		String userName = request.getParameter("userName");
		if(StringUtils.isNoneBlank(userName)){
			criteria.andUserNameLike("%"+userName+"%");
		}
		String realName = request.getParameter("realName");
		if(StringUtils.isNoneBlank(realName)){
			criteria.andRealNameLike("%"+realName+"%");
		}
		String userType = request.getParameter("userType");
		if(StringUtils.isNoneBlank(userType)){
			criteria.andUserTypeEqualTo(userType);
		}
		String unit = request.getParameter("unit");
		if(StringUtils.isNoneBlank(unit)){
			criteria.andUnitLike("%"+unit+"%");
		}
		String phone = request.getParameter("phone");
		if(StringUtils.isNoneBlank(phone)){
			criteria.andPhoneEqualTo(phone);
		}
		String groupName = request.getParameter("groupName");
		if(StringUtils.isNoneBlank(groupName)){
			criteria.andGroupNameLike("%"+groupName+"%");
		}
		example.setLimitPageSize(page.getLimit());
		example.setLimitStart(page.limitStart());
		page.setTotalCount(omUserMapper.countByExample(example));
		example.setOrderByClause("USER_NAME");
		return omUserMapper.selectByExample(example);
	}

	@Override
	public void deleteBatch(String ids) {
		OmUserExample example = new OmUserExample();
		OmUserExample.Criteria c = example.createCriteria();
        c.andUserIdIn(Arrays.asList(ids.split(",")));
        omUserMapper.deleteByExample(example);
	}
}
