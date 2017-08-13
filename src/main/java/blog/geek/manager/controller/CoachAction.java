package blog.geek.manager.controller;

import blog.geek.entity.Coach;
import blog.geek.entity.Pager;
import blog.geek.manager.service.CoachService;
import blog.geek.utils.Result;
import blog.geek.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class CoachAction {

    @Autowired
    private CoachService coachService;

    @RequestMapping(value = "/management/insertCoach",method = RequestMethod.POST)
    public Result insertCoach(Coach coach, MultipartFile picture){
        coachService.insertCoach(coach,picture);
        return ResultUtil.successResult(null);
    }

    @RequestMapping(value = "/management/deleteCoach",method = RequestMethod.GET)
    public Result deleteCoach(String coachId){
        coachService.deleteCoach(coachId);
        return ResultUtil.successResult(null);
    }

    @RequestMapping(value = "/management/updateCoach",method = RequestMethod.POST)
    public Result updateCoach(Coach coach,MultipartFile picture){
        coachService.updateCoach(coach,picture);
        return ResultUtil.successResult(null);
    }

    @RequestMapping(value = "/selectCoach",method = RequestMethod.GET)
    public Result selectCoach(int pageIndex,int pageSize){
        Pager<Coach> coachPager = coachService.selectAllCoaches(pageIndex,pageSize);
        return ResultUtil.successResult(coachPager);
    }

}
