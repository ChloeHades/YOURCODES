package com.example.myspringboot.modulers.problams.controller;


import java.util.*;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.myspringboot.modulers.login.entity.UserEntity;
import com.example.myspringboot.modulers.prloblemcode.service.ProblemcodeService;
import com.example.myspringboot.modulers.problams.entity.ProblemEntity;
import com.example.myspringboot.modulers.problams.entity.Sorts;
import com.example.myspringboot.modulers.problams.service.ProblemService;
import com.example.myspringboot.modulers.problemdetaile.entity.ProblemdetaileEntity;
import com.example.myspringboot.modulers.problemdetaile.entity.Problemvo;
import com.example.myspringboot.modulers.problemdetaile.service.ProblemdetaileService;
import com.example.utils.PageUtils;
import com.example.utils.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


/**
 *
 *
 * @author ""
 * @email ""
 * @date 2022-10-02 19:24:31
 */
@CrossOrigin
@RestController
@RequestMapping("problams/problem")
public class ProblemController {
    @Autowired
    private ProblemService problemService;
    @Autowired
    private ProblemdetaileService problemdetaileService;
    @Autowired
    private ProblemcodeService problemcodeService;
    /**
     * list
     */
    @RequestMapping("/list")
    public R list( @RequestParam(value = "page") String page, @RequestParam(value = "limit") String limit, @RequestParam(value = "key") String key){
        Map<String,Object> param=new HashMap<>();
        param.put("page",page);
        param.put("limit",limit);
        PageUtils pages = problemService.queryPage(param);

        return R.ok().put("page", pages);
    }
    /**
     * condition query
     */
    @RequestMapping("/listlike")
    public R listlike( @RequestParam(value = "page") String page, @RequestParam(value = "limit") String limit, @RequestParam(value = "key") String key,@RequestParam(value = "difficulties") String difficulty,@RequestParam(value = "type") String type,@RequestParam(value = "topics") String topics){

       String [ ] types =type.split(";");
        List<ProblemEntity> problemEntities;
       // Let's find out everything
        if(topics.equals("AllTopics")){
             problemEntities=problemService.list(new QueryWrapper<ProblemEntity>().eq("difficulty",difficulty));
        }else{
            problemEntities=problemService.list(new QueryWrapper<ProblemEntity>().eq("difficulty",difficulty).eq("topics",topics));
        }
       // after filtering
        List<ProblemEntity> problemEntities1=new ArrayList<>();
      if(types.length>1) {
          for (int i = 0; i < problemEntities.size(); i++) {
              String s = problemEntities.get(i).getType();
              String[] ss = s.split(";");
              // put the types of question in one set
              Set<String> set = new HashSet<>();
              for (int m = 0; m < ss.length; m++) {
                  set.add(ss[m]);
              }
              // If it's not in the collection, it's a mismatch
              for (int j = 0; j < types.length; j++) {
                  if (!set.contains(types[j])) {
                      break;
                  }
                  if (j == types.length - 1) {
                      problemEntities1.add(problemEntities.get(i));
                  }
              }

          }
      }else{
          problemEntities1=problemEntities;
      }
        int totalCount =problemEntities1.size();
        int pageSize = problemEntities1.size();
        int totalPage = 1;
        int currPage = 1;

        PageUtils pages = new PageUtils(problemEntities1,totalCount, pageSize,  currPage);

        return R.ok().put("page", pages);
    }

    /**
     * message
     */
    @RequestMapping("/info/{id}")

    public R info(@PathVariable("id") Integer id){
		ProblemEntity problem = problemService.getById(id);

        return R.ok().put("problem", problem);
    }

    /**
     * save
     */
    @RequestMapping("/save")
    public R save(@RequestBody Problemvo problem){
        // Create a problem
        if(problem.getUser().equals("")){
            return R.error("please log in");
        }
        ProblemEntity problemEntity =new ProblemEntity();
       problemEntity.setTitle(problem.getTitle());
        problemEntity.setUser(problem.getUser());
       problemEntity.setDifficulty(problem.getDifficulty());
       problemEntity.setTopics(problem.getTopic());
       problemEntity.setType(problem.getType());
       problemEntity.setRating(0.0);
       problemEntity.setSubmissions(0);
       Long id=System.currentTimeMillis();
        problemEntity.setId(id);
        problemService.save(problemEntity);
       // Creating Problem Details
        ProblemdetaileEntity problemdetaileEntity=new ProblemdetaileEntity();
        problemdetaileEntity.setProblemid(id);
        problemdetaileEntity.setInput1(problem.getInput1());
        problemdetaileEntity.setInput2(problem.getInput2());
        problemdetaileEntity.setOutput1(problem.getOutput1());
        problemdetaileEntity.setOutput2(problem.getOutput2());
        problemdetaileEntity.setCode(problem.getCode());
        problemdetaileEntity.setDescription(problem.getDescription());
        problemdetaileService.save(problemdetaileEntity);
        return R.ok();
    }

    /**
     * modification
     */
    @RequestMapping("/update")
    public R update(@RequestBody ProblemEntity problem){
		problemService.updateById(problem);

        return R.ok();
    }

    /**
     * delete
     */
    @RequestMapping("/delete")
    public R delete(@RequestParam(value = "ids")  Long ids){
		problemService.removeById(ids);

        return R.ok();
    }
    /**
     * list
     */
    @RequestMapping("/sortlist")
    public R sort(){
        List<ProblemEntity> problemEntities=problemService.list(new QueryWrapper<ProblemEntity>().orderByDesc("rating").last("limit 3"));
        List<ProblemEntity> problemEntities2=problemService.list(new QueryWrapper<ProblemEntity>().orderByDesc("submissions").last("limit 3"));
        List<Sorts> problemsorts=problemcodeService.getSorts();
        List<List<Sorts>> res=new ArrayList<List<Sorts>>() ;
        List<Sorts> sorts1 =new ArrayList<>();
        for(int i=0;i<problemEntities.size();i++){
              Sorts sorts =new Sorts();
              sorts.setProfile(problemEntities.get(i).getUser());
              sorts.setRatings(problemEntities.get(i).getRating());
              sorts.setTitle(problemEntities.get(i).getTitle());
             sorts1.add(sorts) ;
       }
        res.add(sorts1);
        List<Sorts> sorts2 =new ArrayList<>();
        for(int i=0;i<problemEntities2.size();i++){
            Sorts sorts =new Sorts();
            sorts.setProfile(problemEntities2.get(i).getUser());
            sorts.setRatings(problemEntities2.get(i).getSubmissions());
            sorts.setTitle(problemEntities2.get(i).getTitle());
            sorts2.add(sorts) ;
        }
         res.add(sorts2);
        List<Sorts> sorts3 =new ArrayList<>();
        for(int i=0;i<problemsorts.size();i++){
            sorts3.add(problemsorts.get(i)) ;
        }
        res.add(sorts3);
        return R.ok().put("res", res);
    }
}
