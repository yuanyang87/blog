package blog.geek.utils;

/**
 * 自定义异常,当service层处理失败直接抛出异常,避免了过多的判断语句
 * @author yuanyang
 * @version 1.0
 */
public class ErrorException extends RuntimeException {

    private String error;

    //constructor
    public ErrorException(String error) {
        this.error = error;
    }

    //start getter and setter
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    //end getter and setter
}
