package blog.geek.manager.service;

import blog.geek.dao.TrainDao;
import blog.geek.entity.Pager;
import blog.geek.entity.Train;
import blog.geek.exception.ErrorException;
import blog.geek.utils.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 管理训练服务类
 * @author yuanyang
 * @version 1.0
 */
@Service
public class TrainService {

    @Autowired
    private TrainDao trainDao;
    @Autowired
    private ImageService imageService;

    /**
     * 插入训练
     * @param train
     */
    public void insertTrain(Train train) throws ParseException {
        List<String> notAppear = ListUtil.searchList(train.getTrainContent(),imageService.getImagePath(train.getTrainId()));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        train.setTrainTime(simpleDateFormat.format(new Date()));

        if (trainDao.insertTrain(train) != 1)//存放数据
            throw new ErrorException("提交失败,请重新操作");

        imageService.deleteImages(notAppear);
    }

    /**
     * 删除训练
     * @param trainId
     */
    public void deleteTrain(String trainId){
        List<String> imagePaths = imageService.getImagePath(trainId);

        if (trainDao.deleteTrain(trainId) != 1)
            throw new ErrorException("删除相关训练失败,请重新操作");

        imageService.deleteImages(imagePaths);
    }

    /**
     * 批量删除训练
     * @param trainIds
     */
    public void deleteTrains(List<String> trainIds){
        List<String> imagePaths = imageService.getImagePaths(trainIds);

        if (trainDao.deleteTrains(trainIds) != trainIds.size())
            throw new ErrorException("删除随笔失败,请重新操作");

        imageService.deleteImages(imagePaths);
    }

    /**
     * 更新训练
     * @param train
     */
    public void updateTrain(Train train){
        List<String> notAppear = ListUtil.searchList(train.getTrainContent(),imageService.getImagePath(train.getTrainId()));  //该文章原有的全部的图片

        if (trainDao.updateTrain(train) != 1)//更新数据库
            throw new ErrorException("更新失败,请重新操作");

        imageService.deleteImages(notAppear);
    }

    /**
     * 获取训练
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public Pager<Train> findAllTrains(int pageIndex, int pageSize){
        Pager<Train> trainPager = new Pager<Train>(pageIndex,pageSize,trainDao.getTotal());

        List<Train> trains = trainDao.findAllTrains(trainPager.getOffSet(),pageSize);

        if (trains == null || trains.size() == 0)
            throw new ErrorException("没有找到你要的数据");

        trainPager.setResult(trains);

        return trainPager;
    }

    public List<Train> findTrainByKeyWord(String key) {
        List<Train> trains = trainDao.findTrainsByKeyWord(key);
        return trains;
    }

    public List<Train> findTrainByTime(String date) {
        List<Train> trains = trainDao.findTrainByTime(date);
        return trains;
    }

    public Train findTrainById(String trainId) {
        Train train = trainDao.findTrainById(trainId);
        if (train == null){
            throw new ErrorException("查找失败");
        }
        return train;
    }
}
