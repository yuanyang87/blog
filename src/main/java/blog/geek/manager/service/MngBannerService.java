package blog.geek.manager.service;

import blog.geek.dao.BannerDao;
import blog.geek.dao.ImageDao;
import blog.geek.entity.Banner;
import blog.geek.entity.Image;
import blog.geek.exception.ErrorException;
import blog.geek.utils.FileUtil;
import blog.geek.utils.RandomStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 服务器端轮播管理服务类
 * @author yuanyang
 * @version 1.0
 */
@Service
public class MngBannerService {

    @Autowired
    private BannerDao bannerDao;

    @Autowired
    private ImageDao imageDao;

    @Autowired
    private FileUtil fileUtil;

    /**
     * 添加轮播图
     *
     * @param banner
     * @param picture
     */
    public void insertBanner(Banner banner, MultipartFile picture) {
        banner.setBannerId(RandomStringUtil.repeatableString(8));
        Image image = new Image(RandomStringUtil.unrepeatableString(8),
                picture.getOriginalFilename(), banner.getBannerId());

        if (!fileUtil.saveImage(picture, banner.getClass().getSimpleName(), banner.getBannerId()))
            throw new ErrorException("服务器在开小差,图片存放失败");

        image.setImageAddress(fileUtil.getVirtualPath());

        if (bannerDao.insertBanner(banner) == 1){
            if (imageDao.insertImage(image) != 1)
                throw new ErrorException("数据库出错啦~,请重新操作");
        }
    }
}
