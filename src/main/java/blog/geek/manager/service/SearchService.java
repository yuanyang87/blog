package blog.geek.manager.service;

import blog.geek.entity.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 客户搜索服务
 * @author yuanyang
 * @version 1.0
 */
@Service
public class SearchService {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private TrainService trainService;

    public Pager<Object> searchAll(String type, String key, int pageIndex, int pageSize){

        if (type.equals("article")){

        }
        return null;
    }

}
