package blog.geek.manager.controller;

import blog.geek.entity.User;
import blog.geek.manager.service.UserService;
import blog.geek.utils.Result;
import blog.geek.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    /**
     * 用户登录
     * @param user
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result login(User user, HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        request.getSession().setAttribute("user",user);
        userService.login(user);
        return ResultUtil.successResult(null);
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    public Result updateUser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        userService.updateUser(user);
        return ResultUtil.successResult(null);
    }

}
