package com.example.myspringboot.modulers.problemdetaile.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myspringboot.modulers.problemdetaile.dao.ProblemdetaileDao;
import com.example.myspringboot.modulers.problemdetaile.entity.ProblemdetaileEntity;
import com.example.myspringboot.modulers.problemdetaile.service.ProblemdetaileService;
import com.example.utils.PageUtils;
import com.example.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;





@Service("problemdetaileService")
public class ProblemdetaileServiceImpl extends ServiceImpl<ProblemdetaileDao, ProblemdetaileEntity> implements ProblemdetaileService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ProblemdetaileEntity> page = this.page(
                new Query<ProblemdetaileEntity>().getPage(params),
                new QueryWrapper<ProblemdetaileEntity>()
        );

        return new PageUtils(page);
    }

}
