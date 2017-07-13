package blog.geek.manager.controller;

import blog.geek.entity.User;
import blog.geek.manager.service.UserService;
import blog.geek.utils.Result;
import blog.geek.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * 用户模块
 * @author yuanyang
 * @version 1.1
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

    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    public Result updateUser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        userService.updateUser(user);
        return ResultUtil.successResult(null);
    }

}
