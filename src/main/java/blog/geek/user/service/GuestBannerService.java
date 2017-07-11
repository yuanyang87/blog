package blog.geek.user.service;

import blog.geek.dao.BannerDao;
import blog.geek.dto.BannerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 客户端轮播图获取服务类
 * @author yuanyang
 * @version 1.0
 */
@Service
public class GuestBannerService {

    @Autowired
    private BannerDao bannerDao;

    /**
     * 前端根据不同的类型获取轮播图片
     * @param bannerType
     * @return
     */
    public BannerDTO findBannerByType(String bannerType){
        BannerDTO bannerDTO = bannerDao.findBannerByType(bannerType);
        return bannerDTO;
    }

}
