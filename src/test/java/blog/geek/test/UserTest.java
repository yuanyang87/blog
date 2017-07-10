package blog.geek.test;

import blog.geek.entity.User;
import blog.geek.manager.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * 用户功能模块测试
 * @author yuanyang
 * @version 1.0
 */
@Component
public class UserTest extends BaseActionTest{

    @Autowired
    private UserService userService;

    @Test
    public void insert() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        User user = new User();
        user.setUserName("yuanyang");
        user.setPassword("123456");
        user.setRealName("袁阳");

        userService.insertUser(user);
    }


    @Test
    public void find(){
        System.out.println(userService.findAllUsers());
    }

    @Test
    public void update() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        User user = new User();
        user.setUserName("yuanyang");
        user.setPassword("654321");
        user.setRealName("侯虹谷");
        userService.updateUser(user);
    }

    @Test
    public void delete(){
        userService.deleteUser("yuanyang");
    }

    @Test
    public void login() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        User user = new User();
        user.setUserName("yuanyang");
        user.setPassword("123456");
        userService.login(user);
    }
}
