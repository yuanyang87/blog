package blog.geek.entity;

/**
 * 健身课程表
 * @author yuanyang
 * @version 2.0
 */
public class Schedule {

    private String scheduleId;  //编号
    private String scheduleImg; //图片
    private String scheduleType;    //类型:课表图和价格图

    public Schedule() {
    }

    public Schedule(String scheduleId, String scheduleImg, String scheduleType) {
        this.scheduleId = scheduleId;
        this.scheduleImg = scheduleImg;
        this.scheduleType = scheduleType;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getScheduleImg() {
        return scheduleImg;
    }

    public void setScheduleImg(String scheduleImg) {
        this.scheduleImg = scheduleImg;
    }

    public String getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(String scheduleType) {
        this.scheduleType = scheduleType;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "scheduleId='" + scheduleId + '\'' +
                ", scheduleImg='" + scheduleImg + '\'' +
                ", scheduleType='" + scheduleType + '\'' +
                '}';
    }
}
