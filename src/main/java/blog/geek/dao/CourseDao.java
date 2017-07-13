package blog.geek.dao;

import blog.geek.entity.Course;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 课程Dao
 * @author yuanyang
 * @version 1.0
 */
@Repository
public interface CourseDao {

    /**
     * 增
     * @param course
     * @return
     */
    int insertCourse(Course course);

    /**
     * 删
     * @param courseId
     * @return
     */
    int deleteCourse(String courseId);

    /**
     * 批量删
     * @param courseIds
     * @return
     */
    int deleteCourses(@Param("courseIds") List<String> courseIds);

    /**
     * 改
     * @param course
     * @return
     */
    int updateCourse(Course course);

    /**
     * 查找所有
     * @param offSet
     * @param limit
     * @return
     */
    List<Course> findAllCourses(int offSet,int limit);

    /**
     * 按关键字查询
     * @param key
     * @return
     */
    List<Course> findCoursesByKeyWord(String key);

    /**
     * 获取总数目
     * @return
     */
    int getTotal();

    List<Course> findCourseByTime(String date);
}
