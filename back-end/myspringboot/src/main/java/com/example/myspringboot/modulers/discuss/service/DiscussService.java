package com.example.myspringboot.modulers.discuss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.myspringboot.modulers.discuss.entity.DiscussEntity;
import com.example.utils.PageUtils;


import java.util.Map;

/**
 *
 *
 * @author ""
 * @email ""
 * @date 2022-10-03 20:46:44
 */
public interface DiscussService extends IService<DiscussEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

