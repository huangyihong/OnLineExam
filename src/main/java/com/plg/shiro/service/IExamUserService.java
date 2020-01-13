package com.plg.shiro.service;

import java.util.List;

import com.plg.shiro.entity.OmExamUser;
import com.plg.shiro.entity.OmUser;

public interface IExamUserService {

	void insertExamUser(OmExamUser bean, String userIds, String groupIds);

	List<OmExamUser> selectAll(String planId, String examUserType);

	public List<OmExamUser> selectUserExam(OmUser user, String examUserType);
}
