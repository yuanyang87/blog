package blog.geek.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * 字符串随机生成工具类
 * @author yuanyang
 * @version 1.0
 */
public class RandomStringUtil {

    private final static String NUMBER = "0123456789";  //数字
    private final static String UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";  //大写字母
    private final static String LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";  //小写字母
    private final static String PUNCTUATION = "`~!@#$%^&*()-_=+[{]}\\|;:'\\\",<.>/?";

    //默认情况下使用数字,大小写字母生成字符串
    private static boolean useNumber = true;
    private static boolean useUpperCase = true;
    private static boolean useLowerCase = true;

    private static boolean usePunctuation = false; //默认不使用标点

    private int length; //需要生成的随机字符串长度

    //setter
    public void setUseNumber(boolean useNumber) {
        this.useNumber = useNumber;
    }

    public void setUseUpperCase(boolean useUpperCase) {
        this.useUpperCase = useUpperCase;
    }

    public void setUseLowerCase(boolean useLowerCase) {
        this.useLowerCase = useLowerCase;
    }

    public void setUsePunctuation(boolean usePunctuation) {
        this.usePunctuation = usePunctuation;
    }

    public void setLength(int length) {
        this.length = length;
    }

    /**
     * 生成可重复的随机字符串
     * @param length
     * @return
     */
    public static String repeatableString(int length){
        String code = getCode();
        StringBuffer password = new StringBuffer();
        for (int i = 0; i < length; i++){
            password.append(code.charAt((int) (Math.random()*code.length())));
        }
        return password.toString();
    }

    /**
     * 生成不可重复的随机字符串
     * @param length
     * @return
     */
    public static String unrepeatableString(int length){
        String code = NUMBER + UPPER_CASE + LOWER_CASE;
        StringBuffer password = new StringBuffer();

        for (int i = 0; i < length; i++){
            char ch;
            do {
                ch = code.charAt((int) (Math.random()*code.length()));
            } while(password.indexOf(String.valueOf(ch)) != -1);
            password.append(ch);
        }

        return password.toString();
    }

    /**
     * 基本字符串
     * @return
     */
    public static String getCode(){
        String code = "";
        if (RandomStringUtil.useNumber)
            code += RandomStringUtil.NUMBER;
        if (RandomStringUtil.useLowerCase)
            code += RandomStringUtil.LOWER_CASE;
        if (RandomStringUtil.useUpperCase)
            code += RandomStringUtil.UPPER_CASE;
        if (RandomStringUtil.usePunctuation)
            code += RandomStringUtil.PUNCTUATION;
        return code;
    }
}
