package blog.geek.guest.controller;

import blog.geek.entity.Schedule;
import blog.geek.guest.service.GuestScheduleService;
import blog.geek.utils.Result;
import blog.geek.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 客户端获取课表请求处理
 * @author yuanyang
 * @version 1.0
 */
@RestController
public class GuestScheduleAction {

    @Autowired
    private GuestScheduleService guestScheduleService;

    /**
     * 获取整张课程表
     * @return
     */
    @RequestMapping(value = "/findAllSchedule",method = RequestMethod.GET)
    public Result findAllSchedule(){
        List<Schedule> schedules = guestScheduleService.findAllSchedules();
        return ResultUtil.successResult(schedules);
    }

}
