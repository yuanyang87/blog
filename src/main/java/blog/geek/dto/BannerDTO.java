package blog.geek.dto;

/**
 * 轮播dto对象
 * @author yuanyang
 * @version 1.0
 */
public class BannerDTO {

    private String bannerType;
    private String[] bannerImg;

    public String getBannerType() {
        return bannerType;
    }

    public void setBannerType(String bannerType) {
        this.bannerType = bannerType;
    }

    public String[] getBannerImg() {
        return bannerImg;
    }

    public void setBannerImg(String[] bannerImg) {
        this.bannerImg = bannerImg;
    }
}
