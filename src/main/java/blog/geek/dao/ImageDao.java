package blog.geek.dao;

import org.springframework.stereotype.Repository;

/**
 * 图片DAO
 * @author yuanyang
 * @version 1.0
 */
@Repository
public interface ImageDao {

    /**
     * 通过图片指向某对象的关键字,获取图片的虚拟地址,以供删除,
     * @param key
     * @return
     */
    String getImagePath(String key);

}
