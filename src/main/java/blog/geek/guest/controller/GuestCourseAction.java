package blog.geek.guest.controller;

import blog.geek.entity.Course;
import blog.geek.guest.service.GuestCourseService;
import blog.geek.utils.Result;
import blog.geek.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 客户端课程请求处理
 * @author yuanyang
 * @version 1.0
 */
@RestController
public class GuestCourseAction {

    @Autowired
    private GuestCourseService guestCourseService;

    @RequestMapping(value = "/findAllCourses",method = RequestMethod.GET)
    public Result findAllCourses(){
        List<Course> courses = guestCourseService.findAllCourses();
        return ResultUtil.successResult(courses);
    }

}
