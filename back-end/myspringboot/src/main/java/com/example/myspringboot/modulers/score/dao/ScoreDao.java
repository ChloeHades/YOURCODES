package com.example.myspringboot.modulers.score.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.myspringboot.modulers.score.entity.ScoreEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 *
 *
 * @author ""
 * @email ""
 * @date 2022-10-04 10:33:41
 */
@Mapper
public interface ScoreDao extends BaseMapper<ScoreEntity> {
    double  getAvg (Long problemid);
}
