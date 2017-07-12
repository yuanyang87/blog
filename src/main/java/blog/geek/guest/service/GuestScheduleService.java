package blog.geek.guest.service;

import blog.geek.dao.ScheduleDao;
import blog.geek.entity.Schedule;
import blog.geek.exception.ErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 客户端课程表服务类
 * @author yuanyang
 * @version 1.0
 */
@Service
public class GuestScheduleService {

    @Autowired
    private ScheduleDao scheduleDao;

    /**
     * 获取整张课程表
     * @return
     */
    public List<Schedule> findAllSchedules() {
        List<Schedule> schedules = scheduleDao.findAllSchedules();
        if (schedules == null)
            throw new ErrorException("没有找到你要的数据");
        return schedules;
    }

}
