package com.example.myspringboot.modulers.prloblemcode.controller;


import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.myspringboot.modulers.prloblemcode.entity.ProblemcodeEntity;
import com.example.myspringboot.modulers.prloblemcode.service.ProblemcodeService;
import com.example.myspringboot.modulers.problams.entity.ProblemEntity;
import com.example.myspringboot.modulers.problams.service.ProblemService;
import com.example.utils.ApiRequestDemo;
import com.example.utils.JsonResult;
import com.example.utils.PageUtils;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 *
 *
 * @author ""
 * @email ""
 * @date 2022-10-06 15:32:08
 */
@CrossOrigin
@RestController
@RequestMapping("prloblemcode/problemcode")
public class ProblemcodeController {
    @Autowired
    private ProblemcodeService problemcodeService;
    @Autowired
    private ProblemService problemService;

    @RequestMapping("/list")
    public R list(@RequestParam(value = "page") String page, @RequestParam(value = "limit") String limit, @RequestParam(value = "problemid") String problemid,@RequestParam(value = "user") String user){

        ProblemcodeEntity problemcodeEntity =problemcodeService.getOne(new QueryWrapper<ProblemcodeEntity>().eq("username",user).eq("problem",problemid));
        if(problemcodeEntity==null){
            return R.error();
        }else{
        Map<String,Object> params=new HashMap<>();
        params.put("page",page);
        params.put("limit",limit);
        params.put("problemid",problemid);
        params.put("user",user);
        PageUtils pages = problemcodeService.queryPage(params);
        return R.ok().put("page", pages);}
    }

    @RequestMapping("/getone")
    public R getone( @RequestParam(value = "problemid") String problemid,@RequestParam(value = "user") String user){

        ProblemcodeEntity problemcodeEntity =problemcodeService.getOne(new QueryWrapper<ProblemcodeEntity>().eq("username",user).eq("problem",problemid));
       if(problemcodeEntity==null){
           return R.error();
       }else{
            return R.ok().put("codes", problemcodeEntity.getCode());}
    }

    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		ProblemcodeEntity problemcode = problemcodeService.getById(id);

        return R.ok().put("problemcode", problemcode);
    }


    @RequestMapping("/save")
    public R save(@RequestBody ProblemcodeEntity problemcode){
        ApiRequestDemo apiRequestDemo = new ApiRequestDemo();

     ProblemcodeEntity problemcodeEntity =  problemcodeService.getOne(new QueryWrapper<ProblemcodeEntity>().eq("problem",problemcode.getProblem()).eq("username",problemcode.getUsername()));
        if(problemcodeEntity==null){
       ProblemEntity problemEntity= problemService.getById(problemcode.getProblem());
        problemEntity.setSolution(problemEntity.getSolution()+1);
        problemService.updateById(problemEntity);
        Date date=new Date();
        problemcode.setTime(date);
        problemcodeService.save(problemcode);
        return R.ok();
        }else{
            problemcodeEntity.setCode(problemcode.getCode());
            problemcodeService.updateById(problemcodeEntity);
            return R.ok();
        }
    }


    @RequestMapping("/update")
    public R update(@RequestBody ProblemcodeEntity problemcode){
		problemcodeService.updateById(problemcode);

        return R.ok();
    }


    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		problemcodeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    @RequestMapping("/test")
    public R test(@RequestBody ProblemcodeEntity problemcode) throws IOException {
        JsonResult res = ApiRequestDemo.getcode(problemcode.getCode(),problemcode.getLang());
        ProblemcodeEntity problemcodeEntity =  problemcodeService.getOne(new QueryWrapper<ProblemcodeEntity>().eq("problem",problemcode.getProblem()).eq("username",problemcode.getUsername()));
        return R.ok().put("res",res);
    }
}
