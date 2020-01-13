package com.plg.shiro.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.plg.shiro.entity.OmExamSubmit;
import com.plg.shiro.entity.OmUser;
import com.plg.shiro.entity.Vo.OmExamSubmitVo;
import com.plg.shiro.util.dwz.LayuiPage;

public interface IExamSubmitService {

	void insertExamSubmit(OmExamSubmit record);
	
	int updateByPrimaryKeySelective(OmExamSubmit record);

	OmExamSubmit selectByUserId(String planId,String paperId,String userId);
	
	List<OmExamSubmit> findPageList(HttpServletRequest request, LayuiPage page);

	List<OmExamSubmitVo> findVoPageList(HttpServletRequest request, LayuiPage page);
	
	OmExamSubmitVo selectVoByUserId(String planId,String paperId,String userId);
	
	public List<OmExamSubmitVo> findUserPageList(HttpServletRequest request, LayuiPage page, OmUser user,String examUserType,String status);
	
	public List<OmExamSubmit> findListByPlanId(String planId,String paperId,String status);
	
	OmExamSubmit selectByPrimaryKey(String submitId);
	
	public List<OmExamSubmitVo> findUserPageList(HttpServletRequest request, LayuiPage page, String status);

	void updateBatch(String ids, String status);

	List<OmExamSubmitVo> getList(HttpServletRequest request,String status);

	List<OmExamSubmitVo> findVoUserList(HttpServletRequest request, String status);
	
	Map<String, Object> sqlQueryScoreNumMaxMin(String planId,String status,String groupId);
	
}
