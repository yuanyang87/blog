package blog.geek.dao;

import blog.geek.entity.Article;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 随笔Dao
 * @author yuanyang
 * @version 1.0
 */
@Repository
public interface ArticleDao {

    /**
     * 增
     * @param article
     * @return
     */
    int insertArticle(Article article);

    /**
     * 批量删
     * @param articleIds
     * @return
     */
    int deleteArticles(@Param("articleIds") List<String> articleIds);

    /**
     * 删
     * @param articleId
     * @return
     */
    int deleteArticle(String articleId);

    /**
     * 改
     * @param article
     * @return
     */
    int updateArticle(Article article);

    /**
     * 查询所有
     * @param offSet
     * @param limit
     * @return
     */
    List<Article> findAllArticles(int offSet,int limit);

    /**
     * 按关键字查询
     * @param key
     * @return
     */
    List<Article> findArticlesByKeyWord(String key);

    /**
     * 获取总数目
     * @return
     */
    int getTotal();

    List<Article> findArticlesByTime(String date);
}
