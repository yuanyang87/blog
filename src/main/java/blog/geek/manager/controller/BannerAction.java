package blog.geek.manager.controller;

import blog.geek.entity.Banner;
import blog.geek.manager.service.BannerService;
import blog.geek.utils.Result;
import blog.geek.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 轮播图管理模块请求
 * @author yuanyang
 * @version 1.0
 */
@RestController
public class BannerAction {

    @Autowired
    private BannerService bannerService;

    /**
     * 添加一张轮播图
     * @param banner
     * @param picture
     * @return
     */
    @RequestMapping(value = "/management/insertBanner",method = RequestMethod.POST)
    public Result insertBanner(Banner banner, MultipartFile picture){
        String imageAddress = bannerService.insertBanner(banner,picture);
        return ResultUtil.successResult(imageAddress);
    }

    /**
     * 删除一张轮播图
     * @param bannerId
     * @return
     */
    @RequestMapping(value = "/management/deleteBanner/{bannerId}",method = RequestMethod.DELETE)
    public Result deleteBanner(@PathVariable String bannerId){
        bannerService.deleteBanner(bannerId);
        return ResultUtil.successResult(null);
    }

    /**
     * 更新轮播图,包括轮播类型,图片
     * @param banner
     * @param picture
     * @return
     */
    @RequestMapping(value = "/management/updateBanner",method = RequestMethod.POST)
    public Result updateBanner(Banner banner, MultipartFile picture){
        bannerService.updateBanner(banner,picture);
        return ResultUtil.successResult(null);
    }

    /**
     * 后台获取所有的轮播图
     * @return
     */
    @RequestMapping(value = "/findAllBanners",method = RequestMethod.GET)
    public Result findAllBanners(){
        List<Banner> banners = bannerService.findAllBanners();
        return ResultUtil.successResult(banners);
    }

    /**
     * 后台获取所有的轮播图
     * @return
     */
    @RequestMapping(value = "/findBannerByType",method = RequestMethod.GET)
    public Result findBannerByType(String bannerType){
        List<Banner> banners = bannerService.findBannerByType(bannerType);
        return ResultUtil.successResult(banners);
    }

}
