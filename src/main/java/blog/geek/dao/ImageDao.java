package blog.geek.dao;

import blog.geek.entity.Article;
import blog.geek.entity.Image;
import com.sun.xml.internal.bind.v2.util.CollisionCheckStack;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 图片DAO
 * @author yuanyang
 * @version 1.0
 */
@Repository
public interface ImageDao {

    /**
     * 批量添加图片
     * @param images
     * @return
     */
    int insertImages(@Param("images") List<Image> images);

    /**
     * 通过图片路径批量删除图片,图片路径唯一
     * @param imagePaths
     * @return
     */
    int deleteImagesByImagePath(@Param("paths") List<String> imagePaths);

    /**
     * 删除不在此列的图片
     * @param imagePaths
     * @param articleId
     * @return
     */
    int deleteImagesNotInList(@Param("imagePaths") List<String> imagePaths, @Param("articleId") String articleId);

    /**
     * 通过图片指向某对象的关键字,获取图片的虚拟地址,以供删除,
     * @param key
     * @return
     */
    List<String> getImagePath(String key);

    /**
     * 计算与之关联的对象所拥有的图片数量
     * @param key
     * @return
     */
    int countImages(String key);

    /**
     * 获取下列随笔的所有图片
     * @param articleIds
     * @return
     */
    List<String> getImagePaths(@Param("articleIds") List<String> articleIds);

    int insertImage(Image image);
}
