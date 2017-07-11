package blog.geek.dto;

import java.util.List;

/**
 * 轮播dto对象
 * @author yuanyang
 * @version 1.0
 */
public class BannerDTO {

    private String bannerType;
    private List<String> bannerImg;

    public String getBannerType() {
        return bannerType;
    }

    public void setBannerType(String bannerType) {
        this.bannerType = bannerType;
    }

    public List<String> getBannerImg() {
        return bannerImg;
    }

    public void setBannerImg(List<String> bannerImg) {
        this.bannerImg = bannerImg;
    }
}
