package com.example.myspringboot.modulers.login.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.myspringboot.modulers.login.entity.UserEntity;



import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author ""
 * @email ""
 * @date 2022-09-09 16:00:25
 */
public interface UserService extends IService<UserEntity> {

    List<UserEntity> queryPage();
}

