package blog.geek.manager.service;

import blog.geek.dao.ImageDao;
import blog.geek.dao.ScheduleDao;
import blog.geek.entity.Image;
import blog.geek.entity.Schedule;
import blog.geek.exception.ErrorException;
import blog.geek.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 管理员管理课表服务类
 * @author yuanyang
 * @version 1.0
 */
@Service
public class ScheduleService {

    @Autowired
    private ScheduleDao scheduleDao;

    @Autowired
    private ImageDao imageDao;

    @Autowired
    private FileUtil fileUtil;

    /**
     * 更新单个课表
     * @param schedule
     */
    public void updateSchedule(Schedule schedule,MultipartFile picture){
        if (picture == null){
            scheduleDao.updateSchedule(schedule,new Image());
            return;
        }
        //获取之前的图片地址
        List<String> imagePath = imageDao.getImagePath(schedule.getScheduleId());
        //创建新图片
        Image image = new Image(null, picture.getOriginalFilename(),schedule.getScheduleId());
        //存放图片
        fileUtil.saveImage(picture,Process.class.getSimpleName(),schedule.getScheduleId());
        //设置图片地址
        image.setImageAddress(fileUtil.getVirtualPath());
        //更新教练
        if (scheduleDao.updateSchedule(schedule,image) != 1){
            //更新失败则把新添加的图片删除,抛出异常
            fileUtil.deleteImage(fileUtil.getRealPath());
            throw new ErrorException("数据库出错啦,请重新操作");
        }
        //更新成功,删除之前的图片
        fileUtil.deleteImage(imagePath.get(0));
    }

    /**
     * 查找课表
     * @param scheduleType
     */
    public Schedule findScheduleByType(String scheduleType){
        Schedule schedule = scheduleDao.findScheduleByType(scheduleType);
        if (schedule == null){
            throw new ErrorException("没有课表");
        }
        return schedule;
    }

}
