package com.example.myspringboot.modulers.problams.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.myspringboot.modulers.problams.entity.ProblemEntity;
import com.example.myspringboot.modulers.problams.entity.Sorts;
import com.example.utils.PageUtils;


import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author ""
 * @email ""
 * @date 2022-10-02 19:24:31
 */
public interface ProblemService extends IService<ProblemEntity> {

    PageUtils queryPage(Map<String, Object> params);

}

