package com.example.myspringboot.modulers.login.controller;

import java.util.*;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.myspringboot.modulers.login.entity.UserEntity;
import com.example.myspringboot.modulers.login.entity.UserVo;
import com.example.myspringboot.modulers.login.service.UserService;
import com.example.myspringboot.modulers.score.entity.ScoreEntity;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


/**
 *
 *
 * @author ""
 * @email ""
 * @date 2022-09-09 16:00:25
 */
@CrossOrigin("http://localhost:8001")
@RestController
@RequestMapping("login/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * log in
     */
    @RequestMapping(value = "/login")
    @ResponseBody
    public HashMap<String, Object> getuser(@RequestParam(value = "name") String name,@RequestParam(value = "password") String password, HttpSession session){
        HashMap<String, Object> res=new HashMap<>();
        UserEntity user=userService.getOne(new QueryWrapper<UserEntity>().eq("username",name).eq("password",password));
        if(user!=null){
            res.put("code",200);
            res.put("list",user);
            return res;
        }else{
            res.put("code",500);
        }
        return res;
    }
    /**
     * list
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public HashMap<String, Object> list(@RequestParam(value = "name") String name){
        List<UserEntity> list=new ArrayList<>();
        HashMap<String, Object> res=new HashMap<>();
        if(name.equals("admin")){
         list=userService.queryPage();
            res.put("code",200);
            res.put("list",list);
            return res;
        } else{
            res.put("code",500);
        }
    return res;
    }

    /**
     * inquire
     */
    @RequestMapping(value = "/list/like")
    @ResponseBody
    public HashMap<String, Object> like(@RequestParam(value = "key") String key){
        List<UserEntity> list=new ArrayList<>();
        HashMap<String, Object> res=new HashMap<>();
        if(!key.equals("")){
            list=userService.list(new QueryWrapper<UserEntity>().like("username",key));
            res.put("code",200);
            res.put("list",list);
            return res;
        } else{
            res.put("code",500);
        }
        return res;
    }

    /**
     * message
     */
    @RequestMapping("/info")
    public HashMap<String, Object> info(@RequestParam(value = "id") String id){
		UserEntity user = userService.getById(id);
        HashMap<String, Object> res=new HashMap<>();
        res.put("user",user);
        return res;
    }

    /**
     * save
     */
    @RequestMapping("/save")

    public HashMap<String, Object> save(@RequestBody UserEntity user){
        HashMap<String, Object> res=new HashMap<>();
        UserEntity user1=userService.getOne(new QueryWrapper<UserEntity>().eq("username",user.getUsername()));
        if(user1!=null){
            res.put("code",500);
            res.put("msg","User already exists");
            return res;
        }
        boolean b= userService.save(user);
        if(b){
            res.put("code",200);
        }else {
            res.put("code",500);
        }
        return res;
    }

    /**
     * modify
     */
    @RequestMapping("/update")
    public HashMap<String, Object> update(@RequestBody UserEntity user){
        boolean b=userService.updateById(user);
        HashMap<String, Object> res=new HashMap<>();
        if(b){
            res.put("code",200);
        }else {
            res.put("code",500);
        }
        return res;
    }

    /**
     * delete
     */
    @RequestMapping("/delete")

    public HashMap<String, Object> delete(@RequestParam(value = "ids")  Integer ids){
        HashMap<String, Object> res=new HashMap<>();
		boolean b=userService.removeById(ids);
        if(b){
		res.put("code",200);
        }
        return res;
    }
    /**
     * modify
     */
    @RequestMapping("/passwordupdate")
    public R passwordupdate(@RequestBody UserVo user){
       UserEntity userEntity = userService.getOne(new QueryWrapper<UserEntity>().eq("username",user.getUsername()));
       if(userEntity!=null){
           if(userEntity.getPassword().equals(user.getPassword())){
               userEntity.setPassword(user.getPasswordnew());
               userService.updateById(userEntity);
               return R.ok();
           }else {
               return R.error("PASSWORD ERROR");
           }
       }else {
           return R.error("The user does not exist");
       }

    }
    /**
     * log in
     */
    @RequestMapping(value = "/getdetail")
    @ResponseBody
    public R getuserdetail(@RequestParam(value = "user") String user){
        UserEntity userdetail=userService.getOne(new QueryWrapper<UserEntity>().eq("username",user));
        if(user!=null){
            return R.ok().put("user",userdetail);
        }else{
            return R.error();
        }
    }

    /**
     * modify
     */
    @RequestMapping("/userupdate")
    public R userupdate(@RequestBody UserEntity user){
        UserEntity userEntity = userService.getOne(new QueryWrapper<UserEntity>().eq("username",user.getUsername()));
        if(userEntity!=null){
          userEntity.setEmail(user.getEmail());
          userEntity.setPhone(user.getPhone());
          userEntity.setSex(user.getSex());
          userEntity.setSelfintroduction(user.getSelfintroduction());
          userService.updateById(userEntity);
          return R.ok();
        }else {
            return R.error("The user does not exist");
        }

    }

}
