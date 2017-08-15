package blog.geek.dao;

import blog.geek.entity.Image;
import blog.geek.entity.Schedule;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 课程表DAO
 * @author yuanyang
 * @version 1.0
 */
@Repository
public interface ScheduleDao {

    /**
     * 更新单个课表
     * @param schedule
     * @param image
     * @return
     */
    int updateSchedule(@Param("schedule") Schedule schedule, @Param("image") Image image);

    /**
     * 获取课表
     * @param scheduleType
     * @return
     */
    Schedule findScheduleByType(String scheduleType);

}
