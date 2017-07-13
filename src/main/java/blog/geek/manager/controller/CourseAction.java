package blog.geek.manager.controller;

import blog.geek.entity.Course;
import blog.geek.manager.service.CourseService;
import blog.geek.utils.JsonUtil;
import blog.geek.utils.Result;
import blog.geek.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理课程请求处理
 * @author yuanyang
 * @version 1.0
 */
@RestController
@RequestMapping("/management")
public class CourseAction {

    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/insertCourse",method = RequestMethod.POST)
    public Result insertCourse(Course course, MultipartFile[] pictures){
        courseService.insertCourse(course,pictures);
        return ResultUtil.successResult(null);
    }

    @RequestMapping(value = "/deleteCourse/{courseId}",method = RequestMethod.DELETE)
    public Result deleteCourse(@PathVariable String courseId){
        courseService.deleteCourse(courseId);
        return ResultUtil.successResult(null);
    }

    @RequestMapping(value = "/deleteCourses/{courseIdsJson}",method = RequestMethod.DELETE)
    public Result deleteCourses(@PathVariable String courseIdsJson){
        courseService.deleteCourses((List<String>) JsonUtil.toPOJO(courseIdsJson,new ArrayList<String>().getClass()));
        return ResultUtil.successResult(null);
    }

    @RequestMapping(value = "/updateCourse",method = RequestMethod.POST)
    public Result updateCourse(Course course,MultipartFile[] pictures){
        courseService.updateCourse(course,pictures);
        return ResultUtil.successResult(null);
    }

    @RequestMapping(value = "/findAllCourses",method = RequestMethod.GET)
    public Result findAllCourses(int pageIndex,int pageSize){
        List<Course> courses = courseService.findAllCourses(pageIndex,pageSize);
        return ResultUtil.successResult(courses);
    }


}