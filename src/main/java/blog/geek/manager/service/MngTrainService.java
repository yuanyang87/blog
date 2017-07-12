package blog.geek.manager.service;

import blog.geek.dao.ImageDao;
import blog.geek.dao.TrainDao;
import blog.geek.entity.Image;
import blog.geek.entity.Train;
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
 * 管理训练服务类
 * @author yuanyang
 * @version 1.0
 */
@Service
public class MngTrainService {

    @Autowired
    private TrainDao trainDao;

    @Autowired
    private FileUtil fileUtil;

    @Autowired
    private ImageDao imageDao;

    /**
     * 插入训练
     * @param train
     * @param pictures
     */
    public void insertTrain(Train train, MultipartFile[] pictures){
        if (pictures == null || pictures.length == 0){//没有图片
            train.setTrainId(RandomStringUtil.unrepeatableString(8)); //设置编号
            trainDao.insertTrain(train);//插入数据
            return;
        }//有图片
        train.setTrainId(RandomStringUtil.unrepeatableString(8));//设置编号
        List<Image> images = transversePictureAndSave(pictures,train);//遍历图片并存储
        if (trainDao.insertTrain(train) != 1)//存放数据
            deleteImage(images);
        if (imageDao.insertImages(images) != images.size())//存放相关图片数据
            deleteImage(images);
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
     * @param pictures
     */
    public void updateTrain(Train train,MultipartFile[] pictures){
        if (pictures == null || pictures.length == 0){//没有新传入的图片
            List<String> existPaths = train.getTrainImg();//没有被删除的图片地址
            List<String> allPaths = imageDao.getImagePath(train.getTrainId());  //该文章原有的全部的图片
            deleteNotExistImage(ListUtil.getDiff(existPaths,allPaths),train);//删除已经不存在的图片数据
            if (trainDao.updateTrain(train) != 1)//更新数据库
                throw new ErrorException("更新随笔失败,请重新操作");
            fileUtil.deleteImages(ListUtil.getDiff(existPaths,allPaths)); //删除已经被删除的图片
            return;
        }
        List<String> existPaths = train.getTrainImg();//没有被删除的图片地址
        List<String> allPaths = imageDao.getImagePath(train.getTrainId());  //该文章原有的全部的图片
        List<Image> images = transversePictureAndSave(pictures,train);//遍历图片并存储
        deleteNotExistImage(ListUtil.getDiff(existPaths,allPaths),train); //删除已经不存在的图片数据
        if (trainDao.updateTrain(train) != 1)//更新数据库
            deleteImage(images);
        if (imageDao.insertImages(images) != images.size())
            deleteImage(images);
        fileUtil.deleteImages(ListUtil.getDiff(existPaths,allPaths)); //删除磁盘上已经被删除的图片
    }

    /**
     * 获取全部训练
     * @return
     */
    public List<Train> findAllTrains(){
        List<Train> trains = trainDao.findAllTrains();
        return trains;
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
     * 遍历图片
     * @param pictures
     * @param train
     * @return
     */
    private List<Image> transversePictureAndSave(MultipartFile[] pictures, Train train) {
        List<Image> images = new ArrayList<Image>();
        for (MultipartFile file : pictures){
            Image image = new Image(RandomStringUtil.unrepeatableString(8),
                    file.getOriginalFilename(),train.getTrainId());
            if (!fileUtil.saveImage(file,train.getClass().getSimpleName(),train.getTrainId())){
                deleteImage(images);
            }
            image.setImageAddress(fileUtil.getVirtualPath());
            images.add(image);
        }
        return images;
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
