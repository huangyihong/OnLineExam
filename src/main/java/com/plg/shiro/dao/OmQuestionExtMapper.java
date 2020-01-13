package com.plg.shiro.dao;

import com.plg.shiro.entity.OmPaperQuestion;
import com.plg.shiro.entity.OmQuestion;
import com.plg.shiro.entity.OmQuestionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmQuestionExtMapper {
    List<OmQuestion> selectByExample(OmQuestionExample example);
    List<OmQuestion> selectPaperQuestion(String paperId);
    List<OmQuestion> selectUserPaperQuestion(OmPaperQuestion paperQuestion);
    
}