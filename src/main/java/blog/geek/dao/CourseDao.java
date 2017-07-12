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

    int insertCourse(Course course);

    int deleteCourse(String courseId);

    int deleteCourses(@Param("courseIds") List<String> courseIds);

    int updateCourse(Course course);

    List<Course> findAllCourses();

}
