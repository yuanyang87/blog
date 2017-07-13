package blog.geek.manager.service;

import blog.geek.dao.ImageDao;
import blog.geek.dao.TrainDao;
import blog.geek.entity.Image;
import blog.geek.entity.Pager;
import blog.geek.entity.Train;
import blog.geek.exception.ErrorException;
import blog.geek.utils.FileUtil;
import blog.geek.utils.RandomStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private FileUtil fileUtil;
    @Autowired
    private ImageDao imageDao;

    /**
     * 插入训练
     * @param train
     */
    public void insertTrain(Train train){
        train.setTrainId(RandomStringUtil.unrepeatableString(8));//设置编号

        if (trainDao.insertTrain(train) != 1)//存放数据
            throw new ErrorException("添加失败,请重新操作");
    }

    /**
     * 删除训练
     * @param trainId
     */
    public void deleteTrain(String trainId){
        List<String> imagePaths = imageDao.getImagePath(trainId);

        if (trainDao.deleteTrain(trainId) != 1)
            throw new ErrorException("删除相关训练失败,请重新操作");

        if (imageDao.deleteImagesByImagePath(imagePaths) != imagePaths.size())
            throw new ErrorException("删除相关训练失败,请重新操作");

        fileUtil.deleteImages(imagePaths);//如果数据都删除成功,则删除磁盘上的图片
    }

    /**
     * 批量删除训练
     * @param trainIds
     */
    public void deleteTrains(List<String> trainIds){
        List<String> imagePaths = imageDao.getImagePaths(trainIds);

        if (trainDao.deleteTrains(trainIds) != trainIds.size())
            throw new ErrorException("删除随笔失败,请重新操作");

        if (imageDao.deleteImagesByImagePath(imagePaths) != imagePaths.size())
            throw new ErrorException("删除随笔失败,请重新操作");

        fileUtil.deleteImages(imagePaths);
    }

    /**
     * 更新训练
     * @param train
     */
    public void updateTrain(Train train){
        List<String> allPaths = imageDao.getImagePath(train.getTrainId());  //该文章原有的全部的图片

        if (trainDao.updateTrain(train) != 1)//更新数据库
            throw new ErrorException("更新失败,请重新操作");

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

    /**
     * 删除不存在的图片
     * @param notExistPaths
     * @param train
     */
    private void deleteNotExistImage(List<String> notExistPaths, Train train) {
        if (notExistPaths == null || notExistPaths.size() == 0) //当文章本来没有图,则这个值会为空
            return;
        if(imageDao.deleteImagesNotInList(notExistPaths,train.getTrainId()) != notExistPaths.size())
            throw new ErrorException("更新随笔失败,请重新操作");
    }

    /**
     * 删除没有了的图片
     * @param images
     */
    private void deleteImage(List<Image> images) {
        for (Image image:images){
            fileUtil.deleteImage(image.getImageAddress());
        }
        throw new ErrorException("操作失败,请重新操作");
    }


}
