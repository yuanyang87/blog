package blog.geek.guest.service;

import blog.geek.dao.ArticleDao;
import blog.geek.dao.CourseDao;
import blog.geek.dao.TrainDao;
import blog.geek.entity.Article;
import blog.geek.entity.Course;
import blog.geek.entity.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 客户搜索服务
 * @author yuanyang
 * @version 1.0
 */
@Service
public class SearchService {

    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private TrainDao trainDao;

    public List<Object> searchAll(String key){
        List<Object> objects = new ArrayList();

        List<Article> articles = articleDao.findArticlesByKeyWord(key);
        if (articles != null && articles.size() > 0)
            objects.add(articles);

        List<Course> courses = courseDao.findCoursesByKeyWord(key);
        if (courses != null && courses.size() > 0)
            objects.add(courses);

        List<Train>  trains = trainDao.findTrainsByKeyWord(key);
        if (trains != null && trains.size() > 0)
            objects.add(trains);
        return objects;
    }

}
