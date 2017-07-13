package blog.geek.dao;

import blog.geek.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户模块Dao
 * @author yuanyang
 * @version 1.1
 */
@Repository
public interface UserDao {

    /**
     * 根据userName修改用户密码或真实姓名
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 获取用户的密码
     * @param userName
     * @return
     */
    String findPassword(String userName);
}
