package com.plg.shiro.dao;

import com.plg.shiro.entity.OmPaperQuestion;
import com.plg.shiro.entity.OmPaperQuestionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmPaperQuestionMapper {
    long countByExample(OmPaperQuestionExample example);

    int deleteByExample(OmPaperQuestionExample example);

    int deleteByPrimaryKey(String paperQuestionId);

    int insert(OmPaperQuestion record);

    int insertSelective(OmPaperQuestion record);

    List<OmPaperQuestion> selectByExample(OmPaperQuestionExample example);

    OmPaperQuestion selectByPrimaryKey(String paperQuestionId);

    int updateByExampleSelective(@Param("record") OmPaperQuestion record, @Param("example") OmPaperQuestionExample example);

    int updateByExample(@Param("record") OmPaperQuestion record, @Param("example") OmPaperQuestionExample example);

    int updateByPrimaryKeySelective(OmPaperQuestion record);

    int updateByPrimaryKey(OmPaperQuestion record);
}