package com.plg.shiro.dao;

import com.plg.shiro.entity.OmPaperDetail;
import com.plg.shiro.entity.OmPaperDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmPaperDetailMapper {
    long countByExample(OmPaperDetailExample example);

    int deleteByExample(OmPaperDetailExample example);

    int deleteByPrimaryKey(String id);

    int insert(OmPaperDetail record);

    int insertSelective(OmPaperDetail record);

    List<OmPaperDetail> selectByExample(OmPaperDetailExample example);

    OmPaperDetail selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") OmPaperDetail record, @Param("example") OmPaperDetailExample example);

    int updateByExample(@Param("record") OmPaperDetail record, @Param("example") OmPaperDetailExample example);

    int updateByPrimaryKeySelective(OmPaperDetail record);

    int updateByPrimaryKey(OmPaperDetail record);
}