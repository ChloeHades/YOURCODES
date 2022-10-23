package com.example.myspringboot.modulers.prloblemcode.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.myspringboot.modulers.prloblemcode.entity.ProblemcodeEntity;
import com.example.myspringboot.modulers.problams.entity.Sorts;
import com.example.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author ""
 * @email ""
 * @date 2022-10-06 15:32:08
 */
public interface ProblemcodeService extends IService<ProblemcodeEntity> {

    PageUtils queryPage(Map<String, Object> params);
    List<Sorts> getSorts();
}

