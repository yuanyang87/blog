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
    @RequestMapping(value = "/insertSchedules",method = RequestMethod.POST)
    public Result insertSchedules(String scheduleJson){
        mngScheduleService.insertSchedules(JsonUtil.toList(scheduleJson, new TypeReference<List<Schedule>>(){}));
        return ResultUtil.successResult(null);
    }

    /**
     * 单个课程表插入
     * @param schedule
     * @return
     */
    @RequestMapping(value = "/insertSchedule",method = RequestMethod.POST)
    public Result insertSchedule(Schedule schedule){
        mngScheduleService.insertSchedule(schedule);
        return ResultUtil.successResult(null);
    }

    /**
     * 批量删除,传入需要被删除的课程表Id的json字符串
     * @param scheduleIdJson
     * @return
     */
    @RequestMapping(value = "/deleteSchedules/{scheduleIdJson}",method = RequestMethod.DELETE)
    public Result deleteSchedules(@PathVariable String scheduleIdJson){
        mngScheduleService.deleteSchedules((List<String>) JsonUtil.toPOJO(scheduleIdJson,new ArrayList<String>().getClass()));
        return ResultUtil.successResult(null);
    }

    /**
     * 单个课程表删除
     * @param scheduleId
     * @return
     */
    @RequestMapping("/deleteSchedule/{scheduleId}")
    public Result deleteSchedule(@PathVariable String scheduleId){
        mngScheduleService.deleteSchedule(scheduleId);
        return ResultUtil.successResult(null);
    }

    /**
     * 批量更新,传入需要被更新的课程表Json字符串
     * @param scheduleJson
     * @return
     */
    @RequestMapping(value = "/updateSchedules",method = RequestMethod.POST)
    public Result updateSchedules(String scheduleJson){
        mngScheduleService.updateSchedules(JsonUtil.toList(scheduleJson, new TypeReference<List<Schedule>>(){}));
        return ResultUtil.successResult(null);
    }

    /**
     * 更新单张课表
     * @param schedule
     * @return
     */
    @RequestMapping(value = "/updateSchedule",method = RequestMethod.POST)
    public Result updateSchedule(Schedule schedule){
        mngScheduleService.updateSchedule(schedule);
        return ResultUtil.successResult(null);
    }

    /**
     * 后台查找所有的课程表
     * @return
     */
    @RequestMapping(value = "/findAllSchedule",method = RequestMethod.GET)
    public Result findAllSchedule(){
        List<Schedule> schedules = mngScheduleService.findAllSchedules();
        return ResultUtil.successResult(schedules);
    }
}
