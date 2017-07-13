package blog.geek.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.HashMap;

/**
 * 客户搜索服务
 * @author yuanyang
 * @version 1.0
 */
@Service
public class SearchService<T> {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private TrainService trainService;

    public HashMap<String,Object> searchAll(String type, String key) throws ParseException {
        HashMap<String,Object> objects = new HashMap<String, Object>();
        if (type.equals("title")){
             objects.put("articles",articleService.findArticlesByKeyWord("%" + key + "%"));
             objects.put("courses",courseService.findCourseByKeyWord("%" + key + "%"));
             objects.put("trains",trainService.findTrainByKeyWord("%" + key + "%"));
             return objects;
        }
        if (type.equals("time")){
            String dateString = key.replace(".","-");

            objects.put("articles",articleService.findArticlesByTime(dateString));
            objects.put("courses",courseService.findCourseByTime(dateString));
            objects.put("trains",trainService.findTrainByTime(dateString));
            return objects;
        }
        return null;
    }

}
