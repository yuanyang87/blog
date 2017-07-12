package blog.geek.manager.controller;

import blog.geek.entity.Course;
import blog.geek.manager.service.MngCourseService;
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
public class MngCourseAction {

    @Autowired
    private MngCourseService mngCourseService;

    @RequestMapping(value = "/insertCourse",method = RequestMethod.POST)
    public Result insertCourse(Course course, MultipartFile[] pictures){
        mngCourseService.insertCourse(course,pictures);
        return ResultUtil.successResult(null);
    }

    @RequestMapping(value = "/deleteCourse/{courseId}",method = RequestMethod.DELETE)
    public Result deleteCourse(@PathVariable String courseId){
        mngCourseService.deleteCourse(courseId);
        return ResultUtil.successResult(null);
    }

    @RequestMapping(value = "/deleteCourses/{courseIdsJson}",method = RequestMethod.DELETE)
    public Result deleteCourses(@PathVariable String courseIdsJson){
        mngCourseService.deleteCourses((List<String>) JsonUtil.toPOJO(courseIdsJson,new ArrayList<String>().getClass()));
        return ResultUtil.successResult(null);
    }

    @RequestMapping(value = "/updateCourse",method = RequestMethod.POST)
    public Result updateCourse(Course course,MultipartFile[] pictures){
        mngCourseService.updateCourse(course,pictures);
        return ResultUtil.successResult(null);
    }

    @RequestMapping(value = "/findAllCourses",method = RequestMethod.GET)
    public Result findAllCourses(){
        List<Course> courses = mngCourseService.findAllCourses();
        return ResultUtil.successResult(courses);
    }


}
