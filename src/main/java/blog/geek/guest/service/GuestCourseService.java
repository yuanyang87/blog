package blog.geek.guest.service;

import blog.geek.dao.CourseDao;
import blog.geek.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 客户端课程服务类
 * @author yuanyang
 * @version 1.0
 */
@Service
public class GuestCourseService {

    @Autowired
    private CourseDao courseDao;

    public List<Course> findAllCourses(){
        List<Course> courses = courseDao.findAllCourses();
        return courses;
    }

}
