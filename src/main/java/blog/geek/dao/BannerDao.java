package blog.geek.dao;

import blog.geek.entity.Banner;
import blog.geek.entity.Image;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 轮播图Dao
 * @author yuanyang
 * @version 1.0
 */
@Repository
public interface BannerDao {

    /**
     * 插入一张轮播图
     * @param banner
     * @param image
     * @return
     */
    int insertBannerImage(@Param("banner") Banner banner,@Param("image") Image image);

    /**
     * 修改一张轮播图,同时修改banner与image表的数据
     * @param banner
     * @param image
     * @return
     */
    int updateBanner(@Param("banner") Banner banner,@Param("image") Image image);

    /**
     * 删除一张轮播图
     * @param bannerId
     * @return
     */
    int deleteBanner(String bannerId);

    /**
     * 后台获取所有的轮播图
     * @return
     */
    List<Banner> findAllBanners(int offSet,int limit);

    /**
     * 获取总数目
     * @return
     */
    int getTotal();
}
