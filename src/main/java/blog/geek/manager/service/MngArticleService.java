package blog.geek.manager.service;

import blog.geek.dao.ArticleDao;
import blog.geek.dao.ImageDao;
import blog.geek.entity.Article;
import blog.geek.entity.Image;
import blog.geek.exception.ErrorException;
import blog.geek.utils.FileUtil;
import blog.geek.utils.RandomStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 管理员的文章服务类
 * @author yuanyang
 * @version 1.0
 */
@Service
public class MngArticleService {

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
     * @param pictures
     */
    public void insertArticle(Article article, MultipartFile[] pictures){
        if (pictures == null || pictures.length == 0){//没有图片
            article.setArticleId(RandomStringUtil.unrepeatableString(8)); //设置编号
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置书写日期
            article.setArticleTime(simpleDateFormat.format(new Date()));//
            articleDao.insertArticle(article);//插入数据
            return;
        }//有图片
        article.setArticleId(RandomStringUtil.unrepeatableString(8));//设置编号
        List<Image> images = transversePictureAndSave(pictures,article);//遍历图片并存储
        if (articleDao.insertArticle(article) != 1)//存放随笔数据
            deleteImage(images);
        if (imageDao.insertImages(images) != images.size())//存放相关图片数据
            deleteImage(images);
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
     * @param pictures
     */
    public void updateArticle(Article article, MultipartFile[] pictures){
        if (pictures == null || pictures.length == 0){//没有新传入的图片
            List<String> existPaths = article.getArticleImg();//没有被删除的图片地址
            List<String> allPaths = imageDao.getImagePath(article.getArticleId());  //该文章原有的全部的图片
            deleteNotExistImage(getDiff(existPaths,allPaths),article);//删除已经不存在的图片数据
            if (articleDao.updateArticle(article) != 1)//更新数据库
                throw new ErrorException("更新随笔失败,请重新操作");
            fileUtil.deleteImages(getDiff(existPaths,allPaths)); //删除已经被删除的图片
            return;
        }
        List<String> existPaths = article.getArticleImg();//没有被删除的图片地址
        List<String> allPaths = imageDao.getImagePath(article.getArticleId());  //该文章原有的全部的图片
        List<Image> images = transversePictureAndSave(pictures,article);//遍历图片并存储
        deleteNotExistImage(getDiff(existPaths,allPaths),article); //删除已经不存在的图片数据
        if (articleDao.updateArticle(article) != 1)//更新数据库
            deleteImage(images);
        if (imageDao.insertImages(images) != images.size())
            deleteImage(images);
        fileUtil.deleteImages(getDiff(existPaths,allPaths)); //删除磁盘上已经被删除的图片
    }

    /**
     * 获取全部文章
     * @return
     */
    public List<Article> findAllArticles() {
        List<Article> articles = articleDao.findAllArticles();
        if (articles == null || articles.size() == 0)
            throw new ErrorException("没有找到任何随笔");
        return articles;
    }

    /**
     * 在出现错误的情况下,删除已经存储的图片,并抛出异常即可使数据回滚
     * @param images
     */
    public void deleteImage(List<Image> images){
        for (Image image:images){
            fileUtil.deleteImage(image.getImageAddress());
        }
        throw new ErrorException("添加随笔失败,请重新操作");
    }

    /**
     * 删除已经不存在的图片
     * @param notExistPaths
     * @param article
     */
    public void deleteNotExistImage(List<String> notExistPaths, Article article){
        if (notExistPaths == null || notExistPaths.size() == 0) //当文章本来没有图,则这个值会为空
            return;
        if(imageDao.deleteImagesNotInList(notExistPaths,article.getArticleId()) != notExistPaths.size())
            throw new ErrorException("更新随笔失败,请重新操作");
    }

    /**
     * 遍历图片并存储
     * @param pictures
     * @param article
     * @return
     */
    public List<Image> transversePictureAndSave(MultipartFile[] pictures, Article article){
        List<Image> images = new ArrayList<Image>();
        for (MultipartFile file : pictures){
            Image image = new Image(RandomStringUtil.unrepeatableString(8),
                    file.getOriginalFilename(),article.getArticleId());
            if (!fileUtil.saveImage(file,article.getClass().getSimpleName(),article.getArticleId())){
                deleteImage(images);
            }
            image.setImageAddress(fileUtil.getVirtualPath());
            images.add(image);
        }
        return images;
    }

    /**
     * 快速找出两个 List 中的不同元素
     * @param exist
     * @param all
     * @return
     */
    public List<String> getDiff(List<String> exist,List<String> all){
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
