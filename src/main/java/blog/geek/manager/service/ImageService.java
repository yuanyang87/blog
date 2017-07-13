package blog.geek.manager.service;

import blog.geek.dao.ImageDao;
import blog.geek.entity.Image;
import blog.geek.exception.ErrorException;
import blog.geek.utils.FileUtil;
import blog.geek.utils.RandomStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author yuanyang
 * @version 1.0
 */
@Service
public class ImageService {

    @Autowired
    private ImageDao imageDao;

    @Autowired
    private FileUtil fileUtil;

    /**
     * 上传图片,并返回图片的一些信息给前端
     * @param picture
     * @param imageRef
     * @return
     */
    public Image insertImage(MultipartFile picture, String imageRef){
        if (picture == null)
            throw new ErrorException("尚未选择图片");
        Image image = new Image(RandomStringUtil.unrepeatableString(8),
                picture.getOriginalFilename(),imageRef);

        fileUtil.saveImage(picture,"",imageRef);
        image.setImageAddress(fileUtil.getVirtualPath());

        if (imageDao.insertImage(image)!= 1){
            fileUtil.deleteImage(image.getImageAddress());
            throw new ErrorException("上传图片出错咯~");
        }
        return image;
    }

    /**
     * 删除图片数据和磁盘上的图片
     * @param imagePaths
     */
    public void deleteImages(List<String> imagePaths){
        if (imagePaths == null || imagePaths.size() == 0)
            return;
        if (imageDao.deleteImagesByImagePath(imagePaths) != imagePaths.size())
            throw new ErrorException("删除失败,请重新操作");
        fileUtil.deleteImages(imagePaths);//如果数据都删除成功,则删除磁盘上的图片
    }

    /**
     * 获取图片地址
     * @param key
     * @return
     */
    public List<String> getImagePath(String key){
        return imageDao.getImagePath(key);
    }

    public List<String> getImagePaths(List<String> ids) {
        return imageDao.getImagePaths(ids);
    }
}
