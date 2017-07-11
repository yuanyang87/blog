package blog.geek.dao;

import blog.geek.entity.Schedule;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 课程表DAO
 * @author yuanyang
 * @version 1.0
 */
@Repository
public interface ScheduleDao     {

    /**
     * 添加课程表
     * @param schedules
     * @return
     */
    int insertSchedule(@Param("schedules") List<Schedule> schedules);

    /**
     * 删除课程表
     * @param scheduleIds
     * @return
     */
    int deleteSchedule(@Param("scheduleIds") List<String> scheduleIds);

    /**
     * 更新课程表
     * @param schedules
     * @return
     */
    int updateSchedule(@Param("schedules") List<Schedule> schedules);

    /**
     * 获取整张课程表内容
     * @return
     */
    List<Schedule> findAllSchedule();

}
