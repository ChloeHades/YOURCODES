package com.example.myspringboot.modulers.prloblemcode.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.myspringboot.modulers.prloblemcode.entity.ProblemcodeEntity;
import com.example.myspringboot.modulers.problams.entity.Sorts;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *
 *
 * @author ""
 * @email ""
 * @date 2022-10-06 15:32:08
 */
@Mapper
public interface ProblemcodeDao extends BaseMapper<ProblemcodeEntity> {
      List<Sorts> getproblemsort();
}
