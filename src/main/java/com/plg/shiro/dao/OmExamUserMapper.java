package com.plg.shiro.dao;

import com.plg.shiro.entity.OmExamUser;
import com.plg.shiro.entity.OmExamUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmExamUserMapper {
    long countByExample(OmExamUserExample example);

    int deleteByExample(OmExamUserExample example);

    int deleteByPrimaryKey(String examUserId);

    int insert(OmExamUser record);

    int insertSelective(OmExamUser record);

    List<OmExamUser> selectByExample(OmExamUserExample example);

    OmExamUser selectByPrimaryKey(String examUserId);

    int updateByExampleSelective(@Param("record") OmExamUser record, @Param("example") OmExamUserExample example);

    int updateByExample(@Param("record") OmExamUser record, @Param("example") OmExamUserExample example);

    int updateByPrimaryKeySelective(OmExamUser record);

    int updateByPrimaryKey(OmExamUser record);
}