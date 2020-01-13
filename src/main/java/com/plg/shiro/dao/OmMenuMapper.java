package com.plg.shiro.dao;

import com.plg.shiro.entity.OmMenu;
import com.plg.shiro.entity.OmMenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmMenuMapper {
    long countByExample(OmMenuExample example);

    int deleteByExample(OmMenuExample example);

    int deleteByPrimaryKey(String menuId);

    int insert(OmMenu record);

    int insertSelective(OmMenu record);

    List<OmMenu> selectByExample(OmMenuExample example);

    OmMenu selectByPrimaryKey(String menuId);

    int updateByExampleSelective(@Param("record") OmMenu record, @Param("example") OmMenuExample example);

    int updateByExample(@Param("record") OmMenu record, @Param("example") OmMenuExample example);

    int updateByPrimaryKeySelective(OmMenu record);

    int updateByPrimaryKey(OmMenu record);
}