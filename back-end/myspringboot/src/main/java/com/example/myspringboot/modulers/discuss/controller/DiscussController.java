package com.example.myspringboot.modulers.discuss.controller;

import java.util.*;


import com.example.myspringboot.modulers.discuss.entity.DiscussEntity;
import com.example.myspringboot.modulers.discuss.service.DiscussService;
import com.example.myspringboot.modulers.problams.entity.ProblemEntity;
import com.example.myspringboot.modulers.problams.service.ProblemService;
import com.example.utils.PageUtils;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 *
 *
 * @author ""
 * @email ""
 * @date 2022-10-03 20:46:44
 */
@CrossOrigin
@RestController
@RequestMapping("discuss/discuss")
public class DiscussController {
    @Autowired
    private DiscussService discussService;
    @Autowired
    private ProblemService problemService;
    /**
     * list
     */
    @RequestMapping("/list")
    public R list(@RequestParam(value = "page") String page, @RequestParam(value = "limit") String limit, @RequestParam(value = "problemids") String problemid){
        Map<String,Object> param=new HashMap<>();
        param.put("page",page);
        param.put("limit",limit);
        param.put("problemid",problemid);
        PageUtils pages = discussService.queryPage(param);

        return R.ok().put("page", pages);
    }


    /**
     * message
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		DiscussEntity discuss = discussService.getById(id);

        return R.ok().put("discuss", discuss);
    }

    /**
     * save
     */
    @RequestMapping("/save")
    public R save(@RequestBody DiscussEntity discuss){
        if(discuss.getDiscussdeuser().equals("")){
            return R.error("please log in");
        }
		Date date =new Date();
		discuss.setTime(date);
		ProblemEntity problemEntity=problemService.getById(discuss.getProblemid());
		problemEntity.setSubmissions(problemEntity.getSubmissions()+1);
		problemService.updateById(problemEntity);
        discussService.save(discuss);

        return R.ok();
    }

    /**
     * modification
     */
    @RequestMapping("/update")

    public R update(@RequestBody DiscussEntity discuss){
		discussService.updateById(discuss);

        return R.ok();
    }

    /**
     * delete
     */
    @RequestMapping("/delete")

    public R delete(@RequestBody Integer[] ids){
		discussService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }
}
