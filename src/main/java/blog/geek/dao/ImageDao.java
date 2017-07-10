package blog.geek.dao;

import blog.geek.entity.Image;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 图片Dao
 * @author yuanyang
 * @version 1.0
 */
@Repository
public interface ImageDao {

    /**
     * 增加一张图片
     * @param image
     * @return
     */
    int insertImage(Image image);

    /**
     * 删除一张图片
     * @param imageId
     * @return
     */
    int deleteImage(String imageId);

    /**
     * 更新一张图片
     * @param image
     * @return
     */
    int updateImage(Image image);

}
