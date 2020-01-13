package com.plg.shiro.dao;

import com.plg.shiro.entity.OmUserGroup;
import com.plg.shiro.entity.OmUserGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmUserGroupMapper {
    long countByExample(OmUserGroupExample example);

    int deleteByExample(OmUserGroupExample example);

    int deleteByPrimaryKey(String groupId);

    int insert(OmUserGroup record);

    int insertSelective(OmUserGroup record);

    List<OmUserGroup> selectByExample(OmUserGroupExample example);

    OmUserGroup selectByPrimaryKey(String groupId);

    int updateByExampleSelective(@Param("record") OmUserGroup record, @Param("example") OmUserGroupExample example);

    int updateByExample(@Param("record") OmUserGroup record, @Param("example") OmUserGroupExample example);

    int updateByPrimaryKeySelective(OmUserGroup record);

    int updateByPrimaryKey(OmUserGroup record);
}