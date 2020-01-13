package com.plg.shiro.service;

import java.util.List;

import com.plg.shiro.entity.OmExamAnswer;

public interface IExamAnswerService {
    int deleteByPrimaryKey(String answerId);

    int insert(OmExamAnswer record);

    OmExamAnswer selectByPrimaryKey(String answerId);
    
    List<OmExamAnswer> selectAll();

    int updateByPrimaryKeySelective(OmExamAnswer record);
    
	void deleteBatch(String ids);

	void saveOrUpdate(OmExamAnswer bean);
	
	OmExamAnswer selectByUserQuestionID(String userId,String planId,String paperId,String questionId);
	
	List<OmExamAnswer> selectByUserPaperID(String userId,String planId,String paperId);
	
	void updateBatch(List<OmExamAnswer> list);
}
