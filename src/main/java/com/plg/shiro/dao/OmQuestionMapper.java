package com.plg.shiro.dao;

import com.plg.shiro.entity.OmQuestion;
import com.plg.shiro.entity.OmQuestionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmQuestionMapper {
    long countByExample(OmQuestionExample example);

    int deleteByExample(OmQuestionExample example);

    int deleteByPrimaryKey(String questionId);

    int insert(OmQuestion record);

    int insertSelective(OmQuestion record);

    List<OmQuestion> selectByExample(OmQuestionExample example);

    OmQuestion selectByPrimaryKey(String questionId);

    int updateByExampleSelective(@Param("record") OmQuestion record, @Param("example") OmQuestionExample example);

    int updateByExample(@Param("record") OmQuestion record, @Param("example") OmQuestionExample example);

    int updateByPrimaryKeySelective(OmQuestion record);

    int updateByPrimaryKey(OmQuestion record);
}