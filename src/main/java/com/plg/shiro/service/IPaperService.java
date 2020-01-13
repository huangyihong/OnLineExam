package com.plg.shiro.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.plg.shiro.entity.OmPaper;
import com.plg.shiro.entity.OmPaperDetail;
import com.plg.shiro.entity.OmQuestion;
import com.plg.shiro.util.dwz.LayuiPage;

public interface IPaperService {
    int deleteByPrimaryKey(String paperId);

    int insert(OmPaper record);

    OmPaper selectByPrimaryKey(String paperId);
    
    List<OmPaper> selectAll();

    int updateByPrimaryKeySelective(OmPaper record);
    
    List<OmPaper> findPageList(HttpServletRequest request, LayuiPage page);

	void deleteBatch(String ids);

	void insert(OmPaper bean, String questionIds);

	void updateByPrimaryKeySelective(OmPaper bean, String questionIds);

	int insertAuto(OmPaper bean);

	void updateAuto(OmPaper bean);

	void rebuild(OmPaper bean);

	List<OmPaperDetail> findPaperDeatilByPaperId(String paperId);

	void insertNewAuto(OmPaper bean, List<OmPaperDetail> detailDTOList);

	void updateNewAuto(OmPaper bean, List<OmPaperDetail> detailDTOList);

	List<OmQuestion> insertPaperQuestionAuto(String paperId, String answerUserId);
	
}
