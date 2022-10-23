package com.example.myspringboot.modulers.problemdetaile.controller;

import java.util.Arrays;
import java.util.Map;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.myspringboot.modulers.prloblemcode.entity.ProblemcodeEntity;
import com.example.myspringboot.modulers.prloblemcode.service.ProblemcodeService;
import com.example.myspringboot.modulers.problams.entity.ProblemEntity;
import com.example.myspringboot.modulers.problams.service.ProblemService;
import com.example.myspringboot.modulers.problemdetaile.entity.ProblemdetaileEntity;
import com.example.myspringboot.modulers.problemdetaile.entity.Problemvo;
import com.example.myspringboot.modulers.problemdetaile.service.ProblemdetaileService;
import com.example.utils.PageUtils;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 *
 *
 * @author ""
 * @email ""
 * @date 2022-10-03 15:42:06
 */
@CrossOrigin
@RestController
@RequestMapping("problemdetaile/problemdetaile")
public class ProblemdetaileController {
    @Autowired
    private ProblemdetaileService problemdetaileService;
    @Autowired
    private ProblemService problemService;
    @Autowired
    private ProblemcodeService problemcodeService;
    /**
     * list
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = problemdetaileService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * message
     */
    @RequestMapping("/info/{id}")

    public R info(@PathVariable("id") Integer id){
		ProblemdetaileEntity problemdetaile = problemdetaileService.getById(id);

        return R.ok().put("problemdetaile", problemdetaile);
    }

    /**
     * save
     */
    @RequestMapping("/save")
    public R save(@RequestBody ProblemdetaileEntity problemdetaile){
		problemdetaileService.save(problemdetaile);

        return R.ok();
    }
    /**
     * modification
     */
    @RequestMapping("/update")
    public R update(@RequestBody ProblemdetaileEntity problemdetaile){
		problemdetaileService.updateById(problemdetaile);

        return R.ok();
    }

    /**
     * delete
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		problemdetaileService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }
    /**
     * list
     */
    @RequestMapping("/getdetail")
    public R getdetail(@RequestParam(value = "problemid") String problemid){
       ProblemEntity problemEntity= problemService.getById(problemid);
       ProblemdetaileEntity problemdetaileEntity =problemdetaileService.getOne(new QueryWrapper<ProblemdetaileEntity>().eq("problemid",problemid));
        Problemvo problemvo =new Problemvo();
        problemvo.setComment(problemEntity.getSubmissions());
        problemvo.setDescription(problemdetaileEntity.getDescription());
        problemvo.setDifficulty(problemEntity.getDifficulty());
        problemvo.setInput1(problemdetaileEntity.getInput1());
        problemvo.setInput2(problemdetaileEntity.getInput2());
        problemvo.setOutput1(problemdetaileEntity.getOutput1());
        problemvo.setOutput2(problemdetaileEntity.getOutput2());
        problemvo.setTitle(problemEntity.getTitle());
        problemvo.setTopic(problemEntity.getTopics());
        problemvo.setType(problemEntity.getType());
        problemvo.setId(problemEntity.getId());
        return R.ok().put("problemvo", problemvo);
    }
    /**
     * Creator code
     */
    @RequestMapping("/creatorcode")
    public R creatorcode(@RequestParam(value = "problemid") String problemid,@RequestParam(value = "user") String user){
        ProblemcodeEntity problemcodeEntity =problemcodeService.getOne(new QueryWrapper<ProblemcodeEntity>().eq("username",user).eq("problem",problemid));
        if(problemcodeEntity==null){
            return R.error("Please do the questions first");
        }else{
        ProblemdetaileEntity problemdetaileEntity =problemdetaileService.getOne(new QueryWrapper<ProblemdetaileEntity>().eq("problemid",problemid));
        return R.ok().put("creatorcode", problemdetaileEntity.getCode());
        }
    }
}
