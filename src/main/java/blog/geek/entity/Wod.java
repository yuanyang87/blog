package blog.geek.entity;

public class Wod {

    private String wodId;
    private String wodTitle;
    private String wodDate;
    private String wodInfo;
    private String wodImg;

    public Wod() {
    }

    @Override
    public String toString() {
        return "Wod{" +
                "wodId='" + wodId + '\'' +
                ", wodTitle='" + wodTitle + '\'' +
                ", wodDate='" + wodDate + '\'' +
                ", wodInfo='" + wodInfo + '\'' +
                ", wodImg='" + wodImg + '\'' +
                '}';
    }

    public Wod(String wodId, String wodTitle, String wodDate, String wodInfo, String wodImg) {

        this.wodId = wodId;
        this.wodTitle = wodTitle;
        this.wodDate = wodDate;
        this.wodInfo = wodInfo;
        this.wodImg = wodImg;
    }

    public String getWodImg() {
        return wodImg;
    }

    public void setWodImg(String wodImg) {
        this.wodImg = wodImg;
    }

    public String getWodId() {
        return wodId;
    }

    public void setWodId(String wodId) {
        this.wodId = wodId;
    }

    public String getWodTitle() {
        return wodTitle;
    }

    public void setWodTitle(String wodTitle) {
        this.wodTitle = wodTitle;
    }

    public String getWodDate() {
        return wodDate;
    }

    public void setWodDate(String wodDate) {
        this.wodDate = wodDate;
    }

    public String getWodInfo() {
        return wodInfo;
    }

    public void setWodInfo(String wodInfo) {
        this.wodInfo = wodInfo;
    }
}
