package blog.geek.manager.controller;

import blog.geek.entity.Banner;
import blog.geek.manager.service.MngBannerService;
import blog.geek.utils.Result;
import blog.geek.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 轮播图管理模块请求
 * @author yuanyang
 * @version 1.0
 */
@RestController
public class MngBannerAction {

    @Autowired
    private MngBannerService mngBannerService;

    /**
     * 添加一张轮播图
     * @param banner
     * @param picture
     * @return
     */
    @RequestMapping(value = "/insertBanner",method = RequestMethod.POST)
    public Result insertBanner(Banner banner, MultipartFile picture){
        mngBannerService.insertBanner(banner,picture);
        return ResultUtil.successResult(null);
    }


}
