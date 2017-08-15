package blog.geek.manager.controller;

import blog.geek.entity.Pager;
import blog.geek.entity.Wod;
import blog.geek.manager.service.WodService;
import blog.geek.utils.Result;
import blog.geek.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class WodAction {

    @Autowired
    private WodService wodService;

    @RequestMapping(value = "/management/insertWod",method = RequestMethod.POST)
    public Result insertWod(Wod wod, MultipartFile picture){
        wodService.insertWod(wod,picture);
        return ResultUtil.successResult(null);
    }

    @RequestMapping(value = "/management/deleteWod",method = RequestMethod.GET)
    public Result deleteWod(String wodId){
        wodService.deleteWod(wodId);
        return ResultUtil.successResult(null);
    }

    @RequestMapping(value = "/management/updateWod",method = RequestMethod.POST)
    public Result updateWod(Wod wod,MultipartFile picture){
        wodService.updateWod(wod,picture);
        return ResultUtil.successResult(null);
    }

    @RequestMapping(value = "/selectWod",method = RequestMethod.GET)
    public Result selectWod(int pageIndex,int pageSize){
        Pager<Wod> wodPager = wodService.selectAllWod(pageIndex,pageSize);
        return ResultUtil.successResult(wodPager);
    }

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public Result selectWod(int pageIndex,int pageSize,String time){
        Pager<Wod> wodPager = wodService.selectWodByTime(pageIndex,pageSize,time);
        return ResultUtil.successResult(wodPager);
    }

}
