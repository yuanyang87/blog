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

}
