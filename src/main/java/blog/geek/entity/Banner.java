package blog.geek.entity;

/**
 * 轮播图片
 * @author yuanyang
 * @version 1.1 去除了轮播的类型.前端决定把下部分轮播写死.
 */
public class Banner {

    private String bannerId;    //轮播图编号
    private String bannerImg;   //轮播图地址

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

    //end getter and setter
}
