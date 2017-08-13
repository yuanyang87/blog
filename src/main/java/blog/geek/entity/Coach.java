package blog.geek.entity;

public class Coach {

    private String coachId;
    private String coachName;
    private String coachIntroduction;
    private String coachImg;

    public Coach() {
    }

    public Coach(String coachId, String coachName, String coachIntroduction, String coachImg) {
        this.coachId = coachId;
        this.coachName = coachName;
        this.coachIntroduction = coachIntroduction;
        this.coachImg = coachImg;
    }

    @Override
    public String toString() {
        return "Coach{" +
                "coachId='" + coachId + '\'' +
                ", coachName='" + coachName + '\'' +
                ", coachIntroduction='" + coachIntroduction + '\'' +
                ", coachImg='" + coachImg + '\'' +
                '}';
    }

    public String getCoachImg() {
        return coachImg;
    }

    public void setCoachImg(String coachImg) {
        this.coachImg = coachImg;
    }

    public String getCoachId() {
        return coachId;
    }

    public void setCoachId(String coachId) {
        this.coachId = coachId;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public String getCoachIntroduction() {
        return coachIntroduction;
    }

    public void setCoachIntroduction(String coachIntroduction) {
        this.coachIntroduction = coachIntroduction;
    }

}
