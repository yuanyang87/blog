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
public interface ScheduleDao {

    /**
     * 批量添加课程表
     * @param schedules
     * @return
     */
    int insertSchedules(@Param("schedules") List<Schedule> schedules);

    /**
     * 插入单个课表
     * @param schedule
     * @return
     */
    int insertSchedule(Schedule schedule);

    /**
     * 批量删除课程表
     * @param scheduleIds
     * @return
     */
    int deleteSchedules(@Param("scheduleIds") List<String> scheduleIds);

    /**
     * 删除单个课表
     * @param scheduleId
     * @return
     */
    int deleteSchedule(String scheduleId);

    /**
     * 批量更新课程表
     * @param schedules
     * @return
     */
    int updateSchedules(@Param("schedules") List<Schedule> schedules);

    /**
     * 更新单个课表
     * @param schedule
     * @return
     */
    int updateSchedule(Schedule schedule);

    /**
     * 获取整张课程表内容
     * @return
     */
    List<Schedule> findAllSchedules();

}
