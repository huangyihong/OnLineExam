package com.plg.shiro.dao;

import com.plg.shiro.entity.OmQuestionImg;
import com.plg.shiro.entity.OmQuestionImgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmQuestionImgMapper {
    long countByExample(OmQuestionImgExample example);

    int deleteByExample(OmQuestionImgExample example);

    int deleteByPrimaryKey(String id);

    int insert(OmQuestionImg record);

    int insertSelective(OmQuestionImg record);

    List<OmQuestionImg> selectByExample(OmQuestionImgExample example);

    OmQuestionImg selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") OmQuestionImg record, @Param("example") OmQuestionImgExample example);

    int updateByExample(@Param("record") OmQuestionImg record, @Param("example") OmQuestionImgExample example);

    int updateByPrimaryKeySelective(OmQuestionImg record);

    int updateByPrimaryKey(OmQuestionImg record);
}