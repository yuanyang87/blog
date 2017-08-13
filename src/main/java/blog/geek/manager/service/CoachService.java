package blog.geek.manager.service;

import blog.geek.dao.CoachDao;
import blog.geek.dao.ImageDao;
import blog.geek.entity.Coach;
import blog.geek.entity.Image;
import blog.geek.entity.Pager;
import blog.geek.exception.ErrorException;
import blog.geek.utils.FileUtil;
import blog.geek.utils.RandomStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class CoachService {

    @Autowired
    private CoachDao coachDao;

    @Autowired
    private ImageDao imageDao;

    @Autowired
    private FileUtil fileUtil;

    public void insertCoach(Coach coach, MultipartFile picture){
        if (picture == null){
            throw new ErrorException("你还没有选择照片");
        }
        coach.setCoachId(RandomStringUtil.unrepeatableString(8));
        Image image = new Image(RandomStringUtil.unrepeatableString(8),
                picture.getOriginalFilename(),coach.getCoachId());

        if (!fileUtil.saveImage(picture,coach.getClass().getSimpleName(),coach.getCoachId()))
            throw new ErrorException("服务器在开小差,图片存放失败");

        image.setImageAddress(fileUtil.getVirtualPath());

        if (coachDao.insertCoach(coach,image) != 1){
            fileUtil.deleteImage(image.getImageAddress());
            throw new ErrorException("添加教练失败,请重新操作");
        }
    }

    public void deleteCoach(String coachId){
        List<String> imagePath = imageDao.getImagePath(coachId);
        if (coachDao.deleteCoach(coachId) != 1)
            throw new ErrorException("删除教练失败,请重新操作");
        fileUtil.deleteImage(imagePath.get(0));
    }

    public void updateCoach(Coach coach,MultipartFile picture){
        if (picture == null){
            coachDao.updateCoach(coach,new Image());
            return;
        }
        //获取之前的图片地址
        List<String> imagePath = imageDao.getImagePath(coach.getCoachId());
        //创建新图片
        Image image = new Image(null, picture.getOriginalFilename(),coach.getCoachId());
        //存放图片
        fileUtil.saveImage(picture,Coach.class.getSimpleName(),coach.getCoachId());
        //设置图片地址
        image.setImageAddress(fileUtil.getVirtualPath());
        //更新教练
        if (coachDao.updateCoach(coach,image) != 1){
            //更新失败则把新添加的图片删除,抛出异常
            fileUtil.deleteImage(fileUtil.getRealPath());
            throw new ErrorException("数据库出错啦,请重新操作");
        }
        //更新成功,删除之前的图片
        fileUtil.deleteImage(imagePath.get(0));
    }

    public Pager<Coach> selectAllCoaches(int pageIndex, int pageSize){
        Pager<Coach> coachPager = new Pager<Coach>(pageIndex,pageSize,coachDao.getTotal());

        List<Coach> coaches = coachDao.selectAllCoaches(coachPager.getOffSet(),pageSize);
        if (coaches == null || coaches.size() == 0){
            throw new ErrorException("暂时没有教练");
        }
        coachPager.setResult(coaches);
        return coachPager;
    }

}
