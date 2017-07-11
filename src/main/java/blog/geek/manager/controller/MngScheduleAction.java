package blog.geek.manager.controller;

import blog.geek.entity.Schedule;
import blog.geek.manager.service.MngScheduleService;
import blog.geek.utils.JsonUtil;
import blog.geek.utils.Result;
import blog.geek.utils.ResultUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理员管理课程表模块请求处理
 * @author yuanyang
 * @version 1.0
 */
@RestController
@RequestMapping("/management")
public class MngScheduleAction {

    @Autowired
    private MngScheduleService mngScheduleService;

    /**
     * 传入JSON字符串,解析成List<Schedule>对象进行添加
     * @param scheduleJson
     * @return
     */
    @RequestMapping(value = "/insertSchedule",method = RequestMethod.POST)
    public Result insertSchedule(String scheduleJson){
        mngScheduleService.insertSchedule(JsonUtil.toList(scheduleJson, new TypeReference<List<Schedule>>(){}));
        return ResultUtil.successResult(null);
    }

    @RequestMapping(value = "/deleteSchedule/{scheduleIdJson}",method = RequestMethod.DELETE)
    public Result deleteSchedule(@PathVariable String scheduleIdJson){
        mngScheduleService.deleteSchedule((List<String>) JsonUtil.toPOJO(scheduleIdJson,new ArrayList<String>().getClass()));
        return ResultUtil.successResult(null);
    }

    @RequestMapping(value = "/updateSchedule",method = RequestMethod.POST)
    public Result updateSchedule(String scheduleJson){
        mngScheduleService.updateSchedule(JsonUtil.toList(scheduleJson, new TypeReference<List<Schedule>>(){}));
        return ResultUtil.successResult(null);
    }

    @RequestMapping(value = "/findAllSchedule",method = RequestMethod.GET)
    public Result findAllSchedule(){
        List<Schedule> schedules = mngScheduleService.findAllSchedule();
        return ResultUtil.successResult(schedules);
    }
}
