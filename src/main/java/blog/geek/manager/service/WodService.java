package blog.geek.manager.service;

import blog.geek.dao.ImageDao;
import blog.geek.dao.WodDao;
import blog.geek.entity.Image;
import blog.geek.entity.Pager;
import blog.geek.entity.Wod;
import blog.geek.exception.ErrorException;
import blog.geek.utils.FileUtil;
import blog.geek.utils.RandomStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class WodService {

    @Autowired
    private WodDao wodDao;

    @Autowired
    private ImageDao imageDao;

    @Autowired
    private FileUtil fileUtil;

    public void insertWod(Wod wod, MultipartFile picture){
        if (picture == null){
            throw new ErrorException("你还没有选择照片");
        }
        wod.setWodId(RandomStringUtil.unrepeatableString(8));
        Image image = new Image(RandomStringUtil.unrepeatableString(8),
                picture.getOriginalFilename(),wod.getWodId());

        if (!fileUtil.saveImage(picture,wod.getClass().getSimpleName(),wod.getWodId()))
            throw new ErrorException("服务器在开小差,图片存放失败");

        image.setImageAddress(fileUtil.getVirtualPath());

        if (wodDao.insertWod(wod,image) != 1){
            fileUtil.deleteImage(image.getImageAddress());
            throw new ErrorException("添加失败,请重新操作");
        }
    }

    public void deleteWod(String wodId){
        List<String> imagePath = imageDao.getImagePath(wodId);
        if (wodDao.deleteWod(wodId) != 1)
            throw new ErrorException("删除失败,请重新操作");
        fileUtil.deleteImage(imagePath.get(0));
    }

    public void updateWod(Wod wod,MultipartFile picture){
        if (picture == null){
            wodDao.updateWod(wod,new Image());
            return;
        }
        //获取之前的图片地址
        List<String> imagePath = imageDao.getImagePath(wod.getWodId());
        //创建新图片
        Image image = new Image(null, picture.getOriginalFilename(),wod.getWodId());
        //存放图片
        fileUtil.saveImage(picture,Wod.class.getSimpleName(),wod.getWodId());
        //设置图片地址
        image.setImageAddress(fileUtil.getVirtualPath());
        //更新Wod
        if (wodDao.updateWod(wod,image) != 1){
            //更新失败则把新添加的图片删除,抛出异常
            fileUtil.deleteImage(fileUtil.getRealPath());
            throw new ErrorException("数据库出错啦,请重新操作");
        }
        //更新成功,删除之前的图片
        fileUtil.deleteImage(imagePath.get(0));
    }

    public Pager<Wod> selectAllWod(int pageIndex, int pageSize){
        Pager<Wod> coachPager = new Pager<Wod>(pageIndex,pageSize,wodDao.getTotal());

        List<Wod> wods = wodDao.selectAllWod(coachPager.getOffSet(),pageSize);
        if (wods == null || wods.size() == 0){
            throw new ErrorException("暂时没有教练");
        }
        coachPager.setResult(wods);
        return coachPager;
    }

    public Pager<Wod> selectWodByTime(int pageIndex, int pageSize,String time){
        Pager<Wod> coachPager = new Pager<Wod>(pageIndex,pageSize,wodDao.getTotal());

        List<Wod> wods = wodDao.selectWodByTime(coachPager.getOffSet(),pageSize,time);
        if (wods == null || wods.size() == 0){
            throw new ErrorException("暂时没有教练");
        }
        coachPager.setResult(wods);
        return coachPager;
    }

}
