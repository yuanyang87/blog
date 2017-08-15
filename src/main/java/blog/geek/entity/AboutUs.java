package blog.geek.entity;

public class AboutUs {

    private String aboutTitle;  //题目
    private String aboutInfo;   //介绍

    public AboutUs() {
    }

    public AboutUs(String aboutTitle, String aboutInfo, String aboutCode) {
        this.aboutTitle = aboutTitle;
        this.aboutInfo = aboutInfo;
    }

    public String getAboutTitle() {
        return aboutTitle;
    }

    public void setAboutTitle(String aboutTitle) {
        this.aboutTitle = aboutTitle;
    }

    public String getAboutInfo() {
        return aboutInfo;
    }

    public void setAboutInfo(String aboutInfo) {
        this.aboutInfo = aboutInfo;
    }

    @Override
    public String toString() {
        return "AboutUs{" +
                "aboutTitle='" + aboutTitle + '\'' +
                ", aboutInfo='" + aboutInfo + '\'' +
                '}';
    }
}
