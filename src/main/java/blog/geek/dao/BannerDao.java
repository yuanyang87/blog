package blog.geek.dao;

import blog.geek.dto.BannerDTO;
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
     * 添加一张轮播图
     * 同时在banner表与image表里面添加
     * @param banner
     * @return
     */
    int insertBanner(Banner banner);

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
     * 前端获取不同类型的轮播图
     * @param bannerType
     * @return
     */
    BannerDTO findBannerByType(String bannerType);

    /**
     * 后台获取所有的轮播图
     * @return
     */
    List<Banner> findAllBanners();
}
