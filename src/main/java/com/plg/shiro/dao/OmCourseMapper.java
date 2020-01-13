package com.plg.shiro.dao;

import com.plg.shiro.entity.OmCourse;
import com.plg.shiro.entity.OmCourseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmCourseMapper {
    long countByExample(OmCourseExample example);

    int deleteByExample(OmCourseExample example);

    int deleteByPrimaryKey(String courseId);

    int insert(OmCourse record);

    int insertSelective(OmCourse record);

    List<OmCourse> selectByExample(OmCourseExample example);

    OmCourse selectByPrimaryKey(String courseId);

    int updateByExampleSelective(@Param("record") OmCourse record, @Param("example") OmCourseExample example);

    int updateByExample(@Param("record") OmCourse record, @Param("example") OmCourseExample example);

    int updateByPrimaryKeySelective(OmCourse record);

    int updateByPrimaryKey(OmCourse record);
}