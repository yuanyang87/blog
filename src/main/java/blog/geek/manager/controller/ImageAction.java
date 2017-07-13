package blog.geek.manager.controller;

import blog.geek.entity.Image;
import blog.geek.manager.service.ImageService;
import blog.geek.utils.Result;
import blog.geek.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图片请求处理
 * @author yuanyang
 * @version 1.0
 */
@RestController
@RequestMapping("/management")
public class ImageAction {

    @Autowired
    private ImageService imageService;

    /**
     * 在编辑文章的过程中上传图片,并返回信息给前端
     * @param picture
     * @param imageRef
     * @return
     */
    @RequestMapping(value = "/uploadImage",method = RequestMethod.POST)
    public Result insertImage(MultipartFile picture,String imageRef){
        Image image = imageService.insertImage(picture,imageRef);
        return ResultUtil.successResult(image);
    }

}
