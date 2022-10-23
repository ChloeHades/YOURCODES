package com.example.myspringboot.modulers.login.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.myspringboot.modulers.login.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 *
 *
 * @author ""
 * @email ""
 * @date 2022-09-09 16:00:25
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {

}
