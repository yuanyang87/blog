package blog.geek.entity;

/**
 * 管理员用户
 * @author yuanyang
 * @version 1.0
 */
public class User {

    private String userName;    //用于登录的用户名
    private String password;    //密码
    private String realName;    //真实姓名

    //start getter and setter
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    //end getter and setter
}
