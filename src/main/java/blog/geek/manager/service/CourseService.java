package blog.geek.manager.service;

import blog.geek.dao.CourseDao;
import blog.geek.entity.Course;
import blog.geek.entity.Pager;
import blog.geek.exception.ErrorException;
import blog.geek.utils.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 管理员课程服务类
 * @author yuanyang
 * @version 1.0
 */
@Service
public class CourseService {

    @Autowired
    private CourseDao courseDao;
    @Autowired
    private ImageService imageService;

    /**
     * 插入课程
     * @param course
     */
    public void insertCourse(Course course){
        List<String> notAppear = ListUtil.searchList(course.getCourseContent(),imageService.getImagePath(course.getCourseId()));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        course.setCourseTime(simpleDateFormat.format(new Date()));

        if (courseDao.insertCourse(course) != 1)
            throw new ErrorException("提交失败,请重新操作");

        imageService.deleteImages(notAppear);
    }

    /**
     * 删除单个课程
     * @param courseId
     */
    public void deleteCourse(String courseId){
        List<String> imagePaths = imageService.getImagePath(courseId);

        if (courseDao.deleteCourse(courseId) != 1)
            throw new ErrorException("删除课程失败,请重新操作");

        imageService.deleteImages(imagePaths);
    }

    /**
     * 批量删除课程
     * @param courseIds
     */
    public void deleteCourses(List<String> courseIds){
        List<String> imagePaths = imageService.getImagePaths(courseIds);

        if (courseDao.deleteCourses(courseIds) != courseIds.size())
            throw new ErrorException("删除随笔失败,请重新操作");

        imageService.deleteImages(imagePaths);
    }

    /**
     * 更新课程
     * @param course
     */
    public void updateCourse(Course course){
        List<String> notAppear = ListUtil.searchList(course.getCourseContent(),imageService.getImagePath(course.getCourseId()));  //该文章原有的全部的图片

        if (courseDao.updateCourse(course) != 1)//更新数据库
            throw new ErrorException("更新失败,请重新操作");

        imageService.deleteImages(notAppear);
    }

    public Pager<Course> findAllCourses(int pageIndex, int pageSize){
        Pager<Course> coursePager = new Pager<Course>(pageIndex,pageSize,courseDao.getTotal());

        List<Course> courses = courseDao.findAllCourses(coursePager.getOffSet(),pageSize);
        if (courses == null || courses.size() == 0)
            throw new ErrorException("没有找到你要的数据");

        coursePager.setResult(courses);
        return coursePager;
    }

    public List<Course> findCourseByKeyWord(String key) {
        return courseDao.findCoursesByKeyWord(key);
    }

    public List<Course> findCourseByTime(String date) {
        return courseDao.findCourseByTime(date);
    }
}
