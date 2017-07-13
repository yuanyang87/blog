package blog.geek.manager.service;

import blog.geek.dao.ScheduleDao;
import blog.geek.entity.Schedule;
import blog.geek.exception.ErrorException;
import blog.geek.utils.RandomStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 管理员管理课表服务类
 * @author yuanyang
 * @version 1.0
 */
@Service
public class ScheduleService {

    @Autowired
    private ScheduleDao scheduleDao;

    /**
     * 批量插入课程表
     * @param schedules
     */
    public void insertSchedules(List<Schedule> schedules){
        int size = schedules.size();
        if (scheduleDao.insertSchedules(schedules) != size)
            throw new ErrorException("课程表添加操作失败");
    }

    /**
     * 插入单个课表
     * @param schedule
     */
    public void insertSchedule(Schedule schedule){
        schedule.setScheduleId(RandomStringUtil.repeatableString(8));
        if (scheduleDao.insertSchedule(schedule) != 1)
            throw new ErrorException("课程表添加操作失败");
    }

    /**
     * 批量删除课程表
     * @param scheduleIds
     */
    public void deleteSchedules(List<String> scheduleIds){
        int size = scheduleIds.size();
        if (scheduleDao.deleteSchedules(scheduleIds) != size)
            throw new ErrorException("课程表删除操作失败");
    }

    /**
     * 删除单个课表
     * @param scheduleId
     */
    public void deleteSchedule(String scheduleId){
        if (scheduleDao.deleteSchedule(scheduleId) != 1)
            throw new ErrorException("课程表删除操作失败");
    }

    /**
     * 批量更新
     * @param schedules
     */
    public void updateSchedules(List<Schedule> schedules){
        //int size = schedules.size(); 由于执行的是多条sql,每条sql都返回的1,所以最后返回1
        //if (scheduleDao.updateSchedules(schedules) != size)
        if (scheduleDao.updateSchedules(schedules) != 1)
            throw new ErrorException("课程表更新操作失败");
    }

    /**
     * 更新单个课表
     * @param schedule
     */
    public void updateSchedule(Schedule schedule){
        if(scheduleDao.updateSchedule(schedule) != 1)
            throw new ErrorException("课程表更新操作失败");
    }

    /**
     * 查找所有的课程表
     * @return
     */
    public List<Schedule> findAllSchedules(){
        List<Schedule> schedules = scheduleDao.findAllSchedules();
        return schedules;
    }

}
