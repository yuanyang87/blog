package blog.geek.user.controller;

import blog.geek.dto.BannerDTO;
import blog.geek.user.service.GuestBannerService;
import blog.geek.utils.Result;
import blog.geek.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 前端获取轮播请求处理
 * @author yuanyang
 * @version 1.0
 */
@RestController
public class GuestBannerAction {

    @Autowired
    private GuestBannerService guestBannerService;

    /**
     * 通过类型获取轮播图
     * @param bannerType
     */
    @RequestMapping(value = "/findBannerByType/{bannerType}",method = RequestMethod.GET)
    public Result findBannerByType(@PathVariable String bannerType){
        BannerDTO bannerDTO = guestBannerService.findBannerByType(bannerType);
        return ResultUtil.successResult(bannerDTO);
    }
}
