package com.plg.shiro.dao;

import com.plg.shiro.entity.OmUser;
import com.plg.shiro.entity.OmUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmUserMapper {
    long countByExample(OmUserExample example);

    int deleteByExample(OmUserExample example);

    int deleteByPrimaryKey(String userId);

    int insert(OmUser record);

    int insertSelective(OmUser record);

    List<OmUser> selectByExample(OmUserExample example);

    OmUser selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") OmUser record, @Param("example") OmUserExample example);

    int updateByExample(@Param("record") OmUser record, @Param("example") OmUserExample example);

    int updateByPrimaryKeySelective(OmUser record);

    int updateByPrimaryKey(OmUser record);
}