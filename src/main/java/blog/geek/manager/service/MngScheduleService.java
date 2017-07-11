package blog.geek.manager.service;

import blog.geek.dao.ScheduleDao;
import blog.geek.entity.Schedule;
import blog.geek.exception.ErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 管理员管理课表服务类
 * @author yuanyang
 * @version 1.0
 */
@Service
public class MngScheduleService {

    @Autowired
    private ScheduleDao scheduleDao;

    public void insertSchedule(List<Schedule> schedules){
        int size = schedules.size();
        if (scheduleDao.insertSchedule(schedules) != size)
            throw new ErrorException("课程表添加操作失败");
    }

    public void deleteSchedule(List<String> scheduleIds){
        int size = scheduleIds.size();
        if (scheduleDao.deleteSchedule(scheduleIds) != size)
            throw new ErrorException("课程表删除操作失败");
    }

    public void updateSchedule(List<Schedule> schedules){
        //int size = schedules.size();
        if (scheduleDao.updateSchedule(schedules) != 1)
            throw new ErrorException("课程表更新操作失败");
    }


    public List<Schedule> findAllSchedule(){
        List<Schedule> schedules = scheduleDao.findAllSchedule();
        return schedules;
    }

}
