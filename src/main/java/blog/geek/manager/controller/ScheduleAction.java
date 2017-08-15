package blog.geek.manager.controller;

import blog.geek.entity.Schedule;
import blog.geek.manager.service.ScheduleService;
import blog.geek.utils.Result;
import blog.geek.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 管理员管理课程表模块请求处理
 * @author yuanyang
 * @version 1.0
 */
@RestController
public class ScheduleAction {

    @Autowired
    private ScheduleService scheduleService;

    /**
     * 更新单张课表
     * @param schedule
     * @return
     */
    @RequestMapping(value = "/management/updateSchedule",method = RequestMethod.POST)
    public Result updateSchedule(Schedule schedule,MultipartFile picture){
        scheduleService.updateSchedule(schedule,picture);
        return ResultUtil.successResult(null);
    }

    /**
     * 后台查找所有的课程表
     * @return
     */
    @RequestMapping(value = "/findSchedule/{scheduleType}",method = RequestMethod.GET)
    public Result findAllSchedule(@PathVariable String scheduleType){
        Schedule schedule = scheduleService.findScheduleByType(scheduleType);
        return ResultUtil.successResult(schedule);
    }
}
