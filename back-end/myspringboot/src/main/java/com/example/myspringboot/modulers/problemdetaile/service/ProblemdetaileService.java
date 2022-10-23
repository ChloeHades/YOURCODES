package com.example.myspringboot.modulers.problemdetaile.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.myspringboot.modulers.problemdetaile.entity.ProblemdetaileEntity;
import com.example.utils.PageUtils;


import java.util.Map;

/**
 *
 *
 * @author ""
 * @email ""
 * @date 2022-10-03 15:42:06
 */
public interface ProblemdetaileService extends IService<ProblemdetaileEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

