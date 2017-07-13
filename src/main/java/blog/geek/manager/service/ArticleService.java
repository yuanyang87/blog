package blog.geek.manager.service;

import blog.geek.dao.ArticleDao;
import blog.geek.dao.ImageDao;
import blog.geek.entity.Article;
import blog.geek.entity.Pager;
import blog.geek.exception.ErrorException;
import blog.geek.utils.FileUtil;
import blog.geek.utils.RandomStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private FileUtil fileUtil;

    @Autowired
    private ImageDao imageDao;

    /**
     * 添加一篇随笔,可能有0~n个图片
     * 没有图片就直接插入文章
     * 如果有图片,先遍历文件数组,存放文件
     * 在插入数据,如果数据插入失败,删除文件
     * @param article
     */
    public void insertArticle(Article article){
        article.setArticleId(RandomStringUtil.unrepeatableString(8));//设置编号
        if (articleDao.insertArticle(article) != 1)//存放随笔数据
           throw new ErrorException("操作失败,请重新操作");
    }

    /**
     * 删除一篇随笔
     * @param articleId
     */
    public void deleteArticle(String articleId){
        List<String> imagePaths = imageDao.getImagePath(articleId);

        if (articleDao.deleteArticle(articleId) != 1)
            throw new ErrorException("删除随笔失败,请重新操作");

        if (imageDao.deleteImagesByImagePath(imagePaths) != imagePaths.size())
            throw new ErrorException("删除随笔失败,请重新操作");

        fileUtil.deleteImages(imagePaths);//如果数据都删除成功,则删除磁盘上的图片
    }

    /**
     * 批量删除随笔
     * @param articleIds
     */
    public void deleteArticles(List<String> articleIds){
        List<String> imagePaths = imageDao.getImagePaths(articleIds);

        if (articleDao.deleteArticles(articleIds) != articleIds.size())
            throw new ErrorException("删除随笔失败,请重新操作");

        if (imageDao.deleteImagesByImagePath(imagePaths) != imagePaths.size())
            throw new ErrorException("删除随笔失败,请重新操作");

        fileUtil.deleteImages(imagePaths);
    }

    /**
     * 更新文章,可能会更新0-n张图片
     * @param article
     */
    public void updateArticle(Article article){
        List<String> allPaths = imageDao.getImagePath(article.getArticleId());  //该文章原有的全部的图片
        if (articleDao.updateArticle(article) != 1)//更新数据库
            throw new ErrorException("更新失败,请重新操作");
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

}
