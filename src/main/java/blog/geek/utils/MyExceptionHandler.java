package blog.geek.utils;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 用于异常捕获并处理
 * @author yuanyang
 * @version 1.0
 */
@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(ErrorException.class)
    public Result handleErrorException(ErrorException e){
        Result result = new Result("0",e.getError());
        return result;
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public Result handleDuplicateKeyException(DuplicateKeyException e){
        Result result = new Result("0","数据库中已存在!");
        return result;
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e){
        return ResultUtil.UNHANDED_RESULT;
    }
}
