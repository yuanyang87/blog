package blog.geek.entity;

/**
 * 轮播图片
 * @author yuanyang
 * @version 1.0
 */
public class Banner {

    private String bannerId;    //轮播图编号
    private String bannerType;  //轮播图类型(分为顶部轮播与下方的下轮播)
    private String[] bannerImg; //轮播图数组

    //start getter and setter
    public String getBannerId() {
        return bannerId;
    }

    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }

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
    //end getter and setter
}
