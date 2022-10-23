package com.example.myspringboot.modulers.score.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myspringboot.modulers.score.dao.ScoreDao;
import com.example.myspringboot.modulers.score.entity.ScoreEntity;
import com.example.myspringboot.modulers.score.service.ScoreService;
import com.example.utils.PageUtils;
import com.example.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;


@Service("scoreService")
public class ScoreServiceImpl extends ServiceImpl<ScoreDao, ScoreEntity> implements ScoreService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ScoreEntity> page = this.page(
                new Query<ScoreEntity>().getPage(params),
                new QueryWrapper<ScoreEntity>()
        );

        return new PageUtils(page);
    }

    @Override
   public double getAvg(Long problemid){
        return this.baseMapper.getAvg(problemid);
    }
}
