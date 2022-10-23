package com.example.myspringboot.modulers.problams.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myspringboot.modulers.problams.dao.ProblemDao;
import com.example.myspringboot.modulers.problams.entity.ProblemEntity;
import com.example.myspringboot.modulers.problams.entity.Sorts;
import com.example.myspringboot.modulers.problams.service.ProblemService;
import com.example.utils.PageUtils;
import com.example.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;





@Service("problemService")
public class ProblemServiceImpl extends ServiceImpl<ProblemDao, ProblemEntity> implements ProblemService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ProblemEntity> page = this.page(
                new Query<ProblemEntity>().getPage(params),
                new QueryWrapper<ProblemEntity>()
        );

        return new PageUtils(page);
    }

}
