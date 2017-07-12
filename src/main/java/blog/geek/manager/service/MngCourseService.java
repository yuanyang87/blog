package blog.geek.manager.service;

import blog.geek.dao.CourseDao;
import blog.geek.dao.ImageDao;
import blog.geek.entity.Course;
import blog.geek.entity.Image;
import blog.geek.exception.ErrorException;
import blog.geek.utils.FileUtil;
import blog.geek.utils.ListUtil;
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
public class MngCourseService {

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private ImageDao imageDao;

    @Autowired
    private FileUtil fileUtil;

    /**
     * 插入课程
     * @param course
     * @param pictures
     */
    public void insertCourse(Course course, MultipartFile[] pictures){
        if (pictures == null || pictures.length == 0){//没有图片,直接存放课程
            course.setCourseId(RandomStringUtil.unrepeatableString(8));
            courseDao.insertCourse(course);
        }//有图片
        course.setCourseId(RandomStringUtil.unrepeatableString(8));
        List<Image> images = transversePictureAndSave(pictures,course);//遍历图片并存储;
        if (courseDao.insertCourse(course) != 1)
            deleteImage(images);
        if (imageDao.insertImages(images) != images.size())
            deleteImage(images);
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
     * @param pictures
     */
    public void updateCourse(Course course,MultipartFile[] pictures){
        if (pictures == null || pictures.length == 0){//没有新传入的图片
            List<String> existPaths = course.getCourseImage();//没有被删除的图片地址
            List<String> allPaths = imageDao.getImagePath(course.getCourseId());  //原有的全部的图片
            deleteNotExistImage(ListUtil.getDiff(existPaths,allPaths),course);//删除已经不存在的图片数据
            if (courseDao.updateCourse(course) != 1)//更新数据库
                throw new ErrorException("更新随笔失败,请重新操作");
            fileUtil.deleteImages(ListUtil.getDiff(existPaths,allPaths)); //删除已经被删除的图片
            return;
        }
        List<String> existPaths = course.getCourseImage();//没有被删除的图片地址
        List<String> allPaths = imageDao.getImagePath(course.getCourseId());  //该文章原有的全部的图片
        List<Image> images = transversePictureAndSave(pictures,course);//遍历图片并存储
        deleteNotExistImage(ListUtil.getDiff(existPaths,allPaths),course); //删除已经不存在的图片数据
        if (courseDao.updateCourse(course) != 1)//更新数据库
            deleteImage(images);
        if (imageDao.insertImages(images) != images.size())
            deleteImage(images);
        fileUtil.deleteImages(ListUtil.getDiff(existPaths,allPaths)); //删除磁盘上已经被删除的图片
    }

    public List<Course> findAllCourses(){
        List<Course> courses = courseDao.findAllCourses();
        return courses;
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
     * 删除已经不在的图片
     * @param notExistPaths
     * @param course
     */
    private void deleteNotExistImage(List<String> notExistPaths, Course course) {
        if (notExistPaths == null || notExistPaths.size() == 0) //当文章本来没有图,则这个值会为空
            return;
        if(imageDao.deleteImagesNotInList(notExistPaths,course.getCourseId()) != notExistPaths.size())
            throw new ErrorException("操作失败,请重新操作");
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
