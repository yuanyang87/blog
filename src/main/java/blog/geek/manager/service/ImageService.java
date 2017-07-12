package blog.geek.manager.service;

import blog.geek.dao.ImageDao;
import blog.geek.entity.Image;
import blog.geek.exception.ErrorException;
import blog.geek.utils.FileUtil;
import blog.geek.utils.RandomStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    public String insertImage(MultipartFile picture, String imageRef){
        Image image = new Image(RandomStringUtil.unrepeatableString(8),
                picture.getOriginalFilename(),imageRef);

        fileUtil.saveImage(picture,"",imageRef);
        image.setImageAddress(fileUtil.getVirtualPath());

        if (imageDao.insertImage(image)!= 1){
            fileUtil.deleteImage(image.getImageAddress());
            throw new ErrorException("上传图片出错咯~");
        }
        return fileUtil.getVirtualPath();
    }

}
