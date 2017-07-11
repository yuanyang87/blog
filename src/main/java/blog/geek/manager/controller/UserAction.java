package blog.geek.manager.controller;

import blog.geek.entity.User;
import blog.geek.manager.service.UserService;
import blog.geek.utils.Result;
import blog.geek.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * 用户模块
 * @author yuanyang
 * @version 1.0
 */
@RestController
@RequestMapping("/management")
public class UserAction {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result login(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        userService.login(user);
        return ResultUtil.successResult(null);
    }

    @RequestMapping(value = "/insertUser",method = RequestMethod.POST)
    public Result insertUser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        userService.insertUser(user);
        return ResultUtil.successResult(null);
    }

    @RequestMapping(value = "/deleteUser/{userName}",method = RequestMethod.DELETE)
    public Result deleteUser(@PathVariable String userName){
        userService.deleteUser(userName);
        return ResultUtil.successResult(null);
    }

    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    public Result updateUser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        userService.updateUser(user);
        return ResultUtil.successResult(null);
    }

    @RequestMapping(value = "/findAllUsers",method = RequestMethod.GET)
    public Result findAllUsers(){
        List<User> users = userService.findAllUsers();
        return ResultUtil.successResult(users);
    }

}
