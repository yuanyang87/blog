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

    int insertArticle(Article article);

    int deleteArticles(@Param("articleIds") List<String> articleIds);

    int deleteArticle(String articleId);

    int updateArticle(Article article);

    List<Article> findAllArticles();

    List<Article> findArticlesByKeyWord(String key);
}
