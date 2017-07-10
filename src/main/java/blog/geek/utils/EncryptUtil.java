package blog.geek.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密工具类
 * @author yuanyang
 * @version 1.0
 */
public class EncryptUtil {

    /**
     * MD5加密算法,生成32位MD5码
     * @param string
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String MD5(String string) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");

        byte[] md5Bytes = messageDigest.digest(string.getBytes("utf-8"));

        return transferByte(md5Bytes);
    }

    /**
     * SHA加密算法,生成40位SHA码
     * @param string
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String SHA(String string) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA");

        byte[] shaBytes = messageDigest.digest(string.getBytes("utf-8"));

        return transferByte(shaBytes);
    }

    private static String transferByte(byte[] bytes) {
        StringBuffer password = new StringBuffer();

        for (byte b : bytes){
            int value = b & 0xff;
            if (value < 16){
                password.append("0");
            }
            password.append(Integer.toHexString(value));
        }
        return password.toString();
    }

}
