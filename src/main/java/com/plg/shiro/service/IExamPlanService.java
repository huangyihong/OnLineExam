package com.plg.shiro.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.plg.shiro.entity.OmExamPlan;
import com.plg.shiro.entity.OmExamPlanVo;
import com.plg.shiro.entity.OmUser;
import com.plg.shiro.entity.Vo.OmExamSubmitVo;
import com.plg.shiro.util.dwz.LayuiPage;

public interface IExamPlanService {
    int deleteByPrimaryKey(String planId);

    int insert(OmExamPlan record);

    OmExamPlan selectByPrimaryKey(String planId);
    
    List<OmExamPlan> selectAll();

    int updateByPrimaryKeySelective(OmExamPlan record);
    
    List<OmExamPlan> findPageList(HttpServletRequest request, LayuiPage page);

	void deleteBatch(String ids);
	
	List<OmExamPlanVo> findVoPageList(HttpServletRequest request, LayuiPage page);
	
	List<OmExamPlanVo> selectVoAll();

	List<OmExamPlanVo> findUserPageList(HttpServletRequest request, LayuiPage page, OmUser omUser, String examUserType);
	
	List<OmExamPlanVo> findUserList(HttpServletRequest request, OmUser omUser, String examUserType);
	
	List<OmExamPlan> selectByStatus(String status);
	
	List<OmExamPlanVo> selectVoByStatus(String status);
	
	OmExamPlanVo selectVoByPrimaryKey(String planId);
	
}
