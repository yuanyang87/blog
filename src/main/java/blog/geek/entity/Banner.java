package blog.geek.entity;

/**
 * 轮播图片
 * @author yuanyang
 * @version 1.1 去除了轮播的类型.前端决定把下部分轮播写死.
 */
public class Banner {

    private String bannerId;    //轮播图编号
    private String bannerImg;   //轮播图地址
    private String bannerType;  //轮播类型

    //start getter and setter
    public String getBannerId() {
        return bannerId;
    }

    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }

    public String getBannerImg() {
        return bannerImg;
    }

    public void setBannerImg(String bannerImg) {
        this.bannerImg = bannerImg;
    }

    public String getBannerType() {
        return bannerType;
    }

    public void setBannerType(String bannerType) {
        this.bannerType = bannerType;
    }

    //end getter and setter
}
