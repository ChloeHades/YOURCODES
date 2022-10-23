package com.example.myspringboot.modulers.score.service;



import com.baomidou.mybatisplus.extension.service.IService;
import com.example.myspringboot.modulers.score.entity.ScoreEntity;
import com.example.utils.PageUtils;

import java.util.Map;

/**
 *
 *
 * @author ""
 * @email ""
 * @date 2022-10-04 10:33:41
 */
public interface ScoreService extends IService<ScoreEntity> {

    PageUtils queryPage(Map<String, Object> params);
    double getAvg(Long problemid);
}

