package com.plg.shiro.service.impl;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.plg.shiro.dao.OmCourseMapper;
import com.plg.shiro.entity.OmCourse;
import com.plg.shiro.entity.OmCourseExample;
import com.plg.shiro.service.ICourseService;
import com.plg.shiro.util.dwz.LayuiPage;

@Service
public class CourseService implements ICourseService {
	private static final Logger logger = LoggerFactory.getLogger(CourseService.class);

	@Resource
	private OmCourseMapper omCourseMapper;
	
	@Override
	public int deleteByPrimaryKey(String courseId) {
		logger.info("删除科目：{}", courseId);
		return omCourseMapper.deleteByPrimaryKey(courseId);
	}

	@Override
	public int insert(OmCourse record) {
		logger.info("新增科目：{}", record.getCourseId());
		return omCourseMapper.insert(record);
	}

	@Override
	public OmCourse selectByPrimaryKey(String courseId) {
		logger.info("查询科目：{}", courseId);
		return omCourseMapper.selectByPrimaryKey(courseId);
	}
	
	
	@Override
	public List<OmCourse> selectAll() {
		logger.info("查询所有科目");
		OmCourseExample example = new OmCourseExample();
		OmCourseExample.Criteria criteria = example.createCriteria();
		example.setOrderByClause("COURSE_NAME");
		return omCourseMapper.selectByExample(example);
	}
	
	@Override
	public int updateByPrimaryKeySelective(OmCourse record) {
		logger.info("更新科目：{}", record.getCourseId());
		return omCourseMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<OmCourse> findPageList(HttpServletRequest request, LayuiPage page) {
		OmCourseExample example = new OmCourseExample();
		OmCourseExample.Criteria criteria = example.createCriteria();
		String courseName = request.getParameter("courseName");
		if(StringUtils.isNoneBlank(courseName)){
			criteria.andCourseNameLike("%"+courseName+"%");
		}
		example.setLimitPageSize(page.getLimit());
		example.setLimitStart(page.limitStart());
		page.setTotalCount(omCourseMapper.countByExample(example));
		example.setOrderByClause("COURSE_NAME");
		return omCourseMapper.selectByExample(example);
	}

	@Override
	public void deleteBatch(String ids) {
		OmCourseExample example = new OmCourseExample();
		OmCourseExample.Criteria c = example.createCriteria();
        c.andCourseIdIn(Arrays.asList(ids.split(",")));
        omCourseMapper.deleteByExample(example);
	}

	@Override
	public OmCourse selectByCourseName(String courseName) {
		OmCourseExample example = new OmCourseExample();
		OmCourseExample.Criteria criteria = example.createCriteria();
		criteria.andCourseNameEqualTo(courseName);
		List<OmCourse> list = omCourseMapper.selectByExample(example);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
}
