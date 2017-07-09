package blog.geek.entity;

/**
 * 课程
 * @author yuanyang
 * @version 1.0
 */
public class Course {

    private String courseId;    //编号
    private String courseName;  //名称
    private String courseTitle; //课程标题
    private String courseLink;  //视频链接
    private String[] courseImage; //图片链接

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

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseLink() {
        return courseLink;
    }

    public void setCourseLink(String courseLink) {
        this.courseLink = courseLink;
    }

    public String[] getCourseImage() {
        return courseImage;
    }

    public void setCourseImage(String[] courseImage) {
        this.courseImage = courseImage;
    }
    //end getter and setter
}
