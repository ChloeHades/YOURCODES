package com.example.myspringboot.modulers.login.service.impl;


import com.example.myspringboot.modulers.login.dao.UserDao;
import com.example.myspringboot.modulers.login.entity.UserEntity;
import com.example.myspringboot.modulers.login.service.UserService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Override
    public List<UserEntity> queryPage() {

        List<UserEntity> list = this.list();
        return   list;
    }

}
