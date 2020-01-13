package com.plg.shiro.dao;

import com.plg.shiro.entity.OmExamPlanVo;
import com.plg.shiro.entity.OmExamPlanVoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmExamPlanVoMapper {
    long countByExample(OmExamPlanVoExample example);

    int deleteByExample(OmExamPlanVoExample example);

    int deleteByPrimaryKey(String planId);

    int insert(OmExamPlanVo record);

    int insertSelective(OmExamPlanVo record);

    List<OmExamPlanVo> selectByExample(OmExamPlanVoExample example);

    OmExamPlanVo selectByPrimaryKey(String planId);

    int updateByExampleSelective(@Param("record") OmExamPlanVo record, @Param("example") OmExamPlanVoExample example);

    int updateByExample(@Param("record") OmExamPlanVo record, @Param("example") OmExamPlanVoExample example);

    int updateByPrimaryKeySelective(OmExamPlanVo record);

    int updateByPrimaryKey(OmExamPlanVo record);
}