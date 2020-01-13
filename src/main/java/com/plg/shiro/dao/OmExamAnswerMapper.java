package com.plg.shiro.dao;

import com.plg.shiro.entity.OmExamAnswer;
import com.plg.shiro.entity.OmExamAnswerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmExamAnswerMapper {
    long countByExample(OmExamAnswerExample example);

    int deleteByExample(OmExamAnswerExample example);

    int deleteByPrimaryKey(String answerId);

    int insert(OmExamAnswer record);

    int insertSelective(OmExamAnswer record);

    List<OmExamAnswer> selectByExample(OmExamAnswerExample example);

    OmExamAnswer selectByPrimaryKey(String answerId);

    int updateByExampleSelective(@Param("record") OmExamAnswer record, @Param("example") OmExamAnswerExample example);

    int updateByExample(@Param("record") OmExamAnswer record, @Param("example") OmExamAnswerExample example);

    int updateByPrimaryKeySelective(OmExamAnswer record);

    int updateByPrimaryKey(OmExamAnswer record);
}