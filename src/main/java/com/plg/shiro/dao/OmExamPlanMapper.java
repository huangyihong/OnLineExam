package com.plg.shiro.dao;

import com.plg.shiro.entity.OmExamPlan;
import com.plg.shiro.entity.OmExamPlanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmExamPlanMapper {
    long countByExample(OmExamPlanExample example);

    int deleteByExample(OmExamPlanExample example);

    int deleteByPrimaryKey(String planId);

    int insert(OmExamPlan record);

    int insertSelective(OmExamPlan record);

    List<OmExamPlan> selectByExample(OmExamPlanExample example);

    OmExamPlan selectByPrimaryKey(String planId);

    int updateByExampleSelective(@Param("record") OmExamPlan record, @Param("example") OmExamPlanExample example);

    int updateByExample(@Param("record") OmExamPlan record, @Param("example") OmExamPlanExample example);

    int updateByPrimaryKeySelective(OmExamPlan record);

    int updateByPrimaryKey(OmExamPlan record);
}