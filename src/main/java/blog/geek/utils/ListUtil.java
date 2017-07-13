package blog.geek.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * list工具类
 * @author yuanyang
 * @version 1.0
 */
public class ListUtil {

    /**
     * 快速找出两个 List 中的不同元素
     * @param exist
     * @param all
     * @return
     */
    public static List<String> getDiff(List<String> exist, List<String> all){
        if (exist == null || exist.size() == 0) //如果小的集合没有数据,直接返回全部数据
            return all;
        List<String> diff = new ArrayList<String>(); //存放不同数据的List
        for (String str : all){
            if (!exist.contains(str))
                diff.add(str);
        }
        return diff;
    }

    /**
     * 查找算法,查找这一些字符串是否在文本中出现
     * @param text
     * @param strings
     * @return
     */
    public static List<String> searchList(String text,List<String > strings){
        List<String> notAppear = new ArrayList<String>();   //没有出现的字符串
        if (strings == null || strings.size() == 0)
            return null;
        for (String str : strings){
            if (text.indexOf(str) == -1)
                notAppear.add(str);
        }
        return notAppear;
    }
}
