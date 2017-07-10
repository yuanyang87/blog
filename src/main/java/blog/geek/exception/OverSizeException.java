package blog.geek.exception;

/**
 * 文件太大异常
 * @author yuanyang
 * @version 1.0
 */
public class OverSizeException extends RuntimeException{

    public OverSizeException() {
    }

    public OverSizeException(String message) {
        super(message);
    }

}
