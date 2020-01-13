package com.plg.shiro.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.plg.shiro.entity.Vo.OmExamSubmitVo;
import com.plg.shiro.entity.Vo.OmExamSubmitVoExample;

public interface OmExamSubmitVoMapper {
    long countByExample(OmExamSubmitVoExample example);

    List<OmExamSubmitVo> selectByExample(OmExamSubmitVoExample example);
   
}