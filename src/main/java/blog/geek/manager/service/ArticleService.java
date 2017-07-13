package blog.geek.manager.service;

import blog.geek.dao.ArticleDao;
import blog.geek.entity.Article;
import blog.geek.entity.Pager;
import blog.geek.exception.ErrorException;
import blog.geek.utils.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 管理员的文章服务类
 * @author yuanyang
 * @version 1.0
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private ImageService imageService;

    /**
     * 添加文章
     * 再写的时候已经将图片上传了,为了避免有些图片在编辑过程中被删除,先要找出没有使用的图片,全部删除
     * @param article
     *
     */
    public void insertArticle(Article article){
        List<String> notAppear = ListUtil.searchList(article.getArticleContent(),imageService.getImagePath(article.getArticleId()));    //没有出现的那些图片地址

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        article.setArticleTime(simpleDateFormat.format(new Date()));

        if (articleDao.insertArticle(article) != 1)//存放随笔数据
           throw new ErrorException("提交失败,请重新操作");

        imageService.deleteImages(notAppear);
    }

    /**
     * 删除一篇随笔,连同其图片一起删除
     * @param articleId
     */
    public void deleteArticle(String articleId){
        List<String> imagePaths = imageService.getImagePath(articleId); //找出该文章使用的图片

        if (articleDao.deleteArticle(articleId) != 1)
            throw new ErrorException("删除失败,请重新操作");

        imageService.deleteImages(imagePaths);
    }

    /**
     * 批量删除随笔
     * @param articleIds
     */
    public void deleteArticles(List<String> articleIds){
        List<String> imagePaths = imageService.getImagePaths(articleIds);   //要被删除的所有文章Id

        if (articleDao.deleteArticles(articleIds) != articleIds.size()) //删除文章数据
            throw new ErrorException("删除失败,请重新操作");

        imageService.deleteImages(imagePaths);
    }

    /**
     * 更新文章
     * @param article
     */
    public void updateArticle(Article article){
        List<String> notAppear = ListUtil.searchList(article.getArticleContent(),imageService.getImagePath(article.getArticleId()));

        if (articleDao.updateArticle(article) != 1)//更新数据库
            throw new ErrorException("更新失败,请重新操作");

        imageService.deleteImages(notAppear);
    }

    /**
     * 查看所有文章
     * @return
     * @param pageIndex
     * @param pageSize
     */
    public Pager<Article> findAllArticles(int pageIndex, int pageSize){
        Pager<Article> articlePager = new Pager<Article>(pageIndex,pageSize,articleDao.getTotal());

        List<Article> articles = articleDao.findAllArticles(articlePager.getOffSet(),pageSize);

        if (articles == null || articles.size() == 0)
            throw new ErrorException("没有找到你要的数据");

        articlePager.setResult(articles);

        return articlePager;
    }

    public List<Article> findArticlesByKeyWord(String key){
        return articleDao.findArticlesByKeyWord(key);
    }

    public List<Article> findArticlesByTime(String date) {
        return articleDao.findArticlesByTime(date);
    }
}
