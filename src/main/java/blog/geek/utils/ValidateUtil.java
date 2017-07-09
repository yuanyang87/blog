package blog.geek.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串验证工具类
 * @author yuanyang
 * @version 1.0
 */
public class ValidateUtil {


    /**
     * 字符串为空值或空串
     * @return
     */
    public static boolean isEmpty(String value){
        return "".equals(value) || value == null;
    }

    /**
     * 字符串非空值或空串
     * @param value
     * @return
     */
    public static boolean notEmpty(String value){
        return !"".equals(value) || value != null;
    }

    /**
     * 正则表达式验证
     *
     * @param regex
     * @param value
     * @return true 为成功, false 为失败
     */
    public static boolean matches(String regex, String value) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(value);
        return m.find();
    }

}
