package com.plg.shiro.dao;

import com.plg.shiro.entity.OmPaper;
import com.plg.shiro.entity.OmPaperExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmPaperMapper {
    long countByExample(OmPaperExample example);

    int deleteByExample(OmPaperExample example);

    int deleteByPrimaryKey(String paperId);

    int insert(OmPaper record);

    int insertSelective(OmPaper record);

    List<OmPaper> selectByExample(OmPaperExample example);

    OmPaper selectByPrimaryKey(String paperId);

    int updateByExampleSelective(@Param("record") OmPaper record, @Param("example") OmPaperExample example);

    int updateByExample(@Param("record") OmPaper record, @Param("example") OmPaperExample example);

    int updateByPrimaryKeySelective(OmPaper record);

    int updateByPrimaryKey(OmPaper record);
}