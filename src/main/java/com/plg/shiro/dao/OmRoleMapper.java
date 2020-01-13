package com.plg.shiro.dao;

import com.plg.shiro.entity.OmRole;
import com.plg.shiro.entity.OmRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmRoleMapper {
    long countByExample(OmRoleExample example);

    int deleteByExample(OmRoleExample example);

    int deleteByPrimaryKey(String roleId);

    int insert(OmRole record);

    int insertSelective(OmRole record);

    List<OmRole> selectByExample(OmRoleExample example);

    OmRole selectByPrimaryKey(String roleId);

    int updateByExampleSelective(@Param("record") OmRole record, @Param("example") OmRoleExample example);

    int updateByExample(@Param("record") OmRole record, @Param("example") OmRoleExample example);

    int updateByPrimaryKeySelective(OmRole record);

    int updateByPrimaryKey(OmRole record);
}