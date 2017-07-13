package blog.geek.entity;

/**
 * 课程
 * @author yuanyang
 * @version 1.0
 */
public class Course {

    private String courseId;    //编号
    private String courseName;  //名称
    private String courseTime;  //发表时间
    private String courseContent; //课程内容
    private String courseLink;  //视频链接


    //start getter and setter
    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseContent() {
        return courseContent;
    }

    public void setCourseContent(String courseContent) {
        this.courseContent = courseContent;
    }

    public String getCourseLink() {
        return courseLink;
    }

    public void setCourseLink(String courseLink) {
        this.courseLink = courseLink;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    //end getter and setter
}
