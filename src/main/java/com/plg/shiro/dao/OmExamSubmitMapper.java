package com.plg.shiro.dao;

import com.plg.shiro.entity.OmExamSubmit;
import com.plg.shiro.entity.OmExamSubmitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmExamSubmitMapper {
    long countByExample(OmExamSubmitExample example);

    int deleteByExample(OmExamSubmitExample example);

    int deleteByPrimaryKey(String submitId);

    int insert(OmExamSubmit record);

    int insertSelective(OmExamSubmit record);

    List<OmExamSubmit> selectByExample(OmExamSubmitExample example);

    OmExamSubmit selectByPrimaryKey(String submitId);

    int updateByExampleSelective(@Param("record") OmExamSubmit record, @Param("example") OmExamSubmitExample example);

    int updateByExample(@Param("record") OmExamSubmit record, @Param("example") OmExamSubmitExample example);

    int updateByPrimaryKeySelective(OmExamSubmit record);

    int updateByPrimaryKey(OmExamSubmit record);
}