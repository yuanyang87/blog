package blog.geek.manager.service;

import blog.geek.dao.CourseDao;
import blog.geek.dao.ImageDao;
import blog.geek.entity.Course;
import blog.geek.entity.Image;
import blog.geek.entity.Pager;
import blog.geek.exception.ErrorException;
import blog.geek.utils.FileUtil;
import blog.geek.utils.RandomStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理员课程服务类
 * @author yuanyang
 * @version 1.0
 */
@Service
public class CourseService {

    @Autowired
    private CourseDao courseDao;
    @Autowired
    private ImageDao imageDao;
    @Autowired
    private FileUtil fileUtil;

    /**
     * 插入课程
     * @param course
     */
    public void insertCourse(Course course){
        course.setCourseId(RandomStringUtil.unrepeatableString(8));
        if (courseDao.insertCourse(course) != 1)
            throw new ErrorException("添加失败,请重新操作");
    }

    /**
     * 删除单个课程
     * @param courseId
     */
    public void deleteCourse(String courseId){
        List<String> imagePaths = imageDao.getImagePath(courseId);

        if (courseDao.deleteCourse(courseId) != 1)
            throw new ErrorException("删除课程失败,请重新操作");
        if (imageDao.deleteImagesByImagePath(imagePaths) != imagePaths.size())
            throw new ErrorException("删除课程失败,请重新操作");
        fileUtil.deleteImages(imagePaths);
    }

    /**
     * 批量删除课程
     * @param courseIds
     */
    public void deleteCourses(List<String> courseIds){
        List<String> imagePaths = imageDao.getImagePaths(courseIds);

        if (courseDao.deleteCourses(courseIds) != courseIds.size())
            throw new ErrorException("删除随笔失败,请重新操作");

        if (imageDao.deleteImagesByImagePath(imagePaths) != imagePaths.size())
            throw new ErrorException("删除随笔失败,请重新操作");

        fileUtil.deleteImages(imagePaths);
    }

    /**
     * 更新课程
     * @param course
     */
    public void updateCourse(Course course){
        List<String> allPaths = imageDao.getImagePath(course.getCourseId());  //该文章原有的全部的图片
        if (courseDao.updateCourse(course) != 1)//更新数据库
         throw new ErrorException("更新失败,请重新操作");
    }

    public Pager<Course> findAllCourses(int pageIndex, int pageSize){
        Pager<Course> coursePager = new Pager<Course>(pageIndex,pageSize,courseDao.getTotal());

        List<Course> courses = courseDao.findAllCourses(coursePager.getOffSet(),pageSize);
        if (courses == null || courses.size() == 0)
            throw new ErrorException("没有找到你要的数据");

        coursePager.setResult(courses);
        return coursePager;
    }

    /**
     * 遍历图片
     * @param pictures
     * @param course
     * @return
     */
    private List<Image> transversePictureAndSave(MultipartFile[] pictures, Course course) {
        List<Image> images = new ArrayList<Image>();
        for (MultipartFile file : pictures){
            Image image = new Image(RandomStringUtil.unrepeatableString(8),
                    file.getOriginalFilename(),course.getCourseId());
            if (!fileUtil.saveImage(file,course.getClass().getSimpleName(),course.getCourseId())){
                deleteImage(images);
            }
            image.setImageAddress(fileUtil.getVirtualPath());
            images.add(image);
        }
        return images;
    }

    /**
     * 删除图片
     * @param images
     */
    private void deleteImage(List<Image> images) {
        for (Image image:images){
            fileUtil.deleteImage(image.getImageAddress());
        }
        throw new ErrorException("操作失败,请重新操作");
    }
}
