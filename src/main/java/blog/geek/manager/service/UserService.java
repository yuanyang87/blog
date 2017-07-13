package blog.geek.manager.service;

import blog.geek.dao.UserDao;
import blog.geek.entity.User;
import blog.geek.utils.EncryptUtil;
import blog.geek.exception.ErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * 管理用户登录模块
 * @author yuanyang
 * @version 1.1
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 用户登录
     * @param user
     */
    public void login(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String password = userDao.findPassword(user.getUserName());
        //System.out.println(password);
        //System.out.println(EncryptUtil.MD5(user.getPassword()));
        if (!EncryptUtil.MD5(user.getPassword()).equals(password)){
            throw new ErrorException("用户名或密码错误,请重新登录!");
        }
    }

    /**
     * 更新用户
     * @param user
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    public void updateUser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        user.setPassword(EncryptUtil.MD5(user.getPassword()));
        if (userDao.updateUser(user) != 1){
            throw new ErrorException("更新用户失败");
        }
    }

}
