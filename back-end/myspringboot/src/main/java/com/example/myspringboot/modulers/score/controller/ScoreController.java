package com.example.myspringboot.modulers.score.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.myspringboot.modulers.problams.entity.ProblemEntity;
import com.example.myspringboot.modulers.problams.service.ProblemService;
import com.example.myspringboot.modulers.score.entity.ScoreEntity;
import com.example.myspringboot.modulers.score.service.ScoreService;
import com.example.utils.PageUtils;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 *
 *
 * @author ""
 * @email ""
 * @date 2022-10-04 10:33:41
 */
@CrossOrigin
@RestController
@RequestMapping("score/score")
public class ScoreController {
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private ProblemService problemService;
    /**
     * list
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = scoreService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * message
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		ScoreEntity score = scoreService.getById(id);

        return R.ok().put("score", score);
    }

    /**
     * save
     */
    @RequestMapping("/save")
    public R save(@RequestBody ScoreEntity score){
        if(score.getUser().equals("")){
            return R.error("please log in");
        }
		ScoreEntity scoreEntity =scoreService.getOne(new QueryWrapper<ScoreEntity>().eq("user",score.getUser()).eq("problemid",score.getProblemid()));
		if(scoreEntity!=null){
           scoreEntity.setScore(score.getScore());
            scoreService.updateById(scoreEntity);
           double tem=scoreService.getAvg(score.getProblemid());
            ProblemEntity problemEntity = problemService.getById(score.getProblemid());
            problemEntity.setRating(tem);
            problemService.updateById(problemEntity);
           return R.ok("modify successfully");
        }else{
           scoreService.save(score);
            double tem=scoreService.getAvg(score.getProblemid());
            ProblemEntity problemEntity = problemService.getById(score.getProblemid());
            problemEntity.setRating(tem);
            problemService.updateById(problemEntity);
		}

        return R.ok("operate successfully");
    }

    /**
     * modification
     */
    @RequestMapping("/update")
    public R update(@RequestBody ScoreEntity score){
		scoreService.updateById(score);

        return R.ok();
    }

    /**
     * delete
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		scoreService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }
}
