package blog.geek.guest.service;

import blog.geek.dao.ArticleDao;
import blog.geek.entity.Article;
import blog.geek.exception.ErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 客户端查看随笔服务类
 * @author yuanyang
 * @version 1.0
 */
@Service
public class GuestArticleService {

    @Autowired
    private ArticleDao articleDao;

    /**
     * 查看所有文章
     * @return
     */
    public List<Article> findAllArticles(){
        List<Article> articles = articleDao.findAllArticles();
        if (articles == null)
            throw new ErrorException("没有找到任何随笔");
        return articles;
    }

}
