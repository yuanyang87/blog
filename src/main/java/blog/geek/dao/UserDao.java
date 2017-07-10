package blog.geek.dao;

import blog.geek.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户模块Dao
 * @author yuanyang
 * @version 1.0
 */
@Repository
public interface UserDao {

    /**
     * 添加一名用户
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 删除一名用户
     * @param userName
     * @return
     */
    int deleteUser(String userName);

    /**
     * 根据userName修改用户密码或真实姓名
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 查询所有用户
     * @return
     */
    List<User> findAllUsers();

    /**
     * 获取用户的密码
     * @param userName
     * @return
     */
    String findPassword(String userName);
}
