package com.example.myspringboot.modulers.discuss.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myspringboot.modulers.discuss.dao.DiscussDao;
import com.example.myspringboot.modulers.discuss.entity.DiscussEntity;
import com.example.myspringboot.modulers.discuss.service.DiscussService;
import com.example.utils.PageUtils;
import com.example.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;




@Service("discussService")
public class DiscussServiceImpl extends ServiceImpl<DiscussDao, DiscussEntity> implements DiscussService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DiscussEntity> page = this.page(
                new Query<DiscussEntity>().getPage(params),
                new QueryWrapper<DiscussEntity>().eq("problemid",Long.parseLong((String) params.get("problemid")))
        );

        return new PageUtils(page);
    }

}
