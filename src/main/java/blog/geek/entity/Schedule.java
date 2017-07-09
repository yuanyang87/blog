package blog.geek.entity;

/**
 * 健身课程表
 * @author yuanyang
 * @version 1.0
 */
public class Schedule {

    private String scheduleId;  //编号
    private String scheduleName;    //课程名称
    private String scheduleContent; //课程内容
    private String scheduleWeekday; //周几
    private String scheduleTime;    //时间段

    //start getter and setter
    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    public String getScheduleContent() {
        return scheduleContent;
    }

    public void setScheduleContent(String scheduleContent) {
        this.scheduleContent = scheduleContent;
    }

    public String getScheduleWeekday() {
        return scheduleWeekday;
    }

    public void setScheduleWeekday(String scheduleWeekday) {
        this.scheduleWeekday = scheduleWeekday;
    }

    public String getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(String scheduleTime) {
        this.scheduleTime = scheduleTime;
    }
    //end getter and setter
}
