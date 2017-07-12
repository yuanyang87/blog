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

import java.util.List;

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
     * 先存放图片,再插入数据库,如果数据插入失败,则删除图片再回滚
     * @param banner
     * @param picture
     */
    public void insertBanner(Banner banner, MultipartFile picture) {
        if (picture == null)
            throw new ErrorException("您还没有选择图片哟,请重新操作");

        banner.setBannerId(RandomStringUtil.repeatableString(8));//为banner添加Id
        Image image = new Image(RandomStringUtil.unrepeatableString(8),
                picture.getOriginalFilename(), banner.getBannerId());//创建Image对象

        if (!fileUtil.saveImage(picture, banner.getClass().getSimpleName(), banner.getBannerId()))//存放图片
            throw new ErrorException("服务器在开小差,图片存放失败");

        image.setImageAddress(fileUtil.getVirtualPath());//获取图片的虚拟路径

        if (bannerDao.insertBannerImage(banner,image) != 1){//添加到数据库
            fileUtil.deleteImage(image.getImageAddress());//删除图片,以免浪费服务器存储空间
            throw new ErrorException("数据库出错啦~,请重新操作");
        }
    }

    /**
     * 删除轮播图
     * 先删除数据库再删除图片,如果先删除图片在数据库出错的情况下图片无法回滚
     * @param bannerId
     */
    public void deleteBanner(String bannerId){
        List<String> imagePath = imageDao.getImagePath(bannerId); //获取该轮播图片的虚拟地址
        if (bannerDao.deleteBanner(bannerId) != 1)  //删除数据库中的数据
            throw new ErrorException("数据库出错啦~,请重新操作");
        fileUtil.deleteImage(imagePath.get(0));    //在磁盘上删除图片
    }

    /**
     * 更新一张轮播图
     * 1.把图片存放到磁盘
     * 2.获取之前的图片存放路径
     * 3.更新数据库信息,如果更新失败删除新图片,回滚
     * 4.数据库更新成功,把原来的图片删除
     * @param banner
     * @param picture
     */
    public void updateBanner(Banner banner,MultipartFile picture){
        if (picture == null){
            bannerDao.updateBanner(banner,new Image());
            return;
        }
        Image image = new Image(RandomStringUtil.repeatableString(8),
                picture.getOriginalFilename(),banner.getBannerId());

        fileUtil.saveImage(picture,Banner.class.getSimpleName(),banner.getBannerId());

        List<String> imagePath = imageDao.getImagePath(banner.getBannerId());

        if (bannerDao.updateBanner(banner,image) != 1){
            fileUtil.deleteImage(fileUtil.getRealPath());
            throw new ErrorException("数据库出错啦~,请重新操作");
        }

        fileUtil.deleteImage(imagePath.get(0));
    }

    /**
     * 获取所有的轮播图片
     * @return
     */
    public List<Banner> findAllBanners(){
        List<Banner> banners = bannerDao.findAllBanners();
        return banners;
    }
}
