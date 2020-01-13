package com.plg.shiro.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.plg.shiro.entity.OmCourse;
import com.plg.shiro.util.dwz.LayuiPage;

public interface ICourseService {
    int deleteByPrimaryKey(String courseId);

    int insert(OmCourse record);

    OmCourse selectByPrimaryKey(String courseId);
    
    List<OmCourse> selectAll();

    int updateByPrimaryKeySelective(OmCourse record);
    
    List<OmCourse> findPageList(HttpServletRequest request, LayuiPage page);

	void deleteBatch(String ids);

	OmCourse selectByCourseName(String courseName);
	
}
